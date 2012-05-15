package tankery.test.expr;

import tankery.expr.tree.Expr;

public final class ExprTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Expr t = new Expr("/", new Expr("-", new Expr(5)), new Expr("-", new Expr(4), new Expr(4)));
		System.out.println(t.exprString() + " = " + t.evaluation());
		
		return;
	}

}
