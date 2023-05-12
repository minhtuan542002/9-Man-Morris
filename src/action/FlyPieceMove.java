package action;

import piece.Piece;
import game.Board;
import game.Position;
/**
 * Used in phase 3. Move piece to any empty position
 * Created by 
 * @author Thanh Nguyen
 * Modified by: 
 * */
public class FlyPieceMove implements Move {
    @Override
    public String execute(Piece piece, Board board, Position position) {
        if (!board.hasPieceAt(position)){
            board.addPieceAt(piece,position);
            board.removePiece(piece);
        }

        return null;
    }

    @Override
    public Piece getPiece() {
        return null;
    }
}
