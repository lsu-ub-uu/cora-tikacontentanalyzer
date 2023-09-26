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
		try {
			TikaInputStream tikaInputStream = TikaInputStream.get(resource);
			return tika.detect(tikaInputStream);
		} catch (Exception e) {
			throw ContentAnalyzerException
					.withMessageAndException(DETECTION_ERROR_MESSAGE + e.getMessage(), e);
		}
	}

}
