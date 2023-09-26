package se.uu.ub.cora.tikacontentanalyzer.spy;

import se.uu.ub.cora.contentanalyzer.ContentAnalyzer;
import se.uu.ub.cora.testutils.mcr.MethodCallRecorder;
import se.uu.ub.cora.testutils.mrv.MethodReturnValues;
import se.uu.ub.cora.tikacontentanalyzer.internal.TikaContentAnalyzerFactory;

public class TikaContentAnalyzerFactorySpy implements TikaContentAnalyzerFactory {

	public MethodCallRecorder MCR = new MethodCallRecorder();
	public MethodReturnValues MRV = new MethodReturnValues();

	public TikaContentAnalyzerFactorySpy() {
		MCR.useMRV(MRV);
		MRV.setDefaultReturnValuesSupplier("factor", ContentAnalyzerSpy::new);
	}

	@Override
	public ContentAnalyzer factor() {
		return (ContentAnalyzer) MCR.addCallAndReturnFromMRV();
	}

}
