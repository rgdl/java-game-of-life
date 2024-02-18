package com.java.hello.world;

// TODO: better to make one data structure of primitives, so we don't need to create objects constantly? Otherwise, maybe there's a way to reuse them more

class Cell {
	public final int row;
	public final int col;

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

	public String toString() {
		return "Cell(row: " + row + ", col: " + col + ")";
	}
}

