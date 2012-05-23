package tankery.expr.interpret;

public class ExprNomalizer {
	private String expression;
	
	public ExprNomalizer(ExprCleaner cleaner) {
		expression = normalize(cleaner.getResult());
	}
	
	public String getResult() {
		return expression;
	}
	
	private String normalize(String expr) {
		return expr;
	}

}
