package newRacing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

public class RacingCar extends Car {

	private static final long serialVersionUID = 386056963794072270L;

	public RacingCar(final Point start, final float angleInDeg) {
		super(start, angleInDeg);
	}

	@Override
	public double getAcceleration() {
		return 0.035;
	}

	@Override
	public double getAgility() {
		return 1.2;
	}

	@Override
	public double getMaxForwardSpeed() {
		return 8;
	}

	@Override
	public double getMaxBackwardSpeed() {
		return -2;
	}

	@Override
	protected void paintComponent(final Graphics g) {
		final Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.translate(x, y);
		g2d.rotate(angle);

		g2d.setPaint(Color.red);
		g2d.fillRect(-15, -20, 30, 40);
		g2d.setPaint(new Color(0x727272));
		g2d.fillRect(-20, -18, 5, 10);
		g2d.fillRect(15, -18, 5, 10);
		g2d.fillRect(-20, 8, 5, 10);
		g2d.fillRect(15, 8, 5, 10);

		g2d.setPaint(Color.yellow);
		g2d.fillRect(-12, 10, 24, 5);

		g2d.setPaint(new Color(0xBFD7FF));
		g2d.fillPolygon(new int[] { 0, -10, 10 }, new int[] { -17, 5, 5 }, 3);
	}

	// @Override
	// public List<Dimension> getCollisionModel() {
	// // TODO Auto-generated method stub
	// return null;
	// }

}
