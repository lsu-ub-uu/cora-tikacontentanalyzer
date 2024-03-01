package se.uu.ub.cora.tikacontentanalyzer.spy;

import java.io.InputStream;

import se.uu.ub.cora.binary.contentanalyzer.ContentAnalyzer;

public class ContentAnalyzerSpy implements ContentAnalyzer {

	@Override
	public String getMimeType(InputStream resource) {
		return null;
	}

}
