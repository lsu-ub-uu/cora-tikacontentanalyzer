package se.uu.ub.cora.tikacontentanalyzer.spy;

import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.Tika;

import se.uu.ub.cora.testutils.mcr.MethodCallRecorder;
import se.uu.ub.cora.testutils.mrv.MethodReturnValues;

public class TikaSpy extends Tika {

	public MethodCallRecorder MCR = new MethodCallRecorder();
	public MethodReturnValues MRV = new MethodReturnValues();

	public TikaSpy() {
		MCR.useMRV(MRV);
		MRV.setDefaultReturnValuesSupplier("detect", () -> "somemedia/somemediatype");
	}

	@Override
	public String detect(InputStream stream) throws IOException {
		return (String) MCR.addCallAndReturnFromMRV("stream", stream);

	}

	@Override
	public String detect(InputStream stream, String filename) throws IOException {
		return (String) MCR.addCallAndReturnFromMRV("stream", stream, "filename", filename);
	}

}
