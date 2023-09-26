package se.uu.ub.cora.tikacontentanalyzer.internal;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import se.uu.ub.cora.tikacontentanalyzer.TikaContentAnalyzer;

public class TikaContentAnalyzerFactoryTest {

	TikaContentAnalyzerFactoryImp factory;

	@BeforeMethod
	public void beforeMethod() {
		factory = new TikaContentAnalyzerFactoryImp();

	}

	@Test
	public void testImplements() throws Exception {
		assertTrue(factory instanceof TikaContentAnalyzerFactory);

	}

	@Test
	public void testFactorContentAnalyzerIntiliaziedWithNewTika() throws Exception {

		TikaContentAnalyzer analyzer1 = (TikaContentAnalyzer) factory.factor();
		TikaContentAnalyzer analyzer2 = (TikaContentAnalyzer) factory.factor();

		assertNotNull(analyzer1.onlyForTestGetTika());
		assertNotNull(analyzer2.onlyForTestGetTika());
		assertNotEquals(analyzer1, analyzer2);
	}

}
