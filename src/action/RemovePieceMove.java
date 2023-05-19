package action;

import piece.Piece;
import status.Status;
import game.Board;
import game.Position;
/**
 * Remove Piece form Board
 * Created by 
 * @author Thanh Nguyen
 * Modified by: 
 * */
public class RemovePieceMove implements Move {
    private Piece piece;
    public RemovePieceMove(Piece piece){
        this.piece =piece;
    }
    @Override
    public String execute(Piece piece, Board board, Position position) {
        String result = "";
        if (!piece.hasStatus(Status.IN_MILL)){
            board.removePiece(piece);
            piece.setCurrentPosition(null);
            result += "Remove 1 piece";
        }
        return result;
    }

    @Override
    public Piece getPiece() {
        return null;
    }
}
