package tankery.ui.dialog;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JPanel;

public class CalcButtonPanel extends JPanel {

	public enum ButtonType {
		NUM_BUTTON,
		OPT_BUTTON,
		FUN_BUTTON,
		MEM_BUTTON,
	}
	
	private static final long serialVersionUID = -2046447342746886052L;
	
	private String buttonValue;
	private ActionListener eventListener;
	
	final static String memString[] = {
			"MC", "MR", "MS", "M+", "M-"
	};
	final static String funString[] = {
			"←", "CE", "C"
	};
	final static String optString[] = {
			"±", "/", "*", "-", "+", "√", "(", ")", "="
	};

	public CalcButtonPanel(ActionListener listener) {
		eventListener = listener;
		createButtonPanel();
	}

	public String getButtonValue() {
		return buttonValue;
	}

	private void createButtonPanel() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//maximum height, maximum width
		c.fill = GridBagConstraints.BOTH;
		// distributing buttons with space (insets).
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.insets = new Insets(2, 4, 2, 4);

		JButton button;

		for (int x = 0; x < memString.length; x++) {
			button = createMemButton(memString[x]);
			c.gridx = x;
			c.gridy = 0;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.ipadx = 5;
			add(button, c);
		}
		for (int x = 0; x < funString.length; x++) {
			button = createButton(funString[x]);
			c.gridx = x;
			c.gridy = 1;
			c.gridwidth = 1;
			c.gridheight = 1;
			add(button, c);
		}
		for (int x = 0; x < 2; x++) {
			for (int y = 0; y < 5; y++) {
				int i = x*5 + y;
				
				c.gridx = x + 3;
				c.gridy = y + 1;
				c.gridwidth = 1;
				c.gridheight = 1;
				
				// if the last one.
				if (i == (optString.length - 1)) {
					c.gridheight = 5 - y; // span it.
					x = 2; y = 5; // quit after this turn.
				}
				button = createButton(optString[i]);
				add(button, c);
			}
		}
		
		Component numBtns = createNumButtonPanel();
		c.insets = new Insets(0, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		c.gridheight = 4;
		add(numBtns, c);
	}
	
	private Component createNumButtonPanel() {
		JPanel pane = new JPanel();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//maximum height, maximum width
		c.fill = GridBagConstraints.BOTH;
		// distributing buttons.
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
		btn.addActionListener(eventListener);
		return btn;
	}
	
	public static ButtonType getButtonType(String btn) {
		if (Arrays.asList(memString).contains(btn)) {
			return ButtonType.MEM_BUTTON;
		}
		else if (Arrays.asList(funString).contains(btn)) {
			return ButtonType.FUN_BUTTON;
		}
		else if (Arrays.asList(optString).contains(btn)) {
			return ButtonType.OPT_BUTTON;
		}
		else {
			return ButtonType.NUM_BUTTON;
		}
	}
}
