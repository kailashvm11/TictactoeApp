package com.tictactoe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class TictactoeGame {

    private static final Logger log = LoggerFactory.getLogger(TictactoeGame.class);
    private char[][] grid = new char[3][3];
    private InputHandler inputHandler;

    public TictactoeGame(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
        for(int i = 0; i < grid.length; i ++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = ' ';
            }
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
