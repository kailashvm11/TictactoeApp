package com.tictactoe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TictactoeGame {

    private static final Logger log = LoggerFactory.getLogger(TictactoeGame.class);
    private char[][] grid = new char[3][3];

    public TictactoeGame() {
        for(int i = 0; i < grid.length; i ++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    public void start() {
        log.info("Starting a new Tic-tac-toe Game");
        printGameGrid();
    }

    private void printGameGrid() {
        printHorizontalGridBorder();
        for (int i = 0; i < grid.length; i++) {
            StringBuilder sb = new StringBuilder("| ");
            for (int j = 0; j < grid[i].length ; j++) {
                sb.append(grid[i][j] + " ");
            }
            sb.append("|");
            log.info(sb.toString());
        }
        printHorizontalGridBorder();
    }

    private void printHorizontalGridBorder() {
        log.info("---------");
    }
}
