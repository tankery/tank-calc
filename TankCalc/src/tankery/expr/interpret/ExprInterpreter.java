package tankery.expr.interpret;

import java.util.Vector;

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
		// remove the redundant brace.
		exprStr = removeRedundantBrace(exprStr);
		
		// empty node.
		if (exprStr.isEmpty())
			return null;
		
		// pure number
		if (exprStr.matches(ExprValidation.validNumber() + "+")) {
			return new Expr(exprStr);
		}
		
		// generate the expression vector.
		Vector<String> exprs = new Vector<String>(0);
		Vector<String> operators = new Vector<String>(0);
		int level = 0;
		int exprIndex = 0;
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
				operators.add("" + c);
				exprIndex++;
			}
			else {
				while (exprIndex > exprs.size()) {
					exprs.add("");
				}
				
				if (exprIndex == exprs.size()) {
					exprs.add("" + c);
				}
				else {
					exprs.set(exprIndex, exprs.elementAt(exprIndex) + c);
				}
			}
		}
		
		for (int i = 0; i < operators.size(); i++) {
			if (operators.elementAt(i).trim().isEmpty()) {
				throw new ExprError("\'" + exprStr + "\' is not valid by unknow reason.");
			}
		}
		if (exprs.size() - operators.size() != 1) {
			throw new ExprError("\'" + exprStr + "\' is not valid by unknow reason.");
		}
		
		
		return exprVecotr2Tree(exprs, operators);
	}

	private boolean isOperator(char c) {
		String s = "";
		s += c;
		
		return isOperator(s);
	}
	
	private boolean isOperator(String s) {
		return s.matches(ExprValidation.validOperator());
	}
	
	private int findHighestOperator(Vector<String> ops) {
		if (ops == null) {
			return -1;
		}
		
		// if contains "√", return it.
		for (int i = 0; i < ops.size(); i++) {
			if (ops.elementAt(i).equals("√")) {
				return i;
			}
		}
		
		// if contains "*" or "/", return it.
		for (int i = 0; i < ops.size(); i++) {
			if (ops.elementAt(i).equals("*") || ops.elementAt(i).equals("/")) {
				return i;
			}
		}

		return 0;
	}

	private String removeRedundantBrace(String exprStr) {
		for (int i = 0, level = 0; i < exprStr.length(); i++) {
			char c = exprStr.charAt(i);
			
			// the first character is not '(' means no '()' surrounding expression.
			if (i == 0 && c != '(') {
				break;
			}
			
			if (c == '(') {
				level++;
			}
			else if (c == ')') {
				level--;
				if (level == 0) {
					if (i == exprStr.length() - 1) {
						exprStr = exprStr.substring(1, i);
						// phase again to avoid nested brace.
						i = 0;
					}
					else {
						// redundant brace already removed.
						break;
					}
				}
			}
		}
		return exprStr;
	}

	private Expr exprVecotr2Tree(Vector<String> exprs, Vector<String> operators)
			throws ExprError {
		
		Vector<Expr> exprTrees = new Vector<Expr>();
		for (int i = 0; i < exprs.size(); i++) {
			exprTrees.add(i, exprStr2Tree(exprs.elementAt(i)));
		}
		
	
		while (operators.size() > 0) {
			int opIndex;
			Expr exprTree = null;
			
			opIndex = findHighestOperator(operators);
			exprTree = new Expr(operators.elementAt(opIndex),
					exprTrees.elementAt(opIndex),
					exprTrees.elementAt(opIndex + 1));
			operators.remove(opIndex);
			exprTrees.remove(opIndex + 1);
			exprTrees.set(opIndex, exprTree);
		}
		
		if (exprTrees.size() != 1) {
			throw new ExprError("Generation of expression tree failed by unknow reason.");
		}
		
		// recursion calling this function to create an expression tree.
		return exprTrees.firstElement();
	}

}
