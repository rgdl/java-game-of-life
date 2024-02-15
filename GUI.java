package com.java.hello.world;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

class GUI {
	public void createAndShow() {
        Frame frame = new Frame("Hello World Swing Example");
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Hello World Swing");
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        label.setBorder(border);
        label.setPreferredSize(new Dimension(150, 100));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        frame.add(label);
        frame.setVisible(true);
    }
}
