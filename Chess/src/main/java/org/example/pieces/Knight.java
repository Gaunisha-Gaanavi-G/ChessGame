package org.example.pieces;

import javafx.util.Pair;
import org.example.models.Cell;
import org.example.models.Direction;
import org.example.models.Pieces;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Pieces {
    public Knight(String name, Cell initialPosition, Cell currentPosition, boolean isKilled, boolean isWhite) {
        super(name, initialPosition, currentPosition, isKilled, isWhite);
    }

    @Override
    public List<Pair<Direction, Cell>> getPossiblePositions() {

        List<Pair<Direction,Cell>> result = new ArrayList<>();
        int x = getCurrentPosition().getX()-2;
        int y = getCurrentPosition().getY()+1;
        validateAndAddXAndY(result, x, y);

        x = getCurrentPosition().getX()-2;
        y = getCurrentPosition().getY()-1;
        validateAndAddXAndY(result, x, y);

        x = getCurrentPosition().getX()+2;
        y = getCurrentPosition().getY()+1;
        validateAndAddXAndY(result, x, y);

        x = getCurrentPosition().getX()+2;
        y = getCurrentPosition().getY()-1;
        validateAndAddXAndY(result, x, y);

        x = getCurrentPosition().getX()-1;
        y = getCurrentPosition().getY()+2;
        validateAndAddXAndY(result, x, y);

        x = getCurrentPosition().getX()-1;
        y = getCurrentPosition().getY()-2;
        validateAndAddXAndY(result, x, y);

        x = getCurrentPosition().getX()+1;
        y = getCurrentPosition().getY()+2;
        validateAndAddXAndY(result, x, y);

        x = getCurrentPosition().getX()+1;
        y = getCurrentPosition().getY()-2;
        validateAndAddXAndY(result, x, y);

        return result;
    }

    private void validateAndAddXAndY(List<Pair<Direction, Cell>> result, int x, int y) {
        if(x<0 || y<0 || x>7 || y>7) {
            x = getCurrentPosition().getX();
            y = getCurrentPosition().getY();
        }
        result.add(new Pair<>(null,new Cell(x, y)));
    }
}
