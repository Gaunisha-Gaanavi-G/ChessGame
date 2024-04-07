package org.example.pieces;

import javafx.util.Pair;
import org.example.models.Cell;
import org.example.models.Direction;
import org.example.models.Pieces;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Pieces{
    public Pawn(String name, Cell initialPosition, Cell currentPosition, boolean isKilled, boolean isWhite) {
        super(name, initialPosition, currentPosition, isKilled, isWhite);
    }

    @Override
    public List<Pair<Direction, Cell>> getPossiblePositions() {
        List<Pair<Direction,Cell>> result = new ArrayList<>();
        int x = getCurrentPosition().getX();
        int y = getCurrentPosition().getY();
        if(getInitialPosition().getX()== getCurrentPosition().getX() && getInitialPosition().getY()==getCurrentPosition().getY()) {
            result.add(new Pair<>(Direction.NORTH, validateAndAddXAndY(x - 2, y)));
            result.add(new Pair<>(Direction.SOUTH, validateAndAddXAndY(x + 2, y)));
        } else{
            result.add(new Pair<>(Direction.NORTH,validateAndAddXAndY(x-1, y)));
            result.add(new Pair<>(Direction.SOUTH, validateAndAddXAndY(x + 1, y)));
            result.add(new Pair<>(Direction.NORTHEAST,validateAndAddXAndY(x-1, y+1)));
            result.add(new Pair<>(Direction.NORTHWEST,validateAndAddXAndY(x-1, y-1)));
            result.add(new Pair<>(Direction.SOUTHEAST,validateAndAddXAndY(x+1, y+1)));
            result.add(new Pair<>(Direction.SOUTHWEST,validateAndAddXAndY(x+1, y-1)));
        }
        return result;
    }

    private Cell validateAndAddXAndY( int x, int y) {
        if(x<0 || y<0 || x>7 || y>7) {
            x = getCurrentPosition().getX();
            y = getCurrentPosition().getY();
        }
        return new Cell(x, y);
    }
}
