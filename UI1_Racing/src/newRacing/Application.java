package newRacing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class Application {

	/**
	 * @param args
	 */
	public static void main(final String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					final JFrame f = new JFrame("Racing");
					f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					f.add(new Racing());
					f.setResizable(false);
					f.pack();
					f.setVisible(true);
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
