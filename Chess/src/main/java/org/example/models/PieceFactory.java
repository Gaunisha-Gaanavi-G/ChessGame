package org.example.models;


import java.util.ArrayList;


/**
 * <p><b>Factory Method:</b></p>
 * <p>Definition:  Factory method is a creational design pattern which solves the problem of creating product objects without
 * specifying their concrete classes.
 * <p>Reference: Factory Method - <a href = "https://refactoring.guru/design-patterns/factory-method">Refactoring Guru</a></p>
 *
 * <p>Usage:</p>
 * <p> There are 2 types of pieces in a Chess Board - Black and White.</p>
 * <p> To initialise the different pieces in its own position we use the Factory method for creating the each <tt>Piece</tt> object.
 * The <tt>BlackPieces</tt> and <tt>WhitePieces</tt> classes inherit this abstract class and initialise their pieces with the initial positions</p>
 * <p>The <tt>createPieces()</tt> method is called from <tt>Board</tt> class when the board is initialised.</p>
 * @see Pieces
 * @see org.example.pieces.BlackPieces
 * @see org.example.pieces.WhitePieces
 * @see org.example.Board
 */
public abstract class PieceFactory {

public abstract ArrayList<Pieces> createPieces();

}
