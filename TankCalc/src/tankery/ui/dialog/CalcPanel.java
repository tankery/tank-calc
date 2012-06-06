package tankery.ui.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class CalcPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = -2429272450894693426L;
	private CalcScreenPanel screenPanel;
	private CalcButtonPanel buttonPanel;

	public CalcPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		screenPanel = new CalcScreenPanel();
		buttonPanel = new CalcButtonPanel(this);
		
		add(screenPanel);
		add(buttonPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = e.getActionCommand();
		if (name.equals("←")) {
			screenPanel.delNumText();
		}
		else {
			screenPanel.addNumText(name);
		}
	}
}
