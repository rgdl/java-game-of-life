package com.java.hello.world;

import java.util.HashMap;
import java.util.Random;

class CellStates {
	// Index by row, then column
	public HashMap<Integer, HashMap<Integer, Boolean>> data;

	public CellStates(int nRows, int nCols) {
		data = new HashMap<>();

		for (int row = 0; row < nRows; row++) {
			HashMap<Integer, Boolean> rowData = new HashMap<>();

			for (int col = 0; col < nCols; col++) {
				rowData.put(col, false);
			}

			data.put(row, rowData);
		}
	}

	public void toggle(int row, int col) {
		data.get(row).put(col, !get(row, col));
	}

	public boolean get(int row, int col) {
		return data.get(row).get(col);
	}

	private void applyToCells(CellMap cellMap) {
		for (int row : data.keySet()) {
			for (int col : data.get(row).keySet()) {
				cellMap.apply(row, col);
			}
		}
	}

	public void randomise(float probability) {
		Random random = new Random();
		applyToCells((row, col) -> {
			data.get(row).put(col, random.nextFloat() > probability);
		});
	}

	public void step() {
		applyToCells((row, col) -> {
			toggle(row, col);
		});
	}
}

interface CellMap {
	void apply(int row, int col);
}
