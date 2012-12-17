package newRacing;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class Application {

	/**
	 * @param args
	 */
	public static void main(final String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension dim = toolkit.getScreenSize();
		System.out.println("Width of Screen Size is " + dim.width + " pixels");
		System.out
				.println("Height of Screen Size is " + dim.height + " pixels");

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					final JFrame f = new JFrame("Racing");
					f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					f.add(new Racing(dim));
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
