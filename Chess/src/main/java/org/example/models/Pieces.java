package org.example.models;

import javafx.util.Pair;
import lombok.Getter;

import java.util.List;

/**
 * Piece abstract class having initializers for the members.
 * Each piece in a chess board have its own name, initial Position, currentPosition, isKilled and isWhite(Black or white) members.
 * <p>The <tt>getPossiblePositions()</tt> method is used for providing all the possible destinations a piece can go from its current position,
 * irrespective of the pieces in the board.</p>
 * <p>This will be used by <tt>getMoves()</tt> method in the Board class for getting the possible moves for the piece
 * corresponding to the board's status.</p>
 * @see org.example.pieces.Queen
 * @see org.example.pieces.Knight
 * @see org.example.pieces.Pawn
 * @see org.example.Board
 *
 */
@Getter
public abstract class Pieces {
    private String name;
    private final Cell initialPosition;
    private Cell currentPosition;
    private boolean isKilled;
    private final boolean isWhite;

    public Pieces(String name, Cell initialPosition, Cell currentPosition, boolean isKilled, boolean isWhite){
        this.name = name;
        this.currentPosition=currentPosition;
        this.initialPosition=initialPosition;
        this.isKilled = isKilled;
        this.isWhite = isWhite;
    }

    public abstract List<Pair<Direction, Cell>> getPossiblePositions();

    public void setKilled(boolean killed) {
        isKilled = killed;
    }

    public void setCurrentPosition(Cell currentPosition) {
        this.currentPosition = currentPosition;
    }
}
