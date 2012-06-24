/**
 * 
 */
package tankery.expr.interpret;

/**
 * @author tankery
 *
 */
public final class ExprValidation {
	final static String numbers = "\\d\\.";
	final static String operators = "\\+\\-\\*/√";
	
	public final static String validNumber() {
		return generate(numbers);
	}
	
	public final static String validOperator() {
		return generate(operators);
	}
	
	public final static String validCharacter() {
		return generate(numbers + operators + "\\s\\(\\)");
	}
	
	public final static String noneValidCharacter() {
		return generate("^" + numbers + operators + "\\s\\(\\)");
	}
	
	private final static String generate(String chars) {
		return "[" + chars + "]";
	}

}
