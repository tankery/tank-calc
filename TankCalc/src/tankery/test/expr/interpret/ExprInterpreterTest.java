package tankery.test.expr.interpret;

import static org.junit.Assert.*;

import org.junit.Test;

import tankery.expr.interpret.ExprInterpreter;
import tankery.expr.tree.Expr;

public class ExprInterpreterTest {

	@Test
	public void testExprInterpreter() {
		String srcExpr = "(-5 / (5  - sqrt(4.0)))";
		String dstExpr = "((-5) / (5 - 4.0))";
		ExprInterpreter interpreter = new ExprInterpreter(srcExpr);
		assertEquals(dstExpr, interpreter.getExpression());
	}

	@Test
	public void testGenerateExprTree() {
		ExprInterpreter interpreter = new ExprInterpreter("((-5)/(5.0 - 4.0))");
		Expr srcExpr = interpreter.generateExprTree();
		Expr dstExpr = new Expr("/", new Expr("-", new Expr(5)), new Expr("-", new Expr(5), new Expr(4)));
		assertEquals(dstExpr.exprString(), srcExpr.exprString());
		assertEquals(dstExpr.evaluation(), srcExpr.evaluation(), 0.0);
	}

}
