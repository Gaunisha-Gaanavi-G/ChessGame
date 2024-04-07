package org.example;

/**
 * Main Runner class for initialising the board and starting  the game.
 * As of now, we can initialise the board and get the different possible moves for a given piece.
 * <p>The game is not yet capable of getting user input for each move and updating the status of the board after each move.
 *
 */
public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.initialiseBoard();

        board.printBoard();
    }
}