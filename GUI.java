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
import javax.swing.border.Border;

class GUI {
	public void createAndShow() {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Hello World Swing Example");
			Panel panel = new Panel();
			JButton button = new JButton("Run");

			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					panel.randomise();
				}
			});

			frame.setLayout(new FlowLayout());
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(panel.WIDTH, panel.HEIGHT);

			panel.setBackground(new Color(50, 10, 150));
			frame.add(button);
			panel.setLayout(null);

			frame.getContentPane().add(panel);

			frame.pack();
            frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		});
	}
}
