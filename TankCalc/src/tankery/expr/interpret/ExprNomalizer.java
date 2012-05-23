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
		expr = braceNormalize(expr);
		expr = spaceNormalize(expr);
		return expr;
	}
	
	private String spaceNormalize(String expr) {
		// first, remove all space.
		expr = expr.replaceAll("\\s+", "");
		
		// create an pattern to match string like "3*5", ")/2", etc.
		String exprPattern =
				"([\\)\\d]+?)(" +
				ExprValidation.validOperator() +
				")([\\(\\d]+?)";
		
		// adding space between number & operator.
		// replace twice to avoid continue number-operator-number group,
		// like "3+4-5" and etc.
		expr = expr.replaceAll(exprPattern, "$1 $2 $3");
		expr = expr.replaceAll(exprPattern, "$1 $2 $3");
		
		
		return expr;
	}
	
	private String braceNormalize(String expr) {
		// remove brace around pure number.
		expr = expr.replaceAll("\\((" + ExprValidation.validNumber() + "+)\\)", "$1");
		
		// adding brace around negative number.
		// '(' and ')' can not appear together around negative number.
		expr = expr.replaceAll(
				"(^\\s*|\\(\\s*|" + ExprValidation.validOperator() + "\\s*)" +
				"(-" + ExprValidation.validNumber() + "+)" +
				"(\\s*$|\\s*" + ExprValidation.validOperator() + ")",
				"$1($2)$3");
		expr = expr.replaceAll(
				"(^\\s*|" + ExprValidation.validOperator() + "\\s*)" +
				"(-" + ExprValidation.validNumber() + "+)" +
				"(\\s*$|\\s*\\)|\\s*" + ExprValidation.validOperator() + ")",
				"$1($2)$3");
		
		// TODO: adding brace around single operation like "3 + 4", "3 - 4 / 2", etc.
		// first adding brace around '*', '/'.
		
		return expr;
	}

}
