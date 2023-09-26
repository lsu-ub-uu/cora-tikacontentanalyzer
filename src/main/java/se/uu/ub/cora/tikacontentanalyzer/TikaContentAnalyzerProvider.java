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

import se.uu.ub.cora.contentanalyzer.ContentAnalyzer;
import se.uu.ub.cora.contentanalyzer.ContentAnalyzerInstanceProvider;
import se.uu.ub.cora.tikacontentanalyzer.internal.TikaContentAnalyzerFactory;
import se.uu.ub.cora.tikacontentanalyzer.internal.TikaContentAnalyzerFactoryImp;

public class TikaContentAnalyzerProvider implements ContentAnalyzerInstanceProvider {

	@Override
	public int getOrderToSelectImplementionsBy() {
		return 0;
	}

	@Override
	public ContentAnalyzer getContentAnalyzer() {
		TikaContentAnalyzerFactory factory = createTikaContentAnalyzerFactory();
		return factory.factor();
	}

	TikaContentAnalyzerFactory createTikaContentAnalyzerFactory() {
		return new TikaContentAnalyzerFactoryImp();
	}

}
