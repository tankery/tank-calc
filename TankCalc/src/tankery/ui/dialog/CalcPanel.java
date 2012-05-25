package tankery.ui.dialog;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.SystemColor;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalcPanel extends JPanel {

	private static final long serialVersionUID = -2429272450894693426L;

	public CalcPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		Component screenPanel = createScreenPanel();
		Component buttonPanel = createButtonPanel();
		
		add(screenPanel);
		add(buttonPanel);
	}

	private Component createScreenPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JTextField exprText = new JTextField();
		exprText.setForeground(new Color(255, 255, 0));
		exprText.setFont(new Font("Consolas", Font.PLAIN, 12));
		exprText.setEditable(false);
		exprText.setBackground(SystemColor.textHighlight);
		exprText.setBorder(BorderFactory.createEmptyBorder());
		panel.add(exprText);

		JTextField numText = new JTextField();
		numText.setForeground(new Color(255, 255, 0));
		numText.setFont(new Font("Consolas", Font.BOLD, 26));
		numText.setEditable(false);
		numText.setBackground(SystemColor.textHighlight);
		numText.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		exprText.setBorder(BorderFactory.createEmptyBorder());
		panel.add(numText);
		
		return panel;
	}

	private Component createButtonPanel() {
		
		JButton button;
		JPanel pane = new JPanel();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//natural height, maximum width
		c.fill = GridBagConstraints.HORIZONTAL;

		button = new JButton("Button 1");
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(button, c);

		button = new JButton("Button 2");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		pane.add(button, c);

		button = new JButton("Button 3");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 0;
		pane.add(button, c);

		button = new JButton("Long-Named Button 4");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40;      //make this component tall
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		pane.add(button, c);

		button = new JButton("5");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;       //reset to default
		c.gridx = 1;       //aligned with button 2
		c.gridwidth = 2;   //2 columns wide
		c.gridy = 2;       //third row
		pane.add(button, c);

		return pane;
	}

}
