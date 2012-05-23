package tankery.test.expr.interpret;

import tankery.expr.interpret.ExprBetter;

public class FakeExprCleaner implements ExprBetter {
	
	private String expression = "";
	
	public FakeExprCleaner(String expr) {
		expression = expr;
	}

	@Override
	public String getResult() {
		return expression;
	}

}
