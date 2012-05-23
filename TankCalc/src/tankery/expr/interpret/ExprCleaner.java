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
		expr = invalidRemove(expr);
		expr = braceCompletion(expr);
		return expr;
	}

	private String invalidRemove(String expr) {
		expr = expr.replaceAll(ExprValidation.noneValidCharacter(), "");
		return expr;
	}
	
	private String braceCompletion(String expr) {
		int braceLevel = 0;
		
		for (int i = 0; i < expr.length(); i++) {
			switch (expr.charAt(i)) {
			case '(':
				braceLevel++;
				break;
			case ')':
				braceLevel--;
				break;
			default:
				break;
			}
		}
		
		expr = braceLevel > 0 ?
				expr + dummyString(')', braceLevel) :
				dummyString('(', -braceLevel) + expr;
		
		return expr;
	}
	
	private static String dummyString(char c, int len) {
		StringBuffer outputBuffer = new StringBuffer(len);
		for (int i = 0; i < len; i++){
		   outputBuffer.append(c);
		}
		return outputBuffer.toString();
	}
	
}
