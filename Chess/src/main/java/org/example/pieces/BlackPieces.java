package org.example.pieces;

import org.example.models.Cell;
import org.example.models.PieceFactory;
import org.example.models.Pieces;

import java.util.ArrayList;

public class BlackPieces extends PieceFactory {
    @Override
    public ArrayList<Pieces> createPieces() {
        ArrayList<Pieces> blackPieces = new ArrayList<>();
        blackPieces.add(new Rook("Rook1",new Cell(7,0),new Cell(7,0),false,false));
        blackPieces.add(new Rook("Rook2",new Cell(7,7),new Cell(7,7),false,false));
        blackPieces.add(new Knight("Knight1",new Cell(7,1),new Cell(7,1),false,false));
        blackPieces.add(new Knight("Knight2",new Cell(7,6),new Cell(7,6),false,false));
        blackPieces.add(new Bishop("Bishop1",new Cell(7,2),new Cell(7,2),false,false));
        blackPieces.add(new Bishop("Bishop2",new Cell(7,5),new Cell(7,5),false,false));
        blackPieces.add(new Queen("Queen",new Cell(7,3),new Cell(7,3),false,false));
        blackPieces.add(new King("King",new Cell(7,4),new Cell(7,4),false,false));
        blackPieces.add(new Pawn("Pawn1",new Cell(6,0),new Cell(6,0),false,false));
        blackPieces.add(new Pawn("Pawn2",new Cell(6,1),new Cell(6,1),false,false));
        blackPieces.add(new Pawn("Pawn3",new Cell(6,2),new Cell(6,2),false,false));
        blackPieces.add(new Pawn("Pawn4",new Cell(6,3),new Cell(6,3),false,false));
        blackPieces.add(new Pawn("Pawn5",new Cell(6,4),new Cell(6,4),false,false));
        blackPieces.add(new Pawn("Pawn6",new Cell(6,5),new Cell(6,5),false,false));
        blackPieces.add(new Pawn("Pawn7",new Cell(6,6),new Cell(6,6),false,false));
        blackPieces.add(new Pawn("Pawn8",new Cell(6,7),new Cell(6,7),false,false));
        return blackPieces;
    }
}
