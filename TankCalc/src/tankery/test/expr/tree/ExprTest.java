package tankery.test.expr.tree;

import static org.junit.Assert.*;

import org.junit.Test;

import tankery.expr.exception.ExprError;
import tankery.expr.tree.Expr;

public class ExprTest {

	@Test
	public void testExprDouble() {
		Expr expr = new Expr(5);
		assertEquals("5.0", expr.exprString());
		assertEquals(5.0, expr.evaluation(), 0.0);
	}

	@Test
	public void testExprString() {
		Expr expr;
		try {
			expr = new Expr("5");
		} catch (ExprError e) {
			fail("Expression not created");
			return;
		}
		
		assertEquals("5.0", expr.exprString());
		assertEquals(5.0, expr.evaluation(), 0.0);
	}

	@Test
	public void testExprStringExpr() {
		Expr expr = new Expr("-", new Expr(5));
		
		assertEquals("(-5.0)", expr.exprString());
		assertEquals(-5.0, expr.evaluation(), 0.0);
	}

	@Test
	public void testExprStringExprExpr() {
		Expr expr = new Expr("*", new Expr(5), new Expr(3));
		
		assertEquals("(5.0 * 3.0)", expr.exprString());
		assertEquals(15.0, expr.evaluation(), 0.0);
	}

	@Test
	public void testExprExprNode() {
		Expr expr = new Expr(new FakeExprNode());
		
		assertEquals("4.0", expr.exprString());
		assertEquals(4.0, expr.evaluation(), 0.0);
	}

	@Test
	public void testExprExpr() {
		Expr expr = new Expr(new Expr(5));
		
		assertEquals("5.0", expr.exprString());
		assertEquals(5.0, expr.evaluation(), 0.0);
	}
	
	@Test
	public void testExpr() {
		Expr expr = new Expr("/", new Expr("-", new Expr(5)), new Expr("-", new Expr(4), new Expr(4)));
		
		assertEquals("((-5.0) / (4.0 - 4.0))", expr.exprString());
		assertEquals(Double.NEGATIVE_INFINITY, expr.evaluation(), 0.0);
	}

}
