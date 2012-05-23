package tankery.expr.interpret;

import tankery.expr.exception.ExprError;
import tankery.expr.tree.Expr;

public class ExprInterpreter {
	
	private String expression = "";
	
	public ExprInterpreter(String expr) {
		ExprCleaner cleaner = new ExprCleaner(expr);
		ExprNomalizer nomalizer = new ExprNomalizer(cleaner);
		expression = nomalizer.getResult();
	}
	
	public Expr generateExprTree() {
		try {
			return exprStr2Tree(expression);
		} catch (ExprError e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getExpression() {
		return expression;
	}

	private Expr exprStr2Tree(String exprStr) throws ExprError {
		// remove the spaces.
		exprStr = exprStr.trim();
		
		if (exprStr.isEmpty())
			return null;
		
		// pure number
		if (exprStr.matches(ExprValidation.validNumber() + "+")) {
			return new Expr(exprStr);
		}
		
		// remove the redundant brace.
		for (int i = 0, level = 0; i < exprStr.length(); i++) {
			char c = exprStr.charAt(i);
			
			if (c == '(') {
				level++;
			}
			else if (c == ')') {
				level--;
				if (level == 0) {
					if (i == exprStr.length() - 1) {
						// using recursion to avoid nested brace.
						return exprStr2Tree(exprStr.substring(1, i));
					}
					else {
						// redundant brace already removed.
						break;
					}
				}
			}
		}
		
		// generate the expression tree.
		String leftExpr = "";
		String rightExpr = "";
		String operator = "";
		int level = 0;
		boolean atLeft = true;
		for (int i = 0; i < exprStr.length(); i++) {
			char c = exprStr.charAt(i);
			
			if (c == '(') {
				level++;
				// don't deal with the first level brace.
				if (level == 1)
					continue;
			}
			else if (c == ')') {
				level--;
				// don't deal with the first level brace.
				if (level == 0)
					continue;
			}
			
			
			if (level == 0 && isOperator(c)) {
				atLeft = false;
				operator += c;
			}
			else if (atLeft) {
				leftExpr += c;
			}
			else {
				rightExpr += c;
			}
		}
		
		if (operator.trim().isEmpty() ||
				rightExpr.trim().isEmpty()) {
				throw new ExprError("\'" + exprStr + "\' is not valid by unknow reason.");
		}
		
		// recursion calling this function to create an expression tree.
		return new Expr(operator, exprStr2Tree(leftExpr), exprStr2Tree(rightExpr));
	}
	
	private boolean isOperator(char c) {
		String s = "";
		s += c;
		
		return isOperator(s);
	}
	
	private boolean isOperator(String s) {
		return s.matches(ExprValidation.validOperator());
	}

}
