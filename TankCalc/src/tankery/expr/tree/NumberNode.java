package tankery.expr.tree;


public class NumberNode implements ExprNode {

	private double number;
	
	protected NumberNode(double n) {
		number = n;
	}
	
	@Override
	public String exprString() {
		return String.valueOf(number);
	}

	public double evaluation() {
		return number;
	}

}
