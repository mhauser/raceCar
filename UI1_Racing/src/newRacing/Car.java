package newRacing;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JComponent;

public abstract class Car extends JComponent {

	private static final long serialVersionUID = 4791116509704882431L;
	private static final double SLOW_SPEED = 1;
	private static final float PI = (float) Math.PI;

	protected float angle = 0;
	protected double speed = 0.0;
	protected float condition = 1;

	protected double x = 0;
	protected double y = 0;

	public Car(final Point start, final float angleInDeg) {
		x = start.x;
		y = start.y;
		angle = angleInDeg / 360 * 2 * PI;
	}

	public void move() {
		x = x + Math.sin(angle) * speed;
		y = y - Math.cos(angle) * speed;
	}

	public double getXCoordinate() {
		return x;
	}

	public double getYCoordinate() {
		return y;
	}

	public Point getPoint() {
		return new Point((int) x, (int) y);
	}

	public float getAngle() {
		return angle;
	}

	public void accelerate() {
		if (speed <= getMaxForwardSpeed()) {
			speed += getAcceleration();
		}
	}

	public void deccelerate() {
		if (speed >= getMaxBackwardSpeed()) {
			speed -= 0.1;
		}
	}

	public void slowdown() {
		slowdown(1);
	}

	public void activateBreaks() {
		slowdown(2);
	}

	public void slowdown(final double factor) {
		speed -= 0.2 * factor;
		if (speed < 0) {
			speed = 0;
		}
	}

	public void slowdownBackward(final double factor) {
		speed += 0.2 * factor;
		if (speed > 0) {
			speed = 0;
		}
	}

	public void setSlowSpeed() {
		if (speed > SLOW_SPEED) {
			slowdown(2.8);
		}
	}

	public void setMinSlowSpeed() {
		if (speed < -SLOW_SPEED) {
			speed = -SLOW_SPEED;
		}
	}

	public void stop() {
		speed = 0;
	}

	public void collide() {
		angle = angle + PI;
		x += Math.sin(angle) * speed;
		y -= Math.cos(angle) * speed;

		// TODO better physics xD

		speed *= 0.1;
		condition = (float) (condition - 0.1 * (speed / getMaxForwardSpeed()));
	}

	public void turnRight() {
		angle += Math.PI / (40 / getAgility());
	}

	public void turnLeft() {
		angle -= Math.PI / (40 / getAgility());
	}

	public double getSpeed() {
		return speed;
	}

	// public abstract List<Dimension> getCollisionModel();

	@Override
	protected abstract void paintComponent(final Graphics g);

	public abstract double getAcceleration();

	public abstract double getAgility();

	public abstract double getMaxForwardSpeed();

	public abstract double getMaxBackwardSpeed();
}
