package racing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

public class RacingCar extends Car {

	private static final long serialVersionUID = 386056963794072270L;

	@Override
	public double getAcceleration() {
		return 0.1;
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
	public List<Dimension> getCollisionModel() {
		final List<Dimension> collisionModel = new ArrayList<>();

		collisionModel.add(calculateMatrix(x - 20, y + 20));
		collisionModel.add(calculateMatrix(x + 20, y + 20));
		collisionModel.add(calculateMatrix(x - 20, y - 20));
		collisionModel.add(calculateMatrix(x + 20, y - 20));
		collisionModel.add(calculateMatrix(x, y + 20));
		collisionModel.add(calculateMatrix(x, y - 20));
		collisionModel.add(calculateMatrix(x + 20, y));
		collisionModel.add(calculateMatrix(x - 20, y));
		collisionModel.add(calculateMatrix(x, y));
		return collisionModel;
	}

	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);
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

}
