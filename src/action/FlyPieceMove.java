package action;

import piece.Piece;
import game.Board;
import game.Position;

public class FlyPieceMove implements Move {
    @Override
    public String execute(Piece piece, Board board, Position position) {
        if (!board.hasPieceAt(position)){
            board.addPieceAt(piece,position);
            board.removePiece(piece);
        }

        return null;
    }
}
