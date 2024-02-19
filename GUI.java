package com.java.hello.world;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.Border;

class GUI {
	public void createAndShow() {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Game of Life");
			Panel panel = new Panel();
			JButton runButton = new JButton("Run");
			JButton stepButton = new JButton("Step");

			frame.setLayout(new FlowLayout());
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(panel.WIDTH, panel.HEIGHT);

			panel.setBackground(new Color(50, 10, 150));
			frame.add(runButton);
			panel.setLayout(null);

			frame.getContentPane().add(panel);

			frame.pack();
            frame.setLocationRelativeTo(null);
			frame.setVisible(true);

			Timer timer = new Timer(200, (e) -> {
				panel.step();
			});

			runButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					panel.randomise();

					timer.start();
				}
			});

		});
	}
}
