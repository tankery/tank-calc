package tankery.expr.tree;

import tankery.expr.exception.ExprError;
import tankery.expr.exception.ExprOperatorError;


public class UnaryNode implements ExprNode {
	
	String operator;
	Expr opNode;
	
	protected UnaryNode(String op, Expr nd) {
		operator = op;
		opNode = nd;
	}

	@Override
	public String exprString() {
		return "(" + operator + opNode.exprString() + ")";
	}

	public double evaluation() throws ExprError {
		if (operator.equals("-")) {
			return -opNode.evaluation();
		}
		else if (operator.equals("√")) {
			return Math.sqrt(opNode.evaluation());
		}
		
		throw new ExprOperatorError(operator, this.toString());
	}

}
