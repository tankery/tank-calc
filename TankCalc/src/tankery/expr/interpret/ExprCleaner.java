package tankery.expr.interpret;

public class ExprCleaner {
	private String expression = "";

	public ExprCleaner(String expr) {
		expression = clean(expr);
	}
	
	public String getResult() {
		return expression;
	}

	private String clean(String expr) {
		expr = expr.replaceAll(ExprValidation.noneValidCharacter(), "");
		return expr;
	}
	
}
