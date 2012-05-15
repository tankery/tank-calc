package tankery.expr.exception;

public class ExprOperatorError extends ExprError {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1809020961306914579L;

	public ExprOperatorError(String op, String obj) {
		super("bad operator \'" + op + "\'" + "in \'" + obj + "\'");
	}

}
