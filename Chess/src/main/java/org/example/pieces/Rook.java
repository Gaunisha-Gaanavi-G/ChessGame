package org.example.pieces;

import javafx.util.Pair;
import org.example.models.Cell;
import org.example.models.Direction;
import org.example.models.Pieces;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Pieces {

    public Rook(String name,Cell initialPosition, Cell currentPosition, boolean isKilled, boolean isWhite) {
        super(name, initialPosition, currentPosition, isKilled, isWhite);
    }
    @Override
    public List<Pair<Direction, Cell>> getPossiblePositions() {
        List<Pair<Direction,Cell>> result = new ArrayList<>();
        result.add(new Pair<>(Direction.NORTH,new Cell(0, getCurrentPosition().getY())));
        result.add(new Pair<>(Direction.SOUTH,new Cell(7,getCurrentPosition().getY())));
        result.add(new Pair<>(Direction.EAST,new Cell(getCurrentPosition().getX(), 7)));
        result.add(new Pair<>(Direction.WEST,new Cell(getCurrentPosition().getX(), 0)));
        return result;
    }
}
