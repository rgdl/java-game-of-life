package com.game.of.life;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
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

class GameOfLife {
    public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Game of Life");
			Panel panel = new Panel();
			JButton runButton = new JButton("Run");

			frame.setLayout(new BorderLayout());
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(panel.WIDTH, panel.HEIGHT);

			frame.add(runButton, BorderLayout.SOUTH);
			panel.setLayout(null);

			frame.getContentPane().add(panel);

			frame.pack();
            frame.setLocationRelativeTo(null);
			frame.setVisible(true);

			Timer timer = new Timer(150, (e) -> {
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
