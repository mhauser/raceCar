package newRacing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Map extends JComponent {

	private static final long serialVersionUID = -4735738015518168941L;
	private final String racingTrack;

	private double x;
	private double y;
	private final Point startPoint;
	private final Dimension screenSize;

	final int grassSize;
	final int xTimesGrass;
	final int yTimesGrass;
	final int deltaXGrass;
	final int deltaYGrass;

	private BufferedImage track;
	private BufferedImage texture;
	private BufferedImage[] checkpt;
	private BufferedImage grass;

	private int checkpointsCount;

	public Map(final String trackName, final Point start, final Dimension scSize) {
		System.out.println("selected map: " + trackName);
		startPoint = start;
		screenSize = scSize;
		x = start.x;
		y = start.y;

		racingTrack = trackName;

		try {
			texture = ImageIO.read(new File("data/tracks/" + racingTrack
					+ "/texture.png"));
			grass = ImageIO.read(new File("data/grass.jpg"));

			final double scaleFactor = 1.7;
			final AffineTransform at = new AffineTransform();
			at.scale(scaleFactor, scaleFactor);
			final AffineTransformOp scaleOp = new AffineTransformOp(at,
					AffineTransformOp.TYPE_BILINEAR);
			texture = scaleOp.filter(texture, null);

			track = SVGLoader.getSVGImage("/tracks/" + racingTrack
					+ "/track.svg", texture.getWidth(), texture.getHeight());

			track = createCompatibleImage(track, true);
			texture = createCompatibleImage(texture, true);

		} catch (final IOException e) {
			e.printStackTrace();
		}

		grassSize = grass.getWidth();
		xTimesGrass = track.getWidth() / grassSize + 1;
		yTimesGrass = track.getHeight() / grassSize + 1;
		deltaXGrass = (xTimesGrass * grassSize - track.getWidth()) / 2;
		deltaYGrass = (yTimesGrass * grassSize - track.getHeight()) / 2;

		loadCheckPoints();
	}

	private void loadCheckPoints() {
		final File checkPointDirectory = new File("data/tracks/" + racingTrack
				+ "/checkpoints");
		final String[] chps = checkPointDirectory.list();
		checkpointsCount = chps.length;

		checkpt = new BufferedImage[checkpointsCount];
		for (int i = 0; i < checkpointsCount; i++) {
			try {
				checkpt[i] = ImageIO.read(new File("data/tracks/" + racingTrack
						+ "/checkpoints/" + i + ".png"));
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void paintComponent(final Graphics g) {
		final Graphics2D g2d = (Graphics2D) g;
		g2d.translate(startPoint.x - x, startPoint.y - y);

		for (int i = 0; i < xTimesGrass; i++) {
			for (int j = 0; j < yTimesGrass; j++) {
				g2d.drawImage(grass, -deltaXGrass + i * grassSize, -deltaYGrass
						+ j * grassSize, null);
			}
		}

		g2d.drawImage(track, 0, 0, null);
		g2d.drawImage(texture, 0, 0, null);
	}

	public int getRGBAtPoint(final Point p) {
		return track.getRGB(p.x, p.y);
	}

	public Color getColorAtPoint(final Point p) {
		return new Color(getRGBAtPoint(p));
	}

	public boolean isOnMap(final Point p) {
		if (p.x <= 1 || p.y <= 1) {
			return false;
		}
		if (p.x >= track.getWidth() - 1 || p.y >= track.getHeight() - 1) {
			return false;
		}
		return true;
	}

	public void moveTo(final double xCoordinate, final double yCoordinate) {
		x = xCoordinate;
		y = yCoordinate;
	}

	private static BufferedImage createCompatibleImage(final BufferedImage img,
			final boolean translucent) {
		final GraphicsEnvironment e = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		final GraphicsDevice d = e.getDefaultScreenDevice();
		final GraphicsConfiguration c = d.getDefaultConfiguration();
		final int t = translucent ? Transparency.TRANSLUCENT
				: Transparency.BITMASK;

		final BufferedImage ret = c.createCompatibleImage(img.getWidth(),
				img.getHeight(), t);

		final Graphics2D g = ret.createGraphics();
		g.drawImage(img, 0, 0, null);
		g.dispose();
		return ret;
	}
}
