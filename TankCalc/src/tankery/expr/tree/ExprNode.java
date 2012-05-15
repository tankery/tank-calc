/**
 * 
 */
package tankery.expr.tree;

import tankery.expr.exception.ExprError;


/**
 * @author tankery
 *
 */
public interface ExprNode {
	String exprString();

	public abstract double evaluation() throws ExprError;
}
