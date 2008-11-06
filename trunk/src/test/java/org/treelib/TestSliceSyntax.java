package org.treelib;

import java.io.DataInputStream;
import java.io.StringBufferInputStream;
import java.io.StringReader;

import net.obsearch.example.SliceLexer;
import net.obsearch.example.SliceParser;

import junit.framework.TestCase;

import antlr.RecognitionException;
import antlr.Token;
import antlr.TokenStreamException;
import antlr.collections.AST;





public class TestSliceSyntax extends TestCase {

	StringReader slice1;
	StringReader slice2;
	StringReader slice3;
	StringReader slice4;
	StringReader slice5;
	StringReader slice6;
	StringReader slice7;
	protected void setUp() throws Exception {
		super.setUp();
		slice1 = new StringReader("finterfaceInvoke(    fmethodRef()    , finterfaceInvoke(fmethodRef(), finterfaceInvoke(fmethodRef(),      finstanceFieldRef(fthisRef(),ffieldRef())), fnull(), fnull(), fparameterRef(),     fnull()), fstringConstant())");
		slice2 = new StringReader("fvirtualInvokeExpr(fmethodRef())");
		slice3 = new StringReader("one(dos)");
		slice4 = new StringReader("float(-4)");
		slice5 = new StringReader("finterfaceInvoke(    fmethodRef    , finterfaceInvoke(fmethodRef, finterfaceInvoke(fmethodRef,      finstanceFieldRef(fthisRef,ffieldRef)), fnull, fnull, fparameterRef,     fnull()), fstringConstant)");
		slice6 = new StringReader("D");
		
		slice7 = new StringReader("G(Y,M,U(M,X),U(M,X),U(U(M,X),X),U(U(M,X),X),b(3))");
	}
	
	public void test1()throws Exception {
		SliceLexer lexer = new SliceLexer(slice1);		
		SliceParser parser = new SliceParser(lexer);
		parser.setASTNodeClass(Tree.class.getCanonicalName());
	    parser.slice();
	    AST tree = parser.getAST();
	    assertTrue(tree.toStringList().equals(" ( finterfaceInvoke fmethodRef ( finterfaceInvoke fmethodRef ( finterfaceInvoke fmethodRef ( finstanceFieldRef fthisRef ffieldRef ) ) fnull fnull fparameterRef fnull ) fstringConstant )"));
	}
	
	public void test1p1()throws Exception {
		SliceLexer lexer = new SliceLexer(slice5);		
		SliceParser parser = new SliceParser(lexer);
		parser.setASTNodeClass(Tree.class.getCanonicalName());
	    parser.slice();
	    AST tree = parser.getAST();
	    assertTrue(tree.toStringList().equals(" ( finterfaceInvoke fmethodRef ( finterfaceInvoke fmethodRef ( finterfaceInvoke fmethodRef ( finstanceFieldRef fthisRef ffieldRef ) ) fnull fnull fparameterRef fnull ) fstringConstant )"));
	}
	
	public void test1p2()throws Exception {
		SliceLexer lexer = new SliceLexer(slice6);		
		SliceParser parser = new SliceParser(lexer);
		parser.setASTNodeClass(Tree.class.getCanonicalName());
	    parser.slice();
	    AST tree = parser.getAST();
	    super.assertEquals(" D", tree.toStringList());
	    
	}
	
	public void test1p3()throws Exception {
		SliceLexer lexer = new SliceLexer(slice7);		
		SliceParser parser = new SliceParser(lexer);
		parser.setASTNodeClass(Tree.class.getCanonicalName());
	    parser.slice();
	    AST tree = parser.getAST();
	    System.out.println(tree.toStringList());
	    //super.assertEquals(" D", tree.toStringList());
	    
	}
	
	public void test2() throws Exception {	
		SliceLexer lexer = new SliceLexer(slice2);		
		SliceParser parser = new SliceParser(lexer);
		parser.setASTNodeClass(Tree.class.getCanonicalName());
	    parser.slice();
	    AST tree = parser.getAST();
	    assertTrue(tree.toStringList().equals(" ( fvirtualInvokeExpr fmethodRef )"));
	}
	public void test3() throws Exception {	
		SliceLexer lexer = new SliceLexer(slice3);		
		SliceParser parser = new SliceParser(lexer);
		parser.setASTNodeClass(Tree.class.getCanonicalName());
	    parser.slice();
	    AST tree = parser.getAST();
	    assertTrue(tree.toStringList().equals(" ( one dos )"));
	}
	
	public void test4() throws Exception {	
		SliceLexer lexer = new SliceLexer(slice4);		
		SliceParser parser = new SliceParser(lexer);
		parser.setASTNodeClass(Tree.class.getCanonicalName());
	    parser.slice();
	    Tree tree = (Tree)parser.getAST();
	    assertTrue(tree != null && tree.toStringList().equals(" ( float -4 )"));
	    System.out.println(tree.prettyPrint());
	}
	
	public void test5() throws Exception {
		Tree a = TreeFactory.createSliceAST("a()");
		assertTrue( a != null);
		assertTrue(a.getText().equals("a"));
	}
	
	// TODO fix the parenthesis!!!
		
	public void test6() {
		boolean ok = false;
		try{
			SliceLexer lexer = new SliceLexer(new StringReader("a(("));		
			SliceParser parser = new SliceParser(lexer);
			parser.setASTNodeClass(Tree.class.getCanonicalName());
		    parser.slice();
		    AST tree = parser.getAST();
		    
		}
		catch (RecognitionException e)
		{
		    ok = true;
		}
		catch (TokenStreamException e)
		{
		    ok = true;
		}
		assertTrue(ok);
	}
	
/** parenthesis balance test **/
	// TODO fix this example
	/*
	public void test7() {
		boolean ok = false;
		try{
			SliceLexer lexer = new SliceLexer(new StringReader("a(b))"));		
			SliceParser parser = new SliceParser(lexer);
			parser.setASTNodeClass("furia.slice.SliceAST");
		    parser.slice();
		    AST tree = parser.getAST();
		   
		}
		catch (RecognitionException e)
		{
		    ok = true;
		}
		catch (TokenStreamException e)
		{
		    ok = true;
		}
		assertTrue(ok);
	}
	*/
	
	

}
