package tankery.expr.interpret;

public class ExprCleaner implements ExprBetter {
	private String expression = "";

	public ExprCleaner(String expr) {
		expression = clean(expr);
	}
	
	/* (non-Javadoc)
	 * @see tankery.expr.interpret.ExprBetter#getResult()
	 */
	@Override
	public String getResult() {
		return expression;
	}

	private String clean(String expr) {
		expr = expr.replaceAll(ExprValidation.noneValidCharacter(), "");
		return expr;
	}
	
}
