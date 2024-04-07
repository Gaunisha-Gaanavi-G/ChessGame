package org.example.pieces;

import javafx.util.Pair;
import lombok.Getter;
import org.example.models.Direction;
import org.example.models.Cell;
import org.example.models.Pieces;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Queen extends Pieces {
    private static String name;
    public Queen(String name, Cell initialPosition, Cell currentPosition, boolean isKilled,boolean isWhite) {
        super(name, initialPosition, currentPosition, isKilled,isWhite);
    }


    @Override
    public List<Pair<Direction, Cell>> getPossiblePositions() {
        List<Pair<Direction,Cell>> result = new ArrayList<>();
        result.add(new Pair<>(Direction.NORTH,new Cell(0, getCurrentPosition().getY())));
        result.add(new Pair<>(Direction.SOUTH,new Cell(7,getCurrentPosition().getY())));
        result.add(new Pair<>(Direction.EAST,new Cell(getCurrentPosition().getX(), 7)));
        result.add(new Pair<>(Direction.WEST,new Cell(getCurrentPosition().getX(), 0)));

        int a= getCurrentPosition().getX();
        int b=7- getCurrentPosition().getY();
        int min = Math.min(a,b);
        result.add(new Pair<>(Direction.NORTHEAST,new Cell(getCurrentPosition().getX()-min, getInitialPosition().getY()+min)));

        a = getCurrentPosition().getX();
        b = getCurrentPosition().getY();
        min = Math.min(a,b);
        result.add(new Pair<>(Direction.NORTHWEST,new Cell(getCurrentPosition().getX()-min, getInitialPosition().getY()-min)));

        a = 7- getCurrentPosition().getX();
        b = 7- getCurrentPosition().getY();
        min = Math.min(a,b);
        result.add(new Pair<>(Direction.SOUTHEAST,new Cell(getCurrentPosition().getX()+min, getCurrentPosition().getY()+min)));

        a = 7- getCurrentPosition().getX();
        b = getCurrentPosition().getY();
        min = Math.min(a,b);
        result.add(new Pair<>(Direction.SOUTHWEST,new Cell(getCurrentPosition().getX()+min, getInitialPosition().getY()-min)));
        return result;
    }
}
