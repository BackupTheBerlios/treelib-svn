package org.treelib;
import java.util.Arrays;

import org.junit.Before;
import org.treelib.distance.levted.LevTed;
import org.treelib.distance.ted.ShashaAndZhangReferenceImpl;

import antlr.RecognitionException;
import antlr.TokenStreamException;

import static org.junit.Assert.*;

import cern.colt.matrix.ObjectMatrix1D;
import cern.colt.matrix.impl.SparseObjectMatrix1D;


import junit.framework.TestCase;


public class TestLevTed
        extends AbstractTreeTester<LevTed> {

	 
    
        
@Before public void setUp() { 
		
		super.treeA = Data.treesA;
		super.treeB = Data.treesB;
		super.expectedResult = Data.NA;
       
    }

	@Override
	protected LevTed getDistance(String t) throws RecognitionException, TokenStreamException {
		return TreeFactory.createLevTed(t);
	}

	@Override
	protected void validateProperties(double result, String ta, String tb) throws RecognitionException, TokenStreamException {
		// make sure that the result is also the same as Sasha's result:
		ShashaAndZhangReferenceImpl a = TreeFactory.createTedShashaZhang(ta);
		ShashaAndZhangReferenceImpl b = TreeFactory.createTedShashaZhang(tb);	
		double res = a.distance(b);
		assertEquals(result, res, 9.0); 
	}


    
}
