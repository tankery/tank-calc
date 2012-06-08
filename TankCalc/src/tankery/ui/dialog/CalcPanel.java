package tankery.ui.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import tankery.expr.interpret.ExprInterpreter;
import tankery.expr.tree.Expr;

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
		processBtnValue(e.getActionCommand());
	}
	
	private void processBtnValue(String val) {
		switch (CalcButtonPanel.getButtonType(val)) {
		case NUM_BUTTON:
			screenPanel.addNumText(val);
			break;
		case OPT_BUTTON:
			processOperator(val);
			break;
		case FUN_BUTTON:
			if (val.equals("←")) {
				screenPanel.delNumText();
			}
			else if (val.equals("CE")) {
				screenPanel.clearNumText();
			}
			else if (val.equals("C")) {
				screenPanel.clearExprText();
				screenPanel.clearNumText();
			}
			break;
		case MEM_BUTTON:
			break;
		default:
			break;
		}
	}

	private void processOperator(String val) {
		screenPanel.addExprText(val);
		if (val.equals("=")) {
			String expr = screenPanel.getExprText();
			ExprInterpreter interpreter = new ExprInterpreter(expr);
			Expr t = interpreter.generateExprTree();
			screenPanel.showNum(t.evaluation());
		}
	}
}
