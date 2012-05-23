package tankery.test.expr.tree;

import tankery.expr.exception.ExprError;
import tankery.expr.tree.ExprNode;

public class FakeExprNode implements ExprNode {

	@Override
	public String exprString() {
		return "4.0";
	}

	@Override
	public double evaluation() throws ExprError {
		return 4;
	}

}
