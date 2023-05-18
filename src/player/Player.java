package player;

import action.FlyPieceMove;
import action.Move;
import action.MovePieceMove;
import game.Board;
import game.Position;
import game.StartPanel;
import piece.Piece;
import piece.PieceSet;
import status.State;
import status.Status;
import status.StatusSet;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

/**
 * Player object class
 * Created by 
 * @author Thanh Nguyen
 * Modified by: 
 * */
public class Player implements State, ActionListener {

    private String name;
    private Board board;
    private PieceSet pieceSet;
    private Status gamePhase;
    private Move currentMove = null;
    private Boolean isInTurn =false;
    private final StatusSet statusSet = new StatusSet();

     /**
      * Constructor
      *
      * @param name
      * @param board
      * @param pieceSet
      */
    public Player(String name, Board board, PieceSet pieceSet) {

        this.name = name;
        this.board = board;
        this.pieceSet = pieceSet;
        gamePhase = Status.PHASE_1;
    }
     /**
     * Print Player name
     *
     * @param 
     * */

    @Override
    public String toString(){
        return name;
    }
     /**
     * Get Player name
     *
     * @param 
     * */
    public String getName() {
        return name;
    }
     /**
     * set Player name
     *
     * @param name
     * */
    public void setName(String name) {
        this.name = name;
    }
     /**
     * Check if actor has a status
     *
     * @param status
     * */
    @Override
    public boolean hasStatus(Enum<?> status) {
        return statusSet.hasStatus(status);
    }

    /**
     * Add stattus to StatusSet
     *
     * @param status
     * */
    @Override
    public void addStatus(Enum<?> status) {
        statusSet.addStatus(status);
    }
    /**
     * Remove Status from statusSet
     *
     * @param status
     * */
    @Override
    public void removeStatus(Enum<?> status) {
        statusSet.removeStatus(status);
    }
    /**
     * get the Status list
     *
     * @param
     * */
    @Override
    public List<Enum<?>> statusList() {
        return statusSet.statusList();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(Map.Entry<Point, Position> entry : board.positions.entrySet()){
            if(entry.getValue()==e.getSource()){
                Piece piece = null;
                //System.out.println(entry.getKey());
                if (gamePhase == Status.PHASE_1) {

                    if(!board.hasPieceAt(entry.getValue())) {
                        piece=pieceSet.useOnePiece();
                        piece.setCurrentPosition(entry.getValue());
                        board.addPieceAt(piece, entry.getValue());


                        if (pieceSet.getPieceSetSize() == 0) {
                            //Testing phase 3
                            gamePhase = Status.PHASE_3;
                            this.addStatus(Status.ACTIVE_FLY);
                            System.out.println("Phase 2 starts");
                        }

                        isInTurn=false;
                        return;
                    }
                }
                else if (gamePhase == Status.PHASE_2){


                    System.out.print(entry.getValue().getLayer());
                    System.out.print(' ');
                    System.out.print(entry.getValue().getPositionNumber());
                    //System.out.println(entry.getValue().getLocation());

                    //System.out.println(board.getPiece(entry.getValue()).getCurrentPosition().getLocation());
                    //targetPiece = board.getPiece(entry.getValue());
                    if(currentMove!=null){
                        System.out.println("In progress");

                    }

                    if(board.hasPieceAt(entry.getValue())) {
                        piece = board.getPiece(entry.getValue());
                        currentMove = new MovePieceMove(piece);
                        System.out.print(name);
                        System.out.println("New");


                    }
                    else {
                        if (currentMove != null) {
                            piece=currentMove.getPiece();
                            if(piece.getCurrentPosition().getAdjacentPositions(board).contains(entry.getValue())) {
                                currentMove.execute(piece, board, entry.getValue());
                                currentMove = null;

                                System.out.println("Empty");
                                isInTurn = false;
                                //return;
                            }
                        }
                    }

                    if(piece!=null)System.out.println(piece);
                    if (board.getNumberOfBluePieces() == 3){
                        this.addStatus(Status.ACTIVE_FLY);
                        gamePhase = Status.PHASE_3;
                    } else if (board.getNumberOfRedPieces() == 3){
                        this.hasStatus(Status.ACTIVE_FLY);
                        gamePhase = Status.PHASE_3;
                    }
                    isInTurn=false;
                    //return;
                } else if (gamePhase == Status.PHASE_3){
                    System.out.println("Phase 3 starts");

                    if(currentMove!=null){
                        System.out.println("In progress");
                    }

                    if (board.hasPieceAt(entry.getValue())){
                        piece = board.getPiece(entry.getValue());

                        System.out.println("Get piece at " + piece.getCurrentPosition());

                        if (this.hasStatus(Status.ACTIVE_FLY)){
                            currentMove = new FlyPieceMove(piece);
                        } else {
                            currentMove = new MovePieceMove(piece);
                        }
                    } else {
                        if (currentMove != null) {
                            piece = currentMove.getPiece();
                            currentMove.execute(piece, board, entry.getValue());
                            currentMove = null;
                            isInTurn = false;
                        }
                    }
                }
                if (board.isGameOver(true)){
                    return;
                }
            }
        }
    }

    public Boolean finishedTurn(){
        return !isInTurn;
    }

    public void newTurn(){
        isInTurn=true;
        board.updatePlayer(this);
    }
}

