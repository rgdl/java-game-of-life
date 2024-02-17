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

        frame.setVisible(true);
    }
}
