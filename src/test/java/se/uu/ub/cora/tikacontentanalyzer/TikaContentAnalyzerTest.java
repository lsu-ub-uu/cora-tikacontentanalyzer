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
