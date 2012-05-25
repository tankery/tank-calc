package tankery.ui.console;

import java.util.Scanner;

import tankery.expr.interpret.ExprInterpreter;
import tankery.expr.tree.Expr;

public class ConsoleCalc {
	
	public int run() {
		int exitCode = ExitCode.DontExit;

		Scanner inputScanner = new Scanner(System.in);
		
		while (ExitCode.notExit(exitCode)) {
			String input = getUserInput(inputScanner);
			
			if (input.isEmpty()) {
				outputln("Please input the Expression...");
			}
			else if (UserCommands.userWhatsQuit(input)) {
				exitCode = ExitCode.ExitNormal;
			}
			else {
				exitCode = procExpr(input);
			}
			
			outputln("");
		}

		inputScanner.close();
		
		return 0;
	}

	private int procExpr(String input) {
		ExprInterpreter interpreter = new ExprInterpreter(input);
		Expr t = interpreter.generateExprTree();
		outputExprResult(t);
		
		return 1;
	}

	private void outputExprResult(Expr t) {
		if (t == null) {
			outputln("expression error...");
		}
		else {
			outputln(t.exprString() + " = " + t.evaluation());
		}
	}

	private String getUserInput(Scanner in) {
		String input = in.nextLine();
		return input;
	}

	private void outputln(String s) {
		System.out.println(s);
	}

}
