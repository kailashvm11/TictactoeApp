package com.tictactoe;

public class TictactoeApp {
    public static void main( String[] args ) {
        InputHandler inputHandler = new InputHandler();
        TictactoeGame game = new TictactoeGame(inputHandler);
        game.start();
    }
}
