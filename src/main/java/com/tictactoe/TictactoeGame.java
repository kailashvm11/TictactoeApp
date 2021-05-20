package com.tictactoe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;

public class TictactoeGame {

    private static final Logger log = LoggerFactory.getLogger(TictactoeGame.class);
    private char[][] grid = new char[3][3];
    private InputHandler inputHandler;

    public TictactoeGame(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
        for ( char[] row: grid ) {
            Arrays.fill(row, ' ');
        }
    }

    public void start() {
        log.info("Starting a new Tic-tac-toe Game");
        printGameGrid();
        play();
    }

    private void play() {
        inputHandler.readInput();
    }

    private void printGameGrid() {
        printHorizontalGridBorder();
        for (char[] row : grid) {
            StringBuilder sb = new StringBuilder("| ");
            for (char cell : row) {
                sb.append(cell).append(" ");
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
