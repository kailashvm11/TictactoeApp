package com.tictactoe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;

public class TictactoeGame {

    private static final Logger log = LoggerFactory.getLogger(TictactoeGame.class);

    public char[][] getGrid() {
        return grid;
    }

    public void setGrid(char[][] grid) {
        this.grid = grid;
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public void setInputHandler(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

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
        String input = inputHandler.readInput();
        updateCoordinatesFromInput(input);
    }

    private void updateCoordinatesFromInput(String input) {
        int x = Character.getNumericValue(input.charAt(0));
        int y = Character.getNumericValue(input.charAt(2));
        grid[x - 1][y - 1] = 'X';
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
