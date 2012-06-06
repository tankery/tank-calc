package tankery.ui.dialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;

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
		exprTextField.setBorder(BorderFactory.createEmptyBorder(0, 1, 0, 1));
		exprTextField.setMargin(new Insets(2, 4, 2, 4));
		add(exprTextField);
		
		JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
		add(sep);

		numTextField.setForeground(new Color(255, 255, 0));
		numTextField.setFont(new Font("Consolas", Font.BOLD, 26));
		numTextField.setEditable(false);
		numTextField.setBackground(SystemColor.textHighlight);
		numTextField.setHorizontalAlignment(JTextField.RIGHT);
		numTextField.setBorder(BorderFactory.createEmptyBorder(0, 1, 0, 1));
		numTextField.setMargin(new Insets(2, 4, 2, 4));
		add(numTextField);
		
		normalize();
	}

	public void addExprText(String text) {
		exprTextField.setText(exprTextField.getText() + text);
		normalize();
	}
	
	public void addNumText(String text) {
		String num = numTextField.getText() + text;
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
	
	private void normalize() {
		String num = numTextField.getText();
		while (num.startsWith("0")) {
			num = num.substring(1);
		}
		
		if (num.length() == 0) {
			num = "0";
		}
		
		numTextField.setText(num);
	}
}