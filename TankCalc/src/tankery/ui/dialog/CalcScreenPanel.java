package tankery.ui.dialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CalcScreenPanel extends JPanel {

	private static final long serialVersionUID = 3078143779185377010L;
	
	private JTextField exprTextField = new JTextField();
	private JTextField numTextField = new JTextField();

	public CalcScreenPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createEmptyBorder(2, 4, 8, 4));

		exprTextField.setForeground(new Color(255, 255, 0));
		exprTextField.setFont(new Font("Consolas", Font.PLAIN, 12));
		exprTextField.setEditable(false);
		exprTextField.setBackground(SystemColor.textHighlight);
		exprTextField.setBorder(BorderFactory.createEmptyBorder(2, 1, 0, 1));
		exprTextField.setMargin(new Insets(2, 4, 2, 4));
		add(exprTextField);
		
		JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
		add(sep);

		numTextField.setForeground(new Color(255, 255, 0));
		numTextField.setFont(new Font("Consolas", Font.BOLD, 26));
		numTextField.setEditable(false);
		numTextField.setBackground(SystemColor.textHighlight);
		numTextField.setHorizontalAlignment(JTextField.RIGHT);
		numTextField.setBorder(BorderFactory.createEmptyBorder(2, 1, 0, 1));
		numTextField.setMargin(new Insets(2, 4, 2, 4));
		add(numTextField);
		
		normalize();
	}

	public void addExprText(String opt) {
		String expr = exprTextField.getText();
		String num = numTextField.getText();

		if (expr.endsWith("=")) {
			// new calculation.
			if (opt.equals("=")) {
				// do nothing.
				return;
			}
			else {
				expr = "";
			}
		}
		
		if (opt.equals("±")) {
			num = num.startsWith("-") ? num.substring(1) : "-" + num;
			numTextField.setText(num);
			return;
		}

		if (leftNeedsNum(opt) &&
				(expr.isEmpty() || !num.equals("0")) ) {
			expr += num;
			num = "0";
		}
		expr += opt;
		
		exprTextField.setText(expr);
		numTextField.setText(num);
		normalize();
	}

	public void clearExprText() {
		exprTextField.setText("");
		normalize();
	}
	
	public String getExprText() {
		String expr = exprTextField.getText();
		while (expr.endsWith("=")) {
			expr = expr.substring(0, expr.length() - 1);
		}
		return expr;
	}
	
	public void addNumText(String text) {
		String num = numTextField.getText();
		if (exprTextField.getText().endsWith("=")) {
			// new calculation.
			clearExprText();
			num = "";
		}
		
		// only allow one '.' in number.
		if (text.equals(".") && num.split("\\.").length > 1) {
			return;
		}
		num += text;
		numTextField.setText(num);
		normalize();
	}
	
	public void delNumText() {
		String text = numTextField.getText();
		if (text.length() > 0) {
			numTextField.setText(text.substring(0, text.length() - 1));
		}
		normalize();
	}
	
	public void clearNumText() {
		numTextField.setText("");
		normalize();
	}
	
	public void showNum(double num) {
		numTextField.setText(Double.toString(num));
		normalize();
	}
	
	private boolean leftNeedsNum(String text) {
		final String binOpt[] = {"+", "-", "*", "/", ")", "="};
		
		return Arrays.asList(binOpt).contains(text);
	}
	
	private void normalize() {
		String num = numTextField.getText();
		boolean isNavigate = num.startsWith("-");
		
		if (isNavigate) {
			num = num.substring(1);
		}
		
		while (num.startsWith("0")) {
			num = num.substring(1);
		}
		
		if (num.length() == 0) {
			num = "0";
		}
		if (num.startsWith(".")) {
			num = "0" + num;
		}
		
		if (isNavigate) {
			num = "-" + num;
		}
		numTextField.setText(num);
	}
}