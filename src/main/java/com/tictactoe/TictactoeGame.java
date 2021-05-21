package com.tictactoe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;

public class TictactoeGame {

    private static final Logger log = LoggerFactory.getLogger(TictactoeGame.class);
    private int turnCount = 1;
    private char[][] grid = new char[3][3];
    private InputHandler inputHandler;


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
        while(turnCount <= 9) {
            char symbol = turnCount % 2 == 0 ? 'O' : 'X';
            String input = inputHandler.readInput(symbol);
            updateCoordinatesFromInput(input, symbol);
        }
    }

    private void updateCoordinatesFromInput(String input,char symbol) {
        int x = Character.getNumericValue(input.charAt(0));
        int y = Character.getNumericValue(input.charAt(2));
        grid[x - 1][y - 1] = symbol;
        printGameGrid();
        turnCount++;
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
