package tankery.expr.exception;

public class ExprError extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1727953718708142407L;
	
	public ExprError(String msg) {
		super(msg);
	}
	
	public String getMessage() {
		return "Expression error: " + super.getMessage();
	}

}
