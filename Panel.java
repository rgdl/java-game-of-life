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
import javax.swing.JPanel;

// TODO: there is flickering on repaint. Do I need to use a JPanel instead?

public class Panel extends JPanel {
	private static final int H_MARGIN = 100;
	private static final int V_MARGIN = 160;

	private static final int NROWS = 20;
	private static final int NCOLS = 30;
	private static final int CELL_SIZE = 20;

	public static final int WIDTH = H_MARGIN * 2 + CELL_SIZE * NCOLS;
	public static final int HEIGHT = V_MARGIN * 2 + CELL_SIZE * NROWS;

	private Map<Cell, Boolean> cellStates = new HashMap<>();

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}

	private Cell cellFromCoords(int x, int y) {
		int row = (y - V_MARGIN) / CELL_SIZE;
		int col = (x - H_MARGIN) / CELL_SIZE;
		System.out.println(
			String.format(
				"Click (x: %d, y: %d), (row: %d, col: %d)", x, y, row, col
			)
		);
		return new Cell(row, col);
	}

	public boolean cellIsValid(Cell cell) {
		return (cell.row >= 0) &&
			(cell.row < NROWS) &&
			(cell.col >= 0) &&
			(cell.col < NCOLS);
	}

	public Panel() {
		super();
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Cell cell = cellFromCoords(e.getX(), e.getY());

				if (!cellIsValid(cell)) {
					return;
				}

				if (cellStates.containsKey(cell)) {
					cellStates.put(cell, !cellStates.get(cell));
				} else {
					cellStates.put(cell, true);
				}
				repaint();
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(200, 255, 200));

		System.out.println("paintComponent");

		for (int row = 0; row < NROWS; row++) {
			for (int col = 0; col < NCOLS; col++) {

				Cell cell = new Cell(row, col);

				if (cellStates.containsKey(cell) && cellStates.get(cell)) {
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
}
