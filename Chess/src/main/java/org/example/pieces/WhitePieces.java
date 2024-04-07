package org.example.pieces;

import org.example.models.Cell;
import org.example.models.PieceFactory;
import org.example.models.Pieces;
import org.example.pieces.Queen;

import java.util.ArrayList;
import java.util.List;

public class WhitePieces extends PieceFactory {
    @Override
    public ArrayList<Pieces> createPieces() {
        ArrayList<Pieces> whitePieces = new ArrayList<>();
        whitePieces.add(new Rook("Rook1",new Cell(0,0),new Cell(0,0),false,true));
        whitePieces.add(new Rook("Rook2",new Cell(0,7),new Cell(0,7),false,true));
        whitePieces.add(new Knight("Knight1",new Cell(0,1),new Cell(0,1),false,true));
        whitePieces.add(new Knight("Knight2",new Cell(0,6),new Cell(0,6),false,true));
        whitePieces.add(new Bishop("Bishop1",new Cell(0,2),new Cell(0,2),false,true));
        whitePieces.add(new Bishop("Bishop2",new Cell(0,5),new Cell(0,5),false,true));
        whitePieces.add(new Queen("Queen",new Cell(0,3),new Cell(0,3),false,true));
        whitePieces.add(new King("King",new Cell(0,4),new Cell(0,4),false,true));
        whitePieces.add(new Pawn("Pawn1",new Cell(1,0),new Cell(1,0),false,true));
        whitePieces.add(new Pawn("Pawn2",new Cell(1,1),new Cell(1,1),false,true));
        whitePieces.add(new Pawn("Pawn3",new Cell(1,2),new Cell(1,2),false,true));
        whitePieces.add(new Pawn("Pawn4",new Cell(1,3),new Cell(1,3),false,true));
        whitePieces.add(new Pawn("Pawn5",new Cell(1,4),new Cell(1,4),false,true));
        whitePieces.add(new Pawn("Pawn6",new Cell(1,5),new Cell(1,5),false,true));
        whitePieces.add(new Pawn("Pawn7",new Cell(1,6),new Cell(1,6),false,true));
        whitePieces.add(new Pawn("Pawn8",new Cell(1,7),new Cell(1,7),false,true));

        return whitePieces;
    }
}
