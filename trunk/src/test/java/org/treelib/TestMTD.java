package org.treelib;


import org.junit.Before;
import org.treelib.distance.mtd.MTD;
import org.treelib.distance.ted.DMRW;
import org.treelib.distance.ted.ShashaAndZhangReferenceImpl;

import antlr.RecognitionException;
import antlr.TokenStreamException;

import junit.framework.TestCase;

import static org.junit.Assert.*;

public class TestMTD extends AbstractTreeTester<MTD> {
	
	@Before public void setUp() { 
		
		super.treeA = Data.treesA;
		super.treeB = Data.treesB;
		super.expectedResult = Data.NA;
       
    }

	@Override
	protected MTD getDistance(String t) throws RecognitionException, TokenStreamException {
		return TreeFactory.createMTD(t);
	}

	@Override
	protected void validateProperties(double result, String ta, String tb) throws RecognitionException, TokenStreamException {
		// make sure that the result is also the same as Sasha's result:
		ShashaAndZhangReferenceImpl a = TreeFactory.createTedShashaZhang(ta);
		ShashaAndZhangReferenceImpl b = TreeFactory.createTedShashaZhang(tb);	
		double res = a.distance(b);
		MTD mA = getDistance(ta);
		MTD mB = getDistance(tb);
		int dep = Math.max(mA.getTree().depth(), mB.getTree().depth());
		assertTrue( result / (dep+1) <= res && res <= result * 2); 
	}


	
	
	
    

}
