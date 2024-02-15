package com.java.hello.world;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class Frame extends JFrame {
	private static final int H_MARGIN = 100;
	private static final int V_MARGIN = 160;

	private static final int NROWS = 20;
	private static final int NCOLS = 30;
	private static final int CELL_SIZE = 20;

	public Frame(String title) {
		super(title);
        setSize(
			H_MARGIN * 2 + CELL_SIZE * NCOLS,
			V_MARGIN * 2 + CELL_SIZE * NROWS
		);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		for (int row = 0; row < NROWS; row++) {
			for (int col = 0; col < NCOLS; col++) {
				g.setColor(Color.BLACK);
				g.drawRect(
					H_MARGIN + col * CELL_SIZE,
					V_MARGIN + row * CELL_SIZE,
					CELL_SIZE,
					CELL_SIZE
				);
			}
		}
	}
}
