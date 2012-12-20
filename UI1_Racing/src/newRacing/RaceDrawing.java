package newRacing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class RaceDrawing extends JPanel implements ActionListener {

	private static final long serialVersionUID = -7384091513718156217L;

	private final Car raceCar;
	private final Map map;
	private final Dimension screenSize;

	public RaceDrawing(final Car c, final Map m, final Dimension dim) {
		raceCar = c;
		map = m;
		screenSize = dim;

		setPreferredSize(screenSize);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent e) {
				// System.out.println(e);
			}
		});
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);

		map.paintComponent(g);
		raceCar.paintComponent(g);
	}

}
