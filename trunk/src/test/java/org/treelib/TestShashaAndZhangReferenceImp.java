package org.treelib;

import org.junit.Before;
import org.treelib.distance.ted.ShashaAndZhangReferenceImpl;

import antlr.RecognitionException;
import antlr.TokenStreamException;

import junit.framework.TestCase;

public class TestShashaAndZhangReferenceImp extends AbstractTreeTester<ShashaAndZhangReferenceImpl> {
	@Before
	public void setUp() {

		super.treeA = Data.treesA;
		super.treeB = Data.treesB;
		super.expectedResult = Data.tedAB;

	}

	@Override
	protected ShashaAndZhangReferenceImpl getDistance(String t)
			throws RecognitionException, TokenStreamException {
		return TreeFactory.createTedShashaZhang(t);
	}

	@Override
	protected void validateProperties(double result, String ta, String tb)
			throws RecognitionException, TokenStreamException {
		// nothing to do as it is our "boot strap" function.		
	}

}
