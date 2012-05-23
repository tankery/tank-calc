package tankery.expr.interpret;

public class ExprNomalizer implements ExprBetter {
	private String expression;
	
	public ExprNomalizer(ExprBetter cleaner) {
		expression = normalize(cleaner.getResult());
	}
	
	/* (non-Javadoc)
	 * @see tankery.expr.interpret.ExprBetter#getResult()
	 */
	@Override
	public String getResult() {
		return expression;
	}
	
	private String normalize(String expr) {
		return expr;
	}

}
