package com.java.hello.world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;

// TODO: there is flickering on repaint. Do I need to use a JPanel instead?

public class Frame extends JFrame {
	private static final int H_MARGIN = 100;
	private static final int V_MARGIN = 160;

	private static final int NROWS = 20;
	private static final int NCOLS = 30;
	private static final int CELL_SIZE = 20;

	private Map<Cell, Boolean> cellStates = new HashMap<>();

	private class Cell {
		private final int row;
		private final int col;

		public Cell(int row, int col) {
			this.row = row;
			this.col = col;
		}

		@Override
		public boolean equals(Object other) {
			if (other == null || getClass() != other.getClass()) {
				return false;
			}
			Cell otherCell = (Cell) other;
			return row == otherCell.row && col == otherCell.col;
		}

		@Override
		public int hashCode() {
			return row + col;
		}

		public boolean isValid() {
			return (row >= 0) && (row < NROWS) && (col >= 0) && (col < NCOLS);
		}

		public String toString() {
			return "Cell(row: " + row + ", col: " + col + ")";
		}
	}

	private Cell cellFromCoords(int x, int y) {
		int _row = (y - V_MARGIN) / CELL_SIZE;
		int _col = (x - H_MARGIN) / CELL_SIZE;
		return new Cell(_row, _col);
	}

	public Frame(String title) {
		super(title);
        setSize(
			H_MARGIN * 2 + CELL_SIZE * NCOLS,
			V_MARGIN * 2 + CELL_SIZE * NROWS
		);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Cell cell = cellFromCoords(e.getX(), e.getY());

				if (!cell.isValid()) {
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
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);

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
