package org.treelib;

import java.io.StringReader;

import org.treelib.distance.bdist.BDist;
import org.treelib.distance.levted.LevTed;
import org.treelib.distance.mtd.MTD;
import org.treelib.distance.ted.DMRW;
import org.treelib.distance.ted.ShashaAndZhangReferenceImpl;

import net.obsearch.example.SliceLexer;
import net.obsearch.example.SliceParser;

import antlr.RecognitionException;
import antlr.TokenStreamException;



/*
 Furia-chan: An Open Source software license violation detector.    
 Copyright (C) 2008 Kyushu Institute of Technology

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * SliceFactory Creates Tree objects and SliceForest objects from strings.
 * @author Arnoldo Jose Muller Molina
 */
public class TreeFactory {

    /**
     * Creates a tree from a slice string definition
     * @param x
     * @return
     */
    protected static TreeIds createSliceAST(String x)
            throws RecognitionException, TokenStreamException {
        SliceLexer lexer = new SliceLexer(new StringReader(x));
        SliceParser parser = new SliceParser(lexer);
        parser.setASTNodeClass(TreeIds.class.getCanonicalName());
        parser.slice();
        TreeIds s = (TreeIds) parser.getAST();
        s.updateIdInfo();
        s.updateContains();
        return s;
    }

    /**
     * Creates a new multi-set based tree distance object. From the paper:
     *  Arnoldo Muller, Kouichi Hirata and Takeshi Shinohara. A Tree Distance Function Based
     *  on Multi-sets. In ALSIP 2008 - Osaka, Japan (to appear in LNCS).
     * @param t A string representation of a tree.
     * @return
     * @throws RecognitionException
     * @throws TokenStreamException
     */
    public static MTD createMTD(String t)
            throws RecognitionException, TokenStreamException {
        SliceLexer lexer = new SliceLexer(new StringReader(t));
        SliceParser parser = new SliceParser(lexer);
        parser.setASTNodeClass(FTree.class.getCanonicalName());
        parser.slice();
        FTree s = (FTree) parser.getAST();
        MTD m = new MTD(s);
        return m;
    }
    
    public static LevTed createLevTed(String t) throws RecognitionException, TokenStreamException{
    	return new LevTed(createSliceASTLean(t));
    }
    
    public static BDist createBDist(String t) throws RecognitionException, TokenStreamException{
    	return new BDist(createSliceASTLean(t));
    }

    /**
     * creates the base clase of the ast. it is lighter
     * @param x
     * @return
     * @throws RecognitionException
     * @throws TokenStreamException
     */
    protected static Tree createSliceASTLean(String x)
            throws RecognitionException, TokenStreamException {
        SliceLexer lexer = new SliceLexer(new StringReader(x));
        SliceParser parser = new SliceParser(lexer);
        parser.setASTNodeClass(Tree.class.getCanonicalName());
        try {
            parser.slice();
        } catch (RecognitionException e) {
            e.fileName = x + " " + e.fileName;
            throw e;
        }
        return (Tree) parser.getAST();
    }

    protected static Forest createSliceForest(String x)
            throws RecognitionException, TokenStreamException {
        // STD was the fastest data structure.
        return new ForestStd(TreeFactory.createTreeForStandardTed(x));
    }
    
    /**
     * Create a new Tree that can be matched with the O(n^4) Tree edit
     * distance algorithm:
     * D. Shasha and K. Zhang. Simple fast algorithms for the editing distance between trees and related problems.
     * SIAM Journal of Computing, 18(6):1245–1262, 1989.
     * @param t A string representation of a tree.
     * @return
     * @throws RecognitionException
     * @throws TokenStreamException
     */
    public static ShashaAndZhangReferenceImpl createTedShashaZhang(String t) throws RecognitionException, TokenStreamException{
    	return new ShashaAndZhangReferenceImpl(createSliceForest(t));
    }
    
    /**
     * Create a new Tree that can be matched with the O(n^3) Tree edit distance algorithm
     *  An optimal decomposition algorithm for tree edit distance (2007)
     *  Erik D. Demaine, Shay Mozes, Benjamin Rossman, Oren Weimann 
     * @param x A string representation of a tree.
     * @return A new DMRW object.
     * @throws RecognitionException
     * @throws TokenStreamException
     */
    public static DMRW createTedDWRW(String t) throws RecognitionException, TokenStreamException{
    	return new DMRW(createSliceForest(t));
    }


    public static TreeForStandardTed createTreeForStandardTed(String x)
            throws RecognitionException, TokenStreamException {
        SliceLexer lexer = new SliceLexer(new StringReader(x));
        SliceParser parser = new SliceParser(lexer);
        parser.setASTNodeClass(TreeForStandardTed.class.getCanonicalName());
        parser.slice();
        TreeForStandardTed s = (TreeForStandardTed) parser.getAST();
        s.updateIdInfo();
        s.updateContains();
        return s;
    }

    public static Forest createEmptySliceForest() {
        return new ForestStd();
    }

}
