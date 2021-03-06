/**
 * 
 */
package tankery.expr.tree;

import tankery.expr.exception.ExprCreateException;
import tankery.expr.exception.ExprError;

/**
 * @author tankery
 *
 */
public class Expr implements ExprNode {

	/* (non-Javadoc)
	 * @see tankery.expr.tree.ExprNode#exprString()
	 */
	private ExprNode exprNode;
	
	public Expr(double n) {
		exprNode = new NumberNode(n);
	}
	public Expr(String numStr) throws ExprError {
		double n = 0;
		try {
			n = Double.parseDouble(numStr);
		}
		catch (NumberFormatException e) {
			throw new ExprCreateException();
		}
		exprNode = new NumberNode(n);
	}
	public Expr(String op, Expr nd) {
		exprNode = new UnaryNode(op, nd);
	}
	public Expr(String op, Expr ln, Expr rn) {
		exprNode = (ln == null) ?
				new UnaryNode(op, rn) : new BinaryNode(op, ln, rn);
	}
	public Expr(ExprNode nd) {
		exprNode = nd;
	}
	public Expr(Expr nd) {
		exprNode = nd.exprNode;
	}
	
	@Override
	public String exprString() {
		return exprNode.exprString();
	}
	@Override
	public double evaluation() {
		try {
			return exprNode.evaluation();
		} catch (ExprError e) {
			e.printStackTrace();
		}
		
		return 0;
	}

}
