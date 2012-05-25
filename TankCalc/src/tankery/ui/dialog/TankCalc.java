package tankery.ui.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.Timer;

public class TankCalc {
	
	static JFrame frame = new JFrame();

	public TankCalc(String string) {
		frame.setTitle(string);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addDataTime();
		frame.getContentPane().add(new CalcPanel());
		
		frame.pack();
		frame.setVisible(true);
	}

	private void addDataTime() {
		JMenuBar menubar = new JMenuBar();
		Date now = new Date();
		final DateFormat d = DateFormat.getDateTimeInstance(); // Show data and time.

		final JMenu menu = new JMenu(d.format(now));
		menubar.add(menu);
		menu.setEnabled(false);
		new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menu.setText(d.format(new Date()));
			}
		}).start();
		frame.setJMenuBar(menubar);
	}
}
