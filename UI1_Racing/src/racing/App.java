package racing;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class App {

	private JFrame frame;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;

	/**
	 * this main method is deprecated. use newRacing/Application.main instead.
	 * have fun =)
	 */
	@Deprecated
	public static void main(final String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					final App window = new App();
					window.frame.setVisible(true);
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 573, 458);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		final Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		final GridBagConstraints gbc_rigidArea = new GridBagConstraints();
		gbc_rigidArea.insets = new Insets(0, 0, 5, 5);
		gbc_rigidArea.gridx = 0;
		gbc_rigidArea.gridy = 0;
		frame.getContentPane().add(rigidArea, gbc_rigidArea);

		final JPanel panel = new JPanel();
		panel.setBorder(null);
		final GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		frame.getContentPane().add(panel, gbc_panel);
		final GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		final JLabel lblChoseYourTrack = new JLabel("Chose your track: ");
		final GridBagConstraints gbc_lblChoseYourTrack = new GridBagConstraints();
		gbc_lblChoseYourTrack.anchor = GridBagConstraints.EAST;
		gbc_lblChoseYourTrack.insets = new Insets(0, 0, 5, 5);
		gbc_lblChoseYourTrack.gridx = 0;
		gbc_lblChoseYourTrack.gridy = 0;
		panel.add(lblChoseYourTrack, gbc_lblChoseYourTrack);

		comboBox = new JComboBox<String>();
		final GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		panel.add(comboBox, gbc_comboBox);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {
				"silverstone", "monaco" }));

		final JLabel lblChoseYourCar = new JLabel("Chose your car: ");
		final GridBagConstraints gbc_lblChoseYourCar = new GridBagConstraints();
		gbc_lblChoseYourCar.anchor = GridBagConstraints.EAST;
		gbc_lblChoseYourCar.insets = new Insets(0, 0, 0, 5);
		gbc_lblChoseYourCar.gridx = 0;
		gbc_lblChoseYourCar.gridy = 1;
		panel.add(lblChoseYourCar, gbc_lblChoseYourCar);

		comboBox_1 = new JComboBox<String>();
		final GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 1;
		gbc_comboBox_1.gridy = 1;
		panel.add(comboBox_1, gbc_comboBox_1);
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {
				"racing car", "DIY car" }));

		final JPanel panel_1 = new JPanel();
		final GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 3;
		frame.getContentPane().add(panel_1, gbc_panel_1);
		final GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0 };
		gbl_panel_1.rowHeights = new int[] { 0 };
		gbl_panel_1.columnWeights = new double[] { Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		final JPanel panel_2 = new JPanel();
		final GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.anchor = GridBagConstraints.EAST;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.VERTICAL;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 4;
		frame.getContentPane().add(panel_2, gbc_panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));

		final JButton btnNewButton_1 = new JButton("Close");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		panel_2.add(btnNewButton_1);

		final Component horizontalStrut = Box.createHorizontalStrut(20);
		panel_2.add(horizontalStrut);

		final JButton btnNewButton = new JButton("Ok");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				final JFrame f = new JFrame("Racing");
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.add(new Racing());
				f.setResizable(false);
				f.pack();
				f.setVisible(true);

				frame.setVisible(false);
			}
		});
		panel_2.add(btnNewButton);

		final Component rigidArea_1 = Box
				.createRigidArea(new Dimension(20, 20));
		final GridBagConstraints gbc_rigidArea_1 = new GridBagConstraints();
		gbc_rigidArea_1.gridx = 2;
		gbc_rigidArea_1.gridy = 5;
		frame.getContentPane().add(rigidArea_1, gbc_rigidArea_1);
	}

}
