package se.uu.ub.cora.tikacontentanalyzer.internal;

import se.uu.ub.cora.contentanalyzer.ContentAnalyzer;

public interface TikaContentAnalyzerFactory {

	/**
	 * factor creates a ContentAnalyzer
	 * 
	 * @return
	 */
	ContentAnalyzer factor();

}
