package org.example;

import javafx.util.Pair;
import org.example.models.Cell;
import org.example.models.Direction;
import org.example.models.PieceFactory;
import org.example.models.Pieces;
import org.example.pieces.BlackPieces;
import org.example.pieces.WhitePieces;

import java.util.*;

public class Board {
    private static List<List<Pieces>> board;

    public Board(){
        board = new ArrayList<>(8);
        for(int j=0;j<8;j++){
            board.add(new ArrayList<>(Collections.nCopies(8, null)));
        }
    }


    /**
     * Initializes the board to the starting position with all <tt>Pieces</tt> in its initial position.
     * <p>
     *     Board contains both <b>Black</b> and <b>White</b> pieces. We create those pieces using the <tt>PiecesFactory</tt>.
     *     Creating the objects for <tt>BlackPieces</tt> and <tt>WhitePieces</tt> using the <tt>PiecesFactory</tt> and setting it in the board.
     * </p>
     * @see Pieces
     * @see PieceFactory
     */
    public void initialiseBoard(){
        PieceFactory blackPieces = new BlackPieces();
        PieceFactory whitePieces = new WhitePieces();
        for(Pieces pieces: blackPieces.createPieces()) {
            board.get(pieces.getInitialPosition().getX()).set(pieces.getInitialPosition().getY(),pieces);
        }
        for(Pieces pieces: whitePieces.createPieces()) {
            board.get(pieces.getInitialPosition().getX()).set(pieces.getInitialPosition().getY(),pieces);
        }
    }


    /**
     * Method for returning the alive pieces in the board. Meaning, the pieces whose <tt>(isKilled == false)</tt>.
     *
     * @return ArrayList of Pieces
     * @see Pieces
     */
    public ArrayList<Pieces> getAlivePiecesOnBoard(){
        ArrayList<Pieces> alivePieces = new ArrayList<>();
        for(int i=0;i<board.size();i++){
            for(int j=0;j<board.get(0).size();j++){
                if(board.get(i).get(j)!=null) alivePieces.add(board.get(i).get(j));
            }
        }
        return alivePieces;
    }


    /**
     * Method for placing the pieces in the given position.
     * Takes in the current position and the new position of the cell.
     * <p><b>Logic:</b></p>
     * <ol>
     *     <li>Get the piece in the current position and the one in the new position. </li>
     *     <li>If there is not pieces in the new position, then move the piece in current position to the new position. </li>
     *     <li>Else if there is an element in the new position, and if it is of opponents type
     *     <pre>{@code pieceInNewPosition.isWhite()!=pieceInCurrentPosition.isWhite()}</pre> then kill it
     *     and replace with the piece in current position.</li>
     *
     * </ol>
     *
     * <p><b>To replace the element in new position:</b></p>
     * <ol>
     *     <li>Set the value in board for the current position to be null</li>
     *     <li>Set the value in board for the new position to be the <tt>pieceInCurrentPosition</tt></li>
     *     <li>Update the currentPosition of the piece</li>
     * </ol>
     * @param currentPosition
     * @param newPosition
     * @see Cell
     */
    public void placePieceInItsPositionOnBoard(Cell currentPosition,Cell newPosition){
        Pieces pieceInCurrentPosition = board.get(currentPosition.getX()).get(currentPosition.getY());
        Pieces pieceInNewPosition = board.get(newPosition.getX()).get(newPosition.getY());
        if(pieceInNewPosition!=null){
            if(pieceInNewPosition.isWhite()!=pieceInCurrentPosition.isWhite())
                pieceInNewPosition.setKilled(true);
            else
                System.out.println("Invalid Placement, Already a coin of same type present in the position");
        }

        board.get(pieceInCurrentPosition.getCurrentPosition().getX()).set(pieceInCurrentPosition.getCurrentPosition().getY(),null);
        board.get(newPosition.getX()).set(newPosition.getY(),pieceInCurrentPosition);
        pieceInCurrentPosition.setCurrentPosition(newPosition);
    }


    /**
     * Method for getting all valid possible moves for an element in the given position.
     * <p><b>Logic:</b></p>
     *     <ol>
     *         <li>Get the piece in the given position = <tt>piece</tt></li>
     *         <li>For each direction and destination Cell provided in each <tt>Pieces</tt> class's <tt>getPossiblePositions()</tt> method,
     *         redirect the call the <tt>moveTowardsDirectionAndCheckBoard()</tt> for moving one step at a time towards the final destination cell
     *         at the same time checking if the move is valid</li>
     *         <li>The <tt><moveTowardsDirectionAndCheckBoard()</tt> method provides all the valid possible moves for the piece. Now
     *         print them one by one using a loop. also printing them in the grid for a coherent view.</li>
     *     </ol>
     *
     * <p><i><b>Note:</b> If the piece is a Pawn or a Knight, they have a different approach of handling moves, hence
     * re-directing to the respective method for handling.</i></p>
     * @param position
     * @return An array of possible Cell positions
     *
     * @see Cell
     * @see Pieces
     */
    public ArrayList<Cell> getMoves(Cell position){
        Pieces piece = board.get(position.getX()).get(position.getY());
        ArrayList<Cell> resultPositions = new ArrayList<>();

        if(piece.getName().contains("Knight")) resultPositions.addAll(getPositionForKnight(piece));
        else if(piece.getName().contains("Pawn")) resultPositions.addAll(getPositionForPawn(piece));
        else{
            for(Pair<Direction, Cell> directionCellPair: piece.getPossiblePositions()){
                resultPositions.addAll(moveTowardsDirectionAndCheckBoard(directionCellPair.getKey(), directionCellPair.getValue(),piece.getCurrentPosition(),piece));
            }
        }

        resultPositions.removeAll(Collections.singleton(null));

        System.out.println("\n"+piece.getName().toUpperCase());
        if(!resultPositions.isEmpty()){
            for(Cell cell: resultPositions){
                System.out.print(Arrays.stream(AlphabetPositioning.values()).filter(x->x.getPosition()==cell.getX()).findAny().get() +""+ cell.getY() + " ");
            }
        }
        else System.out.println("No moves!");

        printBoardWithPossibleMoves(resultPositions);
        return resultPositions;
    }


    /**
     * Helper method for finding the valid possible positions for a Knight.
     * <p><b>Logic:</b></p>
     * <ol>
     *     <li>For each direction and destination Cell provided in each <tt>Pieces</tt> class's <tt>getPossiblePositions()</tt> method,
     *      check if the board has an element in the final destination point. </li>
     *      <li><b>If the element in the destination point is of opponent's type,
     *      we have an option to kill the piece</b>. so, add the Cell to the result array.
     *      <pre>{@code
     *      if(pieceInBoard!=null) {
     *          if (pieceInBoard.isWhite() != piece.isWhite())
     *              result.add(finalDestination);
     *      }
     *      }</pre></li>
     *      <li>Else if there is no Piece in the destination point, then simply add the Cell to the result.</li>
     * </ol>
     * @param piece
     * @return and ArrayList of valid moves of a Knight
     * @see Direction
     * @see Cell
     * @see org.example.pieces.Knight
     */
    private ArrayList<Cell> getPositionForKnight(Pieces piece) {
        ArrayList<Cell> result = new ArrayList<>();
        for(Pair<Direction, Cell> directionCellPair: piece.getPossiblePositions()){
            Cell finalDestination = directionCellPair.getValue();
            Pieces pieceInBoard = board.get(finalDestination.getX()).get(finalDestination.getY());
            if(pieceInBoard!=null) {
                if (pieceInBoard.isWhite() != piece.isWhite())
                    result.add(finalDestination);
            }
            else
                result.add(finalDestination);

        }
        return result;
    }


    /**
     * Helper method for getting valid possible positions for a Pawn.
     * <p><b>Logic:</b></p>
     * <ol>
     *     <li>For each direction and destination Cell provided in each <tt>Pieces</tt> class's <tt>getPossiblePositions()</tt> method.</li>
     *     <li>Re-direct the call to <tt>checkMovesForPawn()</tt> for validating the moves and adding them to a list.</li>
     * </ol>
     *
     * <p><i><b>Note: (Pawns only move in the forward direction) </b> If the piece is of White type, them we can only move in the Southern direction
     *  and if it is a Black piece it moves only in Northern direction.</i></p>
     * @param piece
     * @return An arrayList of Cells with valid moves of a Pawn
     * @see Direction
     * @see Cell
     * @see org.example.pieces.Pawn
     */
    private ArrayList<Cell> getPositionForPawn(Pieces piece){
        ArrayList<Cell> result = new ArrayList<>();
        for(Pair<Direction, Cell> directionCellPair: piece.getPossiblePositions()){
            if (piece.isWhite()) {
                if (directionCellPair.getKey().equals(Direction.SOUTH) || directionCellPair.getKey().equals(Direction.SOUTHEAST) || directionCellPair.getKey().equals(Direction.SOUTHWEST)) {
                    result.add(checkMovesForPawn(piece, directionCellPair, Direction.SOUTH));
                }
            } else {
                if (directionCellPair.getKey().equals(Direction.NORTH) || directionCellPair.getKey().equals(Direction.NORTHEAST) || directionCellPair.getKey().equals(Direction.NORTHWEST)) {
                    result.add(checkMovesForPawn(piece, directionCellPair, Direction.NORTH));
                }
            }

        }
        return result;
    }


    /**
     * Helper method for checking the valid moves for Pawn.
     *
     * <p><b>Logic:</b></p>
     * <p> If the direction towards the destination position is diagonal to the position of the Pawn, then there is a chance for killing
     * the opponent's coin. So, return the diagonal cell.</p>
     * <p> If the direction towards destination is in the same direction as the movement(i.e, NORTH or SOUTH), check if no other piece is in the
     * final position and return the Cell</p>
     * @param piece
     * @param directionCellPair
     * @param pawnMovementDirection
     * @return cell which is a valid move for the Pawn
     * @see Direction
     * @see Cell
     * @see Pieces
     */
    private Cell checkMovesForPawn(Pieces piece, Pair<Direction, Cell> directionCellPair,Direction pawnMovementDirection){
        Cell finalDestination = directionCellPair.getValue();
        Pieces pieceInBoard = board.get(finalDestination.getX()).get(finalDestination.getY());
        if(pieceInBoard!=null){
            if(!directionCellPair.getKey().equals(pawnMovementDirection) && pieceInBoard.isWhite() != piece.isWhite()) {
                return finalDestination;
            }
            return null;
        }
        else{
            if(directionCellPair.getKey().equals(pawnMovementDirection)) {
                return finalDestination;
            }
        }
        return null;
    }



    /**
     * Helper method for taking step-by-step move towards the final position of the piece.
     * <p><b>Logic:</b></p>
     * <ol>
     *     <li>Assume i=currentXPosition, and y = currentYPosition. Loop until we reach the final x and y coordinates.</li>
     *     <li>Get the updated i and j position based on the direction of movement of the piece by calling the <tt>modifyPositionBasedOnDirection()</tt>
     *     method. </li>
     *     <li>If there is an element in the board at the (i,j)th position, and if it if of opponent's type, there is a chance we could
     *     kill the element. So adding it to the result list.</li>
     *     <li>If there is no element in the board at the (i,j)th position, then simply add the Cell to the result list.</li>
     * </ol>
     * @param direction
     * @param lastPosition
     * @param currentPosition
     * @param piece
     * @return an ArrayList of valid moves of the given piece
     * @see Direction
     * @see Cell
     */
    private ArrayList<Cell> moveTowardsDirectionAndCheckBoard(Direction direction, Cell lastPosition,Cell currentPosition,Pieces piece){
        ArrayList<Cell> result = new ArrayList<>();
        int i = currentPosition.getX(), j = currentPosition.getY();
        while(!(i== lastPosition.getX() && j== lastPosition.getY())){
            i=modifyPositionBasedOnDirection(i,j,direction).getX();
            j=modifyPositionBasedOnDirection(i,j,direction).getY();
            Pieces elementInBoard = board.get(i).get(j);
            if(elementInBoard!=null){
                if(elementInBoard.isWhite()!=piece.isWhite()) result.add(new Cell(i,j));
                break;
            }
            result.add(new Cell(i,j));
        }
        return result;
    }


    /**
     * Helper method for incrementing and decrementing the i and j values of the Cell for moving towards the final position.
     * @param i
     * @param j
     * @param direction
     * @return returns the Cell after moving a step towards the given direction.
     */
    private Cell modifyPositionBasedOnDirection(int i, int j, Direction direction){
        switch(direction){
            case NORTH:
                i=(i>0)?i-1:0;
                break;
            case SOUTH:
                i=(i<7)?i+1:7;
                break;
            case EAST:
                j=(j<7)?j+1:7;
                break;
            case WEST:
                j=(j>0)?j-1:0;
                break;
            case NORTHEAST:
                i=(i>0)?i-1:0;
                j=(j<7)?j+1:7;
                break;
            case NORTHWEST:
                i=(i>0)?i-1:0;
                j=(j>0)?j-1:0;
                break;
            case SOUTHEAST:
                i=(i<7)?i+1:7;
                j=(j<7)?j+1:7;
                break;
            case SOUTHWEST:
                i=(i<7)?i+1:7;
                j=(j>0)?j-1:0;
                break;
        }
        return new Cell(i,j);
    }


    /**
     * Method for displaying the moves of a given piece in a grid format.
     * <p>If the element is a White piece - it will be displayed in White colour.
     * <p>If the element is a Black Piece - it will be displayed in Red colour.
     * <p><i>Note:The possible moves of the given Piece is represented by hyphen(-)</i></p>
     * @param resultPositions
     */
    private void printBoardWithPossibleMoves(ArrayList<Cell> resultPositions) {
        System.out.println("\n--------------------------\nBOARD\n--------------------------");

        //grip column number
        for(int i=0;i<board.get(0).size();i++){
            System.out.print(String.format("%8s", i)+"  ");
        }
        System.out.println("\n   -------------------------------------------------------------------------------");


        for(int i=0;i<board.size();i++){
            //grid row number
            System.out.print(i+"  ");
            for(int j=0;j<board.get(0).size();j++){

                //Printing the elements in board
                if(board.get(i).get(j)!=null){
                    if(board.get(i).get(j).isWhite())
                        System.out.print(String.format("%-7s", board.get(i).get(j).getName())+" | ");
                    else
                        System.out.print("\u001B[31m"+String.format("%-7s", board.get(i).get(j).getName())+"\u001B[0m"+" | ");
                }
                //printing hyphen for possible move cells
                else if(resultPositions.contains(new Cell(i,j))){
                    System.out.print(String.format("%7s", "-")+" | ");
                }
                else{
                    System.out.print(String.format("%7s", " ")+" | ");
                }
            }
            System.out.println("\n   -------------------------------------------------------------------------------");
        }
    }


    /**
     * Method for displaying the moves of a given piece in a grid format.
     * <p>If the element is a White piece - it will be displayed in White colour.
     * <p>If the element is a Black Piece - it will be displayed in Red colour.
     *
     */
    public void printBoard(){
        System.out.println("\n--------------------------\nBOARD\n--------------------------");

        //grip column number
        for(int i=0;i<board.get(0).size();i++){
            System.out.print(String.format("%8s", i)+"  ");
        }
        System.out.println("\n   -------------------------------------------------------------------------------");

        for(int i=0;i<board.size();i++){
            //grid row number
            System.out.print(i+"  ");

            for(int j=0;j<board.get(0).size();j++){
                //Printing the elements in board
                if(board.get(i).get(j)!=null){
                    if(board.get(i).get(j).isWhite())
                        System.out.print(String.format("%-7s", board.get(i).get(j).getName())+" | ");
                    else
                        System.out.print("\u001B[31m"+String.format("%-7s", board.get(i).get(j).getName())+"\u001B[0m"+" | ");
                }
                else{
                    System.out.print(String.format("%7s", " ")+" | ");
                }
            }
            System.out.println("\n   -------------------------------------------------------------------------------");
        }
    }

}
