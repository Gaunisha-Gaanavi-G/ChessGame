import javafx.util.Pair;
import org.example.Board;
import org.example.models.Cell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class BoardTest {
    Board board = new Board();
    @Before
    public void init(){
        board.initialiseBoard();
    }
    @Test
    public void displayBoardTest(){
        ArrayList<Pair<Cell, Cell>> newWhitePiecePositions = new ArrayList<Pair<Cell,Cell>>(){{
            add(new Pair(new Cell(1,4),new Cell(3,4)));
            add(new Pair(new Cell(6,6),new Cell(4,6)));
            add(new Pair(new Cell(0,3),new Cell(4,7)));
        }};

        setPiecesInBoard(newWhitePiecePositions);
        board.printBoard();
    }

    @Test
    public void possiblePositionsOfQueen(){
        ArrayList<Pair<Cell, Cell>> newWhitePiecePositions = new ArrayList<Pair<Cell,Cell>>(){{
            add(new Pair(new Cell(1,4),new Cell(3,4)));
            add(new Pair(new Cell(0,3),new Cell(4,7)));
            add(new Pair(new Cell(6,5),new Cell(4,5)));
            add(new Pair(new Cell(1,1),new Cell(3,1)));
            add(new Pair(new Cell(1,3),new Cell(2,3)));
            add(new Pair(new Cell(0,2),new Cell(2,4)));
            add(new Pair(new Cell(6,2),new Cell(5,2)));
        }};

        setPiecesInBoard(newWhitePiecePositions);
        board.getMoves(new Cell(3,4));
        board.printBoard();
    }

    private void setPiecesInBoard(ArrayList<Pair<Cell, Cell>> newPositions){
        for(Pair<Cell,Cell> key: newPositions){
            board.placePieceInItsPositionOnBoard(key.getKey(),key.getValue());
        }
    }

    @After
    public void cleanup(){
        board.initialiseBoard();
    }
}
