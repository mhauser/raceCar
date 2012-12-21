package newRacing;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Racing implements ActionListener {

	private int[] keys;
	private final Car raceCar;
	private final Map map;
	private final Dimension screenSize;
	private final Point start;
	private final MusicPlayer musicPlayer;
	private final RaceDrawing drawing;

	public Racing(final Dimension dim) {
		screenSize = dim;

		start = new Point(screenSize.width / 2, screenSize.height / 2);
		// TODO start coordinates and angle depending on track

		raceCar = new RacingCar(start, 20.7f);
		map = new Map("mc", start, screenSize);

		registerKeyListener();

		final Timer timer = new Timer(7, this);
		timer.start();

		musicPlayer = new MusicPlayer();
		musicPlayer.play();

		drawing = new RaceDrawing(raceCar, map, screenSize, musicPlayer);
		final Timer drawTimer = new Timer(20, drawing);
		drawTimer.start();
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		keyProcessing();

		validatePosition();
		raceCar.move();
		map.moveTo(raceCar.getXCoordinate(), raceCar.getYCoordinate());
	}

	private void validatePosition() {
		if (!map.isOnMap(raceCar.getPoint())) {
			raceCar.collide();
			return;
		}

		// final int col = map.getRGBAtPoint(raceCar.getPoint());
		// TODO collision handling
	}

	private void registerKeyListener() {
		keys = new int[256];
		Arrays.fill(keys, 0); // 0 = key is up

		final KeyboardFocusManager kfm = KeyboardFocusManager
				.getCurrentKeyboardFocusManager();
		kfm.addKeyEventDispatcher(new KeyEventDispatcher() {
			@Override
			public boolean dispatchKeyEvent(final KeyEvent e) {
				if (e.getKeyCode() >= keys.length) {
					return false;
				}

				switch (e.getID()) {
				case KeyEvent.KEY_PRESSED:
					keys[e.getKeyCode()] = 1;
					break;
				case KeyEvent.KEY_RELEASED:
					keys[e.getKeyCode()] = 0;
					break;
				default:
					break;
				}
				return false;
			}
		});

	}

	private void keyProcessing() {

		if (keys[KeyEvent.VK_ESCAPE] == 1) {
			System.exit(JFrame.EXIT_ON_CLOSE);
		}
		if (keys[KeyEvent.VK_UP] == 1) {
			raceCar.accelerate();
		}
		if (keys[KeyEvent.VK_DOWN] == 1) {
			if (raceCar.getSpeed() > 0) {
				raceCar.activateBreaks();
			} else {
				raceCar.deccelerate();
			}
		}
		if (keys[KeyEvent.VK_RIGHT] == 1) {
			raceCar.turnRight();
		}
		if (keys[KeyEvent.VK_LEFT] == 1) {
			raceCar.turnLeft();
		}

		if (keys[KeyEvent.VK_UP] == 0 && keys[KeyEvent.VK_DOWN] == 0) {
			if (raceCar.getSpeed() >= 0) {
				raceCar.slowdown(0.4);
			} else {
				raceCar.slowdownBackward(0.4);
			}
		}
	}

	public Component getDrawing() {
		return drawing;
	}
}