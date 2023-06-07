package player;

import action.*;
import game.Board;
import game.Position;
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
    private Status previousPhase;
    private Move currentMove = null;
    private Boolean isInTurn =false;
    private final StatusSet statusSet = new StatusSet();

    private boolean isRed;

     /**
      * Constructor
      *
      * @param name
      * @param board
      * @param pieceSet
      * @param isRed
      */
    public Player(String name, Board board, PieceSet pieceSet, boolean isRed) {

        this.name = name;
        this.board = board;
        this.pieceSet = pieceSet;
        this.isRed = isRed;
        if(isRed){
            addStatus(Status.RED);
        }else addStatus(Status.BLUE);
        gamePhase = Status.PHASE_1;
    }
    /**
     * return current game phase
     * */
    public Status getGamePhase() {
        return gamePhase;
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
        for(Map.Entry<Point, Position> entry : board.getPositions().entrySet()){
            if(entry.getValue()==e.getSource()){
                Piece piece = null;
                //System.out.println(entry.getKey());
                if(gamePhase==Status.PHASE_2 ) {
                    System.out.println("PHRASE 3--------------------------------------------------------");
                    System.out.println(board.getNumberOfPieces(Status.BLUE));
                    System.out.println(board.getNumberOfPieces(Status.RED));
                    if (isRed && board.getNumberOfPieces(Status.RED) == 3) {
                        gamePhase = Status.PHASE_3;
                    }
                    if (!isRed && board.getNumberOfPieces(Status.BLUE) == 3) {
                        gamePhase = Status.PHASE_3;
                    }
                }
                if (gamePhase == Status.PHASE_1) {

                    if(!board.hasPieceAt(entry.getValue())) {
                        piece=pieceSet.useOnePiece();
                        currentMove = new SetPieceMove();
                        currentMove.execute(piece, board, entry.getValue());
                        //board.updateMills();

                        if (pieceSet.getPieceSetSize() == 0) {
                            //Testing phase 3
                            gamePhase = Status.PHASE_2;
                            System.out.println("Phase 2 starts");
                        }

                        if (piece.hasStatus(Status.IN_MILL) && (!board.isAllMill(!isRed)) ){
                                previousPhase=gamePhase;
                                gamePhase=Status.PHASE_REMOVE;
                        }else {
                            isInTurn=false;
                        }

                        return;
                    }
                }
                else if (gamePhase == Status.PHASE_2) {


                    System.out.print(entry.getValue().getLayer());
                    System.out.print(' ');
                    System.out.print(entry.getValue().getPositionNumber());
                    //System.out.println(entry.getValue().getLocation());

                    //System.out.println(board.getPiece(entry.getValue()).getCurrentPosition().getLocation());
                    //targetPiece = board.getPiece(entry.getValue());
                    if (currentMove != null) {
                        System.out.println("In progress");

                    }

                    if (board.hasPieceAt(entry.getValue())) {
                        piece = board.getPiece(entry.getValue());
                        if (piece.isRed == isRed) {
                            currentMove = new MovePieceMove(piece);
                        }
                        else currentMove = null;

                    } else {
                        if (currentMove != null) {
                            piece = currentMove.getPiece();
                            if (piece.getCurrentPosition().getAdjacentPositions(board).contains(entry.getValue())
                            && !board.hasPieceAt(entry.getValue())) {
                                currentMove.execute(piece, board, entry.getValue());
                                currentMove = null;
                                board.updateMills();
                                if (piece.hasStatus(Status.IN_MILL)&&(!board.isAllMill(!isRed)) ){
                                        previousPhase=gamePhase;
                                        gamePhase=Status.PHASE_REMOVE;

                                }
                                else {
                                    //System.out.println("Empty");
                                    isInTurn = false;
                                }
                                //return;

                            }
                        }
                    }
                } else if (gamePhase == Status.PHASE_3){
                    System.out.println("-------------------------");
                    System.out.println("Phase 3 starts");
                    System.out.println(this.statusList());

                    if (currentMove != null) {
                        System.out.println("In progress");

                    }

                    if (board.hasPieceAt(entry.getValue())) {
                        piece = board.getPiece(entry.getValue());
                        if (piece.isRed == isRed) {
                            currentMove = new FlyPieceMove(piece);
                        }
                        else currentMove=null;
                        //System.out.print(name);
                        //System.out.println("New");


                    } else {
                        if (currentMove != null) {
                            piece = currentMove.getPiece();
                            if (!board.hasPieceAt(entry.getValue())){
                                currentMove.execute(piece, board,entry.getValue());
                                currentMove = null;
                                board.updateMills();
                                if (piece.hasStatus(Status.IN_MILL)&&(!board.isAllMill(!isRed)) ){
                                    previousPhase=gamePhase;
                                    gamePhase=Status.PHASE_REMOVE;

                                }
                                else {
                                    //System.out.println("Empty");
                                    isInTurn = false;
                                }
                            }
                        }
                    }
                }
                else if (gamePhase == Status.PHASE_REMOVE) {
                    if (board.hasPieceAt(entry.getValue())) {
                        piece=board.getPiece(entry.getValue());
                        if (piece.isRed != isRed) {
                            if(piece.hasStatus(Status.OUTSIDE_MILL)) {
                                currentMove = new RemovePieceMove(piece);
                                currentMove.execute(piece, board, entry.getValue());
                                currentMove = null;
                                gamePhase = previousPhase;

                                System.out.println(gamePhase);

                                isInTurn = false;
                            }
                        }
                    }
                }
                else if(gamePhase == Status.GAME_OVER) {
                    gamePhase=null;
                }

                if(board.isGameOver(this,gamePhase)){
                    gamePhase=Status.GAME_OVER;
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

