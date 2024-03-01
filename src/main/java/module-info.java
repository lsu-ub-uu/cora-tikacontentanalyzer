import se.uu.ub.cora.tikacontentanalyzer.TikaContentAnalyzerProvider;

/**
 * The fedora module provides interfaces and classes to use a Fedora Commons System in a Cora based
 * system.
 */
module se.uu.ub.cora.tikacontentanalyzer {
	requires org.apache.tika.core;
	requires org.apache.commons.io;
	// java.sql needed by TikaInputStream
	requires java.sql;
	requires se.uu.ub.cora.binary;

	provides se.uu.ub.cora.binary.contentanalyzer.ContentAnalyzerInstanceProvider
			with TikaContentAnalyzerProvider;

}