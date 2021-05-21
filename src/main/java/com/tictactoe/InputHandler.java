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

    public String readInput(char symbol) {
        boolean validInput = false;
        String input = "";
        while(!validInput) {
            logger.info("Enter the coordinates for " + symbol);
            input = scanner.nextLine().trim();
            validInput = validateInput(input);
            if(!validInput) {
                logger.info("Please enter only two numbers between 1 and 3 separated by a comma");
            }
        }
        return input;
    }

    private boolean validateInput(String input) {
        return isInputLength3(input) && isInputBetween1And3(input.charAt(0)) &&
                isCharacterComma(input.charAt(1)) && isInputBetween1And3(input.charAt(2));
    }


    private boolean isInputLength3(String input) {
        return input.length() == 3;
    }

    private boolean isInputBetween1And3(char ch) {
        return  ch >= '1' && ch <= '3';
    }

    private boolean isCharacterComma(char ch) {
        return ch == ',';
    }

}
