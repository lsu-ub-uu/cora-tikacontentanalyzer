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

import java.io.InputStream;

import org.apache.tika.Tika;
import org.apache.tika.io.TikaInputStream;

import se.uu.ub.cora.contentanalyzer.ContentAnalyzer;
import se.uu.ub.cora.contentanalyzer.ContentAnalyzerException;

public class TikaContentAnalyzer implements ContentAnalyzer {

	private static final String DETECTION_ERROR_MESSAGE = "Failed to detect mimetype from resource: ";
	private Tika tika;

	public TikaContentAnalyzer(Tika tika) {
		this.tika = tika;
	}

	@Override
	public String getMimeType(InputStream resource) {
		try (TikaInputStream tikaInputStream = TikaInputStream.get(resource)) {
			return tika.detect(tikaInputStream);
		} catch (Exception e) {
			throw ContentAnalyzerException
					.withMessageAndException(DETECTION_ERROR_MESSAGE + e.getMessage(), e);
		}
	}

	public Tika onlyForTestGetTika() {
		return tika;
	}

}
