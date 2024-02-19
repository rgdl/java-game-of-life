package com.game.of.life;

import java.util.HashMap;
import java.util.Random;

class CellStates {
    // Index by row, then column
    private HashMap<Integer, HashMap<Integer, Boolean>> data;
    private int nRows;
    private int nCols;

    public CellStates(int nRows, int nCols) {
        data = new HashMap<>();

        for (int row = 0; row < nRows; row++) {
            HashMap<Integer, Boolean> rowData = new HashMap<>();

            for (int col = 0; col < nCols; col++) {
                rowData.put(col, false);
            }

            data.put(row, rowData);
        }

        this.nRows = nRows;
        this.nCols = nCols;
    }

    private boolean cellIsValid(int row, int col) {
        return row >= 0 &&
            row < nRows &&
            col >= 0 &&
            col < nCols;
    }

    public int countNeighbours(int row, int col) {
        int[] offsets = {-1, 0, 1};
        int count = 0;

        for (int rowOffset : offsets) {
            for (int colOffset : offsets) {

                if (rowOffset == 0 && colOffset == 0) {
                    continue;
                }

                Boolean neighbourVal = get(row + rowOffset, col + colOffset);

                if (Boolean.TRUE.equals(neighbourVal)) {
                    count++;
                }
            }
        }
        return count;
    }

    // Only read cell data via this method
    public Boolean get(int row, int col) {
        if (cellIsValid(row, col)) {
            return data.get(row).get(col);
        }
        return null;
    }

    // Only write cell data via this method
    public void set(int row, int col, boolean val) {
        if (cellIsValid(row, col)) {
            data.get(row).put(col, val);
        }
    }

    public void toggle(int row, int col) {
        set(row, col, !get(row, col));
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
            set(row, col, random.nextFloat() > probability);
        });
    }

    public void step() {
        // Do NOT call newStates.step, or there will be an infinite loop
        CellStates newStates = new CellStates(nRows, nCols);

        // Get new states
        applyToCells((row, col) -> {
            int nNeighbours = countNeighbours(row, col);
            boolean alive = get(row, col);
            boolean newState;

            if (alive) {
                newState = nNeighbours == 2 || nNeighbours == 3;
            } else {
                newState = nNeighbours == 3;
            }

            newStates.set(row, col, newState);
        });

        // Update current states
        applyToCells((row, col) -> {
            set(row, col, newStates.get(row, col));
        });
    }
}

interface CellMap {
    void apply(int row, int col);
}
