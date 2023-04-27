package action;

import game.Board;
import piece.Piece;
import game.Position;

public class MovePieceMove implements Move {
    @Override
    public String execute(Piece piece, Board board, Position position) {
        board.removePiece(piece);
        piece.setCurrentPosition(position);
        board.addPieceAt(piece, position);
        return null;
    }

}
