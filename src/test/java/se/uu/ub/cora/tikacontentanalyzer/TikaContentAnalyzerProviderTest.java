/*
 * Copyright 2023 Uppsala University Library
 *
 * This file is part of Cora.
 *
 *     Cora is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Cora is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Cora.  If not, see <http://www.gnu.org/licenses/>.
 */
package se.uu.ub.cora.tikacontentanalyzer;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import se.uu.ub.cora.binary.contentanalyzer.ContentAnalyzerInstanceProvider;
import se.uu.ub.cora.tikacontentanalyzer.internal.TikaContentAnalyzerFactory;
import se.uu.ub.cora.tikacontentanalyzer.spy.TikaContentAnalyzerFactorySpy;

public class TikaContentAnalyzerProviderTest {

	TikaContentAnalyzerProvider provider;
	private TikaContentAnalyzerFactorySpy factory;

	@BeforeMethod
	public void beforeMethod() {
		provider = new TikaContentAnalyzerProvider();
		factory = new TikaContentAnalyzerFactorySpy();
	}

	@Test
	public void testProviderImplementsContentAnalyzerInstanceProvider() throws Exception {
		assertTrue(provider instanceof ContentAnalyzerInstanceProvider);
	}

	@Test
	public void testGetOrderToSelectImplementionsBy() throws Exception {
		assertEquals(provider.getOrderToSelectImplementionsBy(), 0);
	}

	@Test
	public void testContentAnalyzer() throws Exception {
		TikaContentAnalyzer contentAnalyzer = (TikaContentAnalyzer) provider.getContentAnalyzer();

		assertNotNull(contentAnalyzer);

	}

	@Test
	public void testContentAnalyzerCreatedUsingFactor() throws Exception {
		provider.onlyForTestSetFactory(factory);

		provider.getContentAnalyzer();

		factory.MCR.assertMethodWasCalled("factor");

	}

	@Test
	public void testDefaultFactory() throws Exception {
		TikaContentAnalyzerFactory factory = provider.onlyForTestGetFactory();
		assertTrue(factory instanceof TikaContentAnalyzerFactory);
	}

	@Test
	public void testProviderOnlyCreatesFactoryOnce() throws Exception {

		provider.getContentAnalyzer();
		TikaContentAnalyzerFactory factory1 = provider.onlyForTestGetFactory();

		provider.getContentAnalyzer();
		TikaContentAnalyzerFactory factory2 = provider.onlyForTestGetFactory();

		assertSame(factory1, factory2);
	}
}
