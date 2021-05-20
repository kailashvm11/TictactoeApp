package com.tictactoe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class InputHandler {
    Scanner scanner;
    private static final Logger logger = LoggerFactory.getLogger(InputHandler.class);

    public InputHandler() {
        scanner = new Scanner(System.in);
    }

    public String readInput() {
        logger.info("Enter the coordinates for X");
        return scanner.nextLine();
    }
}
