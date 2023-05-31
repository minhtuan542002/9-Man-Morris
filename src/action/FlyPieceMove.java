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
    private Piece piece;
    public FlyPieceMove(Piece piece) {
        this.piece = piece;
    }

    @Override
    public String execute(Piece piece, Board board, Position position) {
        Position oldPosition =piece.getCurrentPosition();
        this.piece.setCurrentPosition(position);
        board.addPieceAt(this.piece, position);
        board.removePiece(this.piece, oldPosition);
        board.addPieceAt(this.piece, position);

        board.updateMills();

        return null;
    }

    @Override
    public Piece getPiece() {
        return piece;
    }
}
