package newRacing;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class Application extends JFrame {

	private static final long serialVersionUID = -1824092949935612488L;
	private final GraphicsDevice device;
	private final Dimension dim;

	public Application(final GraphicsDevice device, final Dimension dim) {
		super(device.getDefaultConfiguration());
		this.device = device;
		this.dim = dim;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void begin() {
		final boolean isFullScreen = device.isFullScreenSupported();
		setUndecorated(isFullScreen);
		setResizable(!isFullScreen);
		if (isFullScreen) {
			// Full-screen mode
			device.setFullScreenWindow(this);
			validate();
		} else {
			// Windowed mode
			pack();
			setVisible(true);
		}
	}

	private void initComponents() {
		add(new Racing(dim));
	}

	public static void main(final String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension dim = toolkit.getScreenSize();
		System.out.println("Width of Screen Size is " + dim.width + " pixels");
		System.out
				.println("Height of Screen Size is " + dim.height + " pixels");

		final GraphicsEnvironment env = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		final GraphicsDevice defaultScreen = env.getDefaultScreenDevice();
		System.out.println("isFullScreenSupported: "
				+ defaultScreen.isFullScreenSupported());

		new Thread() {
			@Override
			public void run() {
				setPriority(MAX_PRIORITY);
				final Application window = new Application(defaultScreen, dim);
				window.initComponents();
				window.begin();
			}
		}.start();
	}
}
