package action;

import game.Board;
import piece.Piece;
import game.Position;
/**
 * Move Piece from Position to another
 * Created by 
 * @author Thanh Nguyen
 * Modified by: 
 * */
public class MovePieceMove implements Move {
    private Piece piece;
    public MovePieceMove(Piece piece){
        this.piece =piece;
    }
    @Override
    public String execute(Piece piece, Board board, Position position) {
        if(piece.getCurrentPosition().getAdjacentPositions(board).contains(position)) {
            this.piece.setCurrentPosition(position);
            board.addPieceAt(this.piece, position);
            board.removePiece(this.piece);
            return null;
        }
        else return  null;
    }

}
