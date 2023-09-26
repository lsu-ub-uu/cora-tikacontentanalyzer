package se.uu.ub.cora.tikacontentanalyzer;

import java.io.InputStream;
import java.text.MessageFormat;

import org.apache.tika.Tika;

import se.uu.ub.cora.contentanalyzer.ContentAnalyzer;
import se.uu.ub.cora.contentanalyzer.ContentAnalyzerException;

public class TikaContentAnalyzer implements ContentAnalyzer {

	private static final String DETECTION_ERROR_MESSAGE = "Failed to detect mimetype from resource: ";
	private static final String DETECTION_ERROR_MESSAGE_FILENAME = "Failed to detect mimetype from"
			+ " resource and filename {0}: {1}";
	private Tika tika;

	public TikaContentAnalyzer(Tika tika) {
		this.tika = tika;
	}

	@Override
	public String getMimeType(InputStream resource) {
		try {
			return tika.detect(resource);
		} catch (Exception e) {
			throw ContentAnalyzerException
					.withMessageAndException(DETECTION_ERROR_MESSAGE + e.getMessage(), e);
		}
	}

	@Override
	public String getMimeTypeWithFileName(InputStream resource, String filename) {
		try {
			return tika.detect(resource, filename);
		} catch (Exception e) {
			throw ContentAnalyzerException
					.withMessageAndException(buildErrorMessage(e.getMessage(), filename), e);
		}
	}

	private String buildErrorMessage(String exceptionErrorMessage, String filename) {
		return MessageFormat.format(DETECTION_ERROR_MESSAGE_FILENAME, filename,
				exceptionErrorMessage);
	}
}
