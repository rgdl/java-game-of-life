package com.game.of.life;

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
    private static final int H_MARGIN = 10;
    private static final int V_MARGIN = 10;

    private static final int NROWS = 50;
    private static final int NCOLS = 80;
    private static final int CELL_SIZE = 15;

    public static final int WIDTH = H_MARGIN * 2 + CELL_SIZE * NCOLS;
    public static final int HEIGHT = V_MARGIN * 2 + CELL_SIZE * NROWS;

    static final Color BG_COLOUR = new Color(40, 0, 80);
    static final Color GRID_COLOUR = new Color(100, 0, 100);
    static final boolean DRAW_GRID = false;

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

        setBackground(BG_COLOUR);

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

    private Color getCellColour(int nNeighbours) {
        int cappedNNeighbours = Math.min(nNeighbours, 5);
        return new Color(150, 100 + 31 * cappedNNeighbours, 150);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int row = 0; row < NROWS; row++) {
            for (int col = 0; col < NCOLS; col++) {

                boolean shouldFill = cellStates.get(row, col);

                if (shouldFill) {
                    Color cellColour = getCellColour(
                        cellStates.countNeighbours(row, col)
                    );
                    g.setColor(cellColour);
                    g.fillRect(
                        H_MARGIN + col * CELL_SIZE,
                        V_MARGIN + row * CELL_SIZE,
                        CELL_SIZE,
                        CELL_SIZE
                    );
                    continue;
                }

                if (DRAW_GRID) {
                    g.setColor(GRID_COLOUR);
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

    public void randomise() {
        cellStates.randomise(0.7f);
        repaint();
    }

    public void step() {
        cellStates.step();
        repaint();
    }
}
