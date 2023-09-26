/*
 * Copyright 2023 Uppsala University Library
 *
 * This file is part of Cora.
 *
 *     Cora is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Cora is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Cora.  If not, see <http://www.gnu.org/licenses/>.
 */
package se.uu.ub.cora.tikacontentanalyzer;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.io.IOException;

import org.apache.tika.io.TikaInputStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import se.uu.ub.cora.contentanalyzer.ContentAnalyzer;
import se.uu.ub.cora.contentanalyzer.ContentAnalyzerException;
import se.uu.ub.cora.tikacontentanalyzer.spy.InputStreamSpy;
import se.uu.ub.cora.tikacontentanalyzer.spy.TikaSpy;

public class TikaContentAnalyzerTest {

	private static final String DETECTION_ERROR_MESSAGE = "Failed to detect mimetype from resource: ";
	private static final String SOME_EXCEPTION_MESSAGE = "someExceptionMessage";
	private TikaContentAnalyzer analyzer;
	private InputStreamSpy inputStream;
	private TikaSpy tika;

	@BeforeMethod
	public void beforeMethod() {
		tika = new TikaSpy();

		analyzer = new TikaContentAnalyzer(tika);
		inputStream = new InputStreamSpy();
	}

	@Test
	public void testImplementsContentAnalyzer() throws Exception {
		assertTrue(analyzer instanceof ContentAnalyzer);
	}

	@Test
	public void testCallGetMimeType() throws Exception {
		String mimeType = analyzer.getMimeType(inputStream);

		tika.MCR.assertReturn("detect", 0, mimeType);
	}

	@Test
	public void testInputStreamConvertedToTikaInputStream() throws Exception {
		analyzer.getMimeType(inputStream);

		TikaInputStream tikaInputStream = (TikaInputStream) tika.MCR
				.getValueForMethodNameAndCallNumberAndParameterName("detect", 0, "stream");
		assertInputStreamIsConsumed(tikaInputStream);
	}

	private void assertInputStreamIsConsumed(TikaInputStream tikaInputStream) throws IOException {
		tikaInputStream.read();
		inputStream.MCR.assertMethodWasCalled("read");
	}

	@Test
	public void testExceptionWhileDetecting() throws Exception {
		RuntimeException runtimeException = new RuntimeException(SOME_EXCEPTION_MESSAGE);
		tika.MRV.setAlwaysThrowException("detect", runtimeException);
		try {
			analyzer.getMimeType(inputStream);
			fail();
		} catch (Exception e) {
			assertTrue(e instanceof ContentAnalyzerException);
			assertEquals(e.getMessage(), DETECTION_ERROR_MESSAGE + SOME_EXCEPTION_MESSAGE);
			assertEquals(e.getCause(), runtimeException);
		}
	}
}
