package newRacing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public final class RaceDrawing extends JPanel implements ActionListener {

	private static final long serialVersionUID = -7384091513718156217L;

	private static final int PLAYER_MUTE = 0;
	private static final int PLAYER_PREV = 1;
	private static final int PLAYER_PLAY = 2;
	private static final int PLAYER_NEXT = 3;

	private final Car raceCar;
	private final Map map;
	private final Dimension screenSize;
	private final MusicPlayer musicPlayer;

	private final Rectangle[] buttons;
	private final BufferedImage[] buttonIcons;
	private final Rectangle buttonArea;

	private BufferedImage bi_mute;
	private BufferedImage bi_noMute;
	private BufferedImage bi_prev;
	private BufferedImage bi_play;
	private BufferedImage bi_pause;
	private BufferedImage bi_next;

	public RaceDrawing(final Car c, final Map m, final Dimension dim,
			final MusicPlayer mp) {
		raceCar = c;
		map = m;
		screenSize = dim;
		musicPlayer = mp;

		buttonIcons = new BufferedImage[4];
		buttons = new Rectangle[4];
		buttonArea = new Rectangle(initButtons());

		setPreferredSize(screenSize);

		addMouseListener(new DrawingActionListener());
	}

	private Rectangle initButtons() {
		buttons[PLAYER_MUTE] = new Rectangle(20, 20, 35, 35);
		buttons[PLAYER_PREV] = new Rectangle(65, 20, 35, 35);
		buttons[PLAYER_PLAY] = new Rectangle(110, 20, 35, 35);
		buttons[PLAYER_NEXT] = new Rectangle(155, 20, 35, 35);

		try {
			bi_mute = ImageIO.read(new File("data/icons/mute.png"));
			bi_noMute = ImageIO.read(new File("data/icons/noMute.png"));
			bi_prev = ImageIO.read(new File("data/icons/prev.png"));
			bi_play = ImageIO.read(new File("data/icons/play.png"));
			bi_pause = ImageIO.read(new File("data/icons/pause.png"));
			bi_next = ImageIO.read(new File("data/icons/next.png"));
		} catch (final IOException e) {
			e.printStackTrace();
		}
		buttonIcons[PLAYER_MUTE] = bi_noMute;
		buttonIcons[PLAYER_PREV] = bi_prev;
		buttonIcons[PLAYER_PLAY] = bi_pause;
		buttonIcons[PLAYER_NEXT] = bi_next;

		return new Rectangle(20, 20, 170, 35);
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		repaint();
	}

	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);

		map.paintComponent(g);
		raceCar.paintComponent(g);
		map.revertGraphicsTranslate();

		for (int i = musicPlayer.isMutable() ? 0 : 1; i < 4; i++) {
			g.drawImage(buttonIcons[i], buttons[i].x, buttons[i].y, null);
		}
	}

	final class DrawingActionListener extends MouseAdapter {
		@Override
		public void mouseClicked(final MouseEvent e) {
			final Point clickPoint = e.getPoint();
			if (!buttonArea.contains(clickPoint)) {
				// nothing to do here
				return;
			}
			if (buttons[PLAYER_MUTE].contains(clickPoint)) {
				if (musicPlayer.isMuted()) {
					musicPlayer.mute(false);
					changeIcon(PLAYER_MUTE, bi_noMute);
				} else {
					musicPlayer.mute(true);
					changeIcon(PLAYER_MUTE, bi_mute);
				}
				return;
			}
			if (buttons[PLAYER_PREV].contains(clickPoint)) {
				musicPlayer.previous();
				return;
			}
			if (buttons[PLAYER_PLAY].contains(clickPoint)) {
				if (musicPlayer.isPaused()) {
					musicPlayer.play();
					changeIcon(PLAYER_PLAY, bi_pause);
				} else {
					musicPlayer.pause();
					changeIcon(PLAYER_PLAY, bi_play);
				}
				return;
			}
			if (buttons[PLAYER_NEXT].contains(clickPoint)) {
				musicPlayer.next();
				return;
			}
		}

		private void changeIcon(final int place, final BufferedImage newIcon) {
			buttonIcons[place] = newIcon;
		}
	}
}