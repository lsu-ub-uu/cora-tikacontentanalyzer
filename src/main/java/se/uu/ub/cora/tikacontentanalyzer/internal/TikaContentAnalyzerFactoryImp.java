package se.uu.ub.cora.tikacontentanalyzer.internal;

import org.apache.tika.Tika;

import se.uu.ub.cora.contentanalyzer.ContentAnalyzer;
import se.uu.ub.cora.tikacontentanalyzer.TikaContentAnalyzer;

public class TikaContentAnalyzerFactoryImp implements TikaContentAnalyzerFactory {

	@Override
	public ContentAnalyzer factor() {
		return new TikaContentAnalyzer(new Tika());
	}

}
