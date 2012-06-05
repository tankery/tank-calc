package tankery.ui.dialog;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CalcPanel extends JPanel {

	private static final long serialVersionUID = -2429272450894693426L;
	private JTextField exprTextField = new JTextField();
	private JTextField numTextField = new JTextField();

	public CalcPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		Component screenPanel = createScreenPanel();
		Component buttonPanel = createButtonPanel();
		
		add(screenPanel);
		add(buttonPanel);
	}

	private Component createScreenPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(2, 4, 8, 4));

		exprTextField.setForeground(new Color(255, 255, 0));
		exprTextField.setFont(new Font("Consolas", Font.PLAIN, 12));
		exprTextField.setEditable(false);
		exprTextField.setBackground(SystemColor.textHighlight);
		exprTextField.setBorder(BorderFactory.createEmptyBorder(0, 1, 0, 1));
		exprTextField.setMargin(new Insets(2, 4, 2, 4));
		panel.add(exprTextField);
		
		JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
		panel.add(sep);

		numTextField.setForeground(new Color(255, 255, 0));
		numTextField.setFont(new Font("Consolas", Font.BOLD, 26));
		numTextField.setEditable(false);
		numTextField.setBackground(SystemColor.textHighlight);
		numTextField.setHorizontalAlignment(JTextField.RIGHT);
		numTextField.setBorder(BorderFactory.createEmptyBorder(0, 1, 0, 1));
		numTextField.setMargin(new Insets(2, 4, 2, 4));
		panel.add(numTextField);
		
		return panel;
	}

	private Component createButtonPanel() {
		
		JPanel pane = new JPanel();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JButton button;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.weighty = 0.5;

		String memString[] = {
				"MC", "MR", "MS", "M+", "M-"
		};
		for (int x = 0; x < memString.length; x++) {
			button = createMemButton(memString[x]);
			c.insets = new Insets(2, 4, 2, 4);
			c.gridx = x;
			c.gridy = 0;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.ipadx = 5;
			pane.add(button, c);
		}
		
		String funString[] = {
				"←", "CE", "C"
		};
		for (int x = 0; x < funString.length; x++) {
			button = createButton(funString[x]);
			c.insets = new Insets(2, 4, 2, 4);
			c.gridx = x;
			c.gridy = 1;
			c.gridwidth = 1;
			c.gridheight = 1;
			pane.add(button, c);
		}
		
		String opString[] = {
				"±", "/", "*", "-", "+", "√", "(", ")"
		};
		for (int x = 0; x < 2; x++) {
			for (int y = 0; y < 5; y++) {
				int i = x*5 + y;
				if (i == opString.length) {
					x = 2;
					y = 5;
					break;
				}
				button = createButton(opString[i]);
				c.insets = new Insets(2, 4, 2, 4);
				c.gridx = x + 3;
				c.gridy = y + 1;
				c.gridwidth = 1;
				c.gridheight = 1;
				pane.add(button, c);
			}
		}

		c.fill = GridBagConstraints.BOTH;
		
		button = createButton("=");
		c.insets = new Insets(2, 4, 2, 4);
		c.gridx = 4;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 2;
		pane.add(button, c);
		
		
		Component numBtns = createNumButtonPanel();
		c.insets = new Insets(0, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		c.gridheight = 4;
		pane.add(numBtns, c);
		
		
		return pane;
	}
	
	private Component createNumButtonPanel() {
		JPanel pane = new JPanel();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//natural height, maximum width
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.weighty = 0.5;
		
		JButton button;
		
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				button = createNumButton(String.valueOf((2 - y)*3 + x + 1));
				c.insets = new Insets(2, 4, 2, 4);
				c.gridx = x;
				c.gridy = y;
				pane.add(button, c);
			}
		}
		
		button = createNumButton("0");
		c.insets = new Insets(2, 4, 2, 4);
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		pane.add(button, c);
		
		button = createNumButton(".");
		c.insets = new Insets(2, 4, 2, 4);
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 1;
		pane.add(button, c);
		
		return pane;
	}

	private JButton createNumButton(final String name) {
		final JButton btn = createButton(name);
		btn.setForeground(Color.RED);
		return btn;
	}

	private JButton createMemButton(final String name) {
		final JButton btn = createButton(name);
		btn.setBackground(new Color(176, 224, 230));
		return btn;
	}

	private JButton createButton(final String name) {
		final JButton btn = new JButton();
		btn.setText(name);
		btn.setMargin(new Insets(2, 4, 2, 4));
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				numTextField.setText(numTextField.getText() + name);
			}
		});
		return btn;
	}

}
