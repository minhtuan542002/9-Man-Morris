package action;

import game.Board;
import piece.Piece;
import game.Position;
import status.Status;

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
            board.removePiece(this.piece);
            this.piece.setCurrentPosition(position);
            board.addPieceAt(this.piece, position);
            board.updateMills();

        }
        return null;
    }

    public Piece getPiece() {
        return piece;
    }
}
