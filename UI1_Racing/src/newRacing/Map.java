package newRacing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
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

	private BufferedImage track;
	private BufferedImage texture;
	private BufferedImage[] checkpt;
	private BufferedImage wall;
	private BufferedImage grass;

	private int checkpointsCount;

	public Map(final String trackName, final Point start) {
		startPoint = start;
		x = start.x;
		y = start.y;

		racingTrack = trackName;

		try {
			track = ImageIO.read(new File("data/tracks/" + racingTrack
					+ "/track.png"));
			texture = ImageIO.read(new File("data/tracks/" + racingTrack
					+ "/texture.png"));
			wall = ImageIO.read(new File("data/tracks/" + racingTrack
					+ "/wall.png"));
			grass = ImageIO.read(new File("data/tracks/" + racingTrack
					+ "/grass.png"));
		} catch (final IOException e) {
			e.printStackTrace();
		}

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

		g2d.drawImage(grass, 0, 0, null);
		g2d.drawImage(wall, 0, 0, null);
		g2d.drawImage(track, 0, 0, null);
		g2d.drawImage(texture, 0, 0, null);
	}

	public Color getColorAtPoint(final Point p) {
		return new Color(track.getRGB(p.x, p.y));
	}

	public void moveTo(final double xCoordinate, final double yCoordinate) {
		x = xCoordinate;
		y = yCoordinate;
	}
}
