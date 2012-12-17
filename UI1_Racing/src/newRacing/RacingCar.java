package newRacing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class RacingCar extends Car {

	private static final long serialVersionUID = 386056963794072270L;
	private BufferedImage car = null;

	public RacingCar(final Point start, final float angleInDeg) {
		super(start, angleInDeg);
	}

	@Override
	public double getAcceleration() {
		return 0.14;
	}

	@Override
	public double getAgility() {
		return 1.4;
	}

	@Override
	public double getMaxForwardSpeed() {
		return 10;
	}

	@Override
	public double getMaxBackwardSpeed() {
		return -2;
	}

	@Override
	protected void paintComponent(final Graphics g) {
		final Graphics2D g2d = (Graphics2D) g;

		g2d.translate(x, y);
		g2d.rotate(angle);

		if (car == null) {
			createCar();
		}

		g2d.drawImage(car, null, -20, -20);
		g2d.translate(-x, -y);
	}

	private void createCar() {
		car = new BufferedImage(40, 40, BufferedImage.TRANSLUCENT);
		final Graphics2D g2d = car.createGraphics();

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setPaint(Color.red);
		g2d.fillRect(5, 0, 30, 40);
		g2d.setPaint(new Color(0x727272));
		g2d.fillRect(0, 2, 5, 10);
		g2d.fillRect(35, 2, 5, 10);
		g2d.fillRect(0, 28, 5, 10);
		g2d.fillRect(35, 28, 5, 10);

		g2d.setPaint(Color.yellow);
		g2d.fillRect(8, 30, 24, 5);

		g2d.setPaint(new Color(0xBFD7FF));
		g2d.fillPolygon(new int[] { 20, 10, 30 }, new int[] { 3, 25, 25 }, 3);
	}

	// @Override
	// public List<Dimension> getCollisionModel() {
	// // TODO Auto-generated method stub
	// return null;
	// }

}
