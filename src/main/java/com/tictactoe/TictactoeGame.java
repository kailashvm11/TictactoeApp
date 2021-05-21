package com.tictactoe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;

public class TictactoeGame {

    private static final Logger log = LoggerFactory.getLogger(TictactoeGame.class);
    private int turnCount = 1;
    private char gameWinner = ' ';
    private boolean isGameCompleted = false;
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
        while(turnCount <= 9 && !isGameCompleted) {
            char symbol = turnCount % 2 == 0 ? 'O' : 'X';
            String input = inputHandler.readInput(symbol);
            updateCoordinatesAndCheckWin(input, symbol);
        }
    }

    private void updateCoordinatesAndCheckWin(String input, char symbol) {
        int x = Character.getNumericValue(input.charAt(0));
        int y = Character.getNumericValue(input.charAt(2));
        if (grid[x - 1][y - 1] == ' ') {
            grid[x - 1][y - 1] = symbol;
            printGameGrid();
            turnCount++;
            if (turnCount > 5) {
                checkWin(symbol);
            }
        } else {
            log.info("This cell is already filled. Please select another cell");
        }
    }

    private void checkWin(char symbol) {
        checkHorizontalRows(symbol);
        if (isGameCompleted) {
            log.info("Player with " + gameWinner + " mark wins!");
        }
    }

    private void checkHorizontalRows(char symbol) {
        for (int i = 0; i < grid.length; i++) {
            char winSymbol = checkIfEquals(grid[i][0], grid[i][1], grid[i][2]);
            if (winSymbol == symbol) {
                setWinner(symbol);
            }
        }
    }

    private char checkIfEquals(char a, char b, char c) {
        if(a == b && b == c) {
            return a;
        }
        return ' ';
    }

    private void setWinner(char symbol) {
        gameWinner = symbol;
        isGameCompleted = true;
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
