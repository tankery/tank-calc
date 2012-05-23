package tankery.test.expr.interpret;

import static org.junit.Assert.*;

import org.junit.Test;

import tankery.expr.interpret.ExprInterpreter;
import tankery.expr.tree.Expr;

public class ExprInterpreterTest {

	@Test
	public void testExprInterpreter() {
		final String srcExpr[] = {
				"(-5 / (5  - sqrt(4.0)))",
				"1+4/2",
				"1 +((-5) / (5.0 -4.0))",
				};
		final String dstExpr[] = {
				"((-5) / (5 - 4.0))",
				"1 + 4 / 2",
				"1 + ((-5) / (5.0 - 4.0))",
				};
		
		for (int i = 0; i < srcExpr.length; i++) {
			ExprInterpreter interpreter = new ExprInterpreter(srcExpr[i]);
			assertEquals(dstExpr[i], interpreter.getExpression());
		}
	}

	@Test
	public void testGenerateExprTree() {
		ExprInterpreter interpreter = new ExprInterpreter("1 + (((-5)/(5.0 - 4.0 + 1)))");
		Expr srcExpr = interpreter.generateExprTree();
		Expr dstExpr = new Expr("+", new Expr(1), new Expr("/",
				new Expr("-", new Expr(5)),
				new Expr("+",
						new Expr("-", new Expr(5), new Expr(4)),
						new Expr(1))));
		assertEquals(dstExpr.exprString(), srcExpr.exprString());
		assertEquals(dstExpr.evaluation(), srcExpr.evaluation(), 0.0);
	}

}
