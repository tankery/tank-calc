package tankery.expr.tree;

import tankery.expr.exception.ExprError;
import tankery.expr.exception.ExprOperatorError;

public class BinaryNode implements ExprNode {
	
	private String operator;
	private Expr left;
	private Expr right;
	
	protected BinaryNode(String op, Expr ln, Expr rn) {
		operator = op;
		left = ln;
		right = rn;
	}

	@Override
	public String exprString() {
		return "(" + left.exprString() + " " + operator + " " + right.exprString() + ")";
	}

	public double evaluation() throws ExprError {
		double evalLeft = left.evaluation();
		double evalRight = right.evaluation();
		
		if (operator.equals("+")) {
			return evalLeft + evalRight;
		}
		else if (operator.equals("-")) {
			return evalLeft - evalRight;
		}
		else if (operator.equals("*")) {
			return evalLeft * evalRight;
		}
		else if (operator.equals("/")) {
			return evalLeft / evalRight;
		}
		
		throw new ExprOperatorError(operator, this.toString());
	}

}
