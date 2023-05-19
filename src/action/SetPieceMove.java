package action;

import game.Board;
import game.Position;
import piece.Piece;
/**
 * Place Piece on Board
 * Created by 
 * @author Thanh Nguyen
 * Modified by: 
 * */
public class SetPieceMove implements Move{
    @Override
    public String execute(Piece piece, Board board, Position position) {
        piece.setCurrentPosition(position);
        board.addPieceAt(piece, position);
        board.updateMills();
        return null;
    }

    @Override
    public Piece getPiece() {
        return null;
    }
}
