package com.java.hello.world;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.swing.JPanel;

public class Panel extends JPanel {
	private static final int H_MARGIN = 100;
	private static final int V_MARGIN = 160;

	private static final int NROWS = 20;
	private static final int NCOLS = 30;
	private static final int CELL_SIZE = 20;

	public static final int WIDTH = H_MARGIN * 2 + CELL_SIZE * NCOLS;
	public static final int HEIGHT = V_MARGIN * 2 + CELL_SIZE * NROWS;

	private CellStates cellStates = new CellStates(NROWS, NCOLS);

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}

	private int xToCol(int x) {
		return (x - H_MARGIN) / CELL_SIZE;
	}

	private int yToRow(int y) {
		return (y - V_MARGIN) / CELL_SIZE;
	}

	public Panel() {
		super();
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row = yToRow(e.getY());
				int col = xToCol(e.getX());

				cellStates.toggle(row, col);

				repaint();
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(200, 255, 200));

		for (int row = 0; row < NROWS; row++) {
			for (int col = 0; col < NCOLS; col++) {

				boolean shouldFill = cellStates.get(row, col);

				if (shouldFill) {
					g.fillRect(
						H_MARGIN + col * CELL_SIZE,
						V_MARGIN + row * CELL_SIZE,
						CELL_SIZE,
						CELL_SIZE
					);
					continue;
				}

				g.drawRect(
					H_MARGIN + col * CELL_SIZE,
					V_MARGIN + row * CELL_SIZE,
					CELL_SIZE,
					CELL_SIZE
				);
			}
		}
	}

	public void randomise() {
		cellStates.randomise(0.5f);
		repaint();
	}

	public void step() {
		cellStates.step();
		repaint();
	}
}
