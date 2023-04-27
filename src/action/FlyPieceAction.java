package Action;

import Piece.Piece;
import game.Board;
import game.Position;

public class FlyPieceAction implements Action{
    @Override
    public String execute(Piece piece, Board board, Position position) {
        if (!board.hasPieceAt(position)){
            board.addPieceAt(piece,position);
            board.removePiece(piece.getCurrentPosition(), piece);
        }

        return null;
    }
}
