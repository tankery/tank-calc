package tankery.test.expr;

import tankery.expr.interpret.ExprInterpreter;
import tankery.expr.tree.Expr;
import tankery.ui.console.ConsoleCalc;
import tankery.ui.dialog.TankCalc;

public final class ExprTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testExpr();
		testExprInterpreter();
		
		// run console.
		// runExpr();
		
		// run GUI.
		new TankCalc("TankCalc");
		
		return;
	}

	private static void runExpr() {
		ConsoleCalc calc = new ConsoleCalc();
		calc.run();
	}

	private static void testExprInterpreter() {
		System.out.println("------------------ Test ExprInterpreter ----------------");
		ExprInterpreter interpreter = new ExprInterpreter("1 +((-5) / (5.0 -4.0))");
		Expr t = interpreter.generateExprTree();
		System.out.println(t.exprString() + " = " + t.evaluation());
		System.out.println();
	}

	private static void testExpr() {
		System.out.println("----------------------- Test Expr ----------------------");
		Expr t = new Expr("/", new Expr("-", new Expr(5)), new Expr("-", new Expr(4), new Expr(4)));
		System.out.println(t.exprString() + " = " + t.evaluation());
		System.out.println();
	}

}
