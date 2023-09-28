import se.uu.ub.cora.tikacontentanalyzer.TikaContentAnalyzerProvider;

/**
 * The fedora module provides interfaces and classes to use a Fedora Commons System in a Cora based
 * system.
 */
module se.uu.ub.cora.tikacontentanalyzer {
	requires se.uu.ub.cora.contentanalyzer;
	requires org.apache.tika.core;
	requires org.apache.commons.io;
	// java.sql needed by TikaInputStream
	requires java.sql;

	provides se.uu.ub.cora.contentanalyzer.ContentAnalyzerInstanceProvider
			with TikaContentAnalyzerProvider;

}