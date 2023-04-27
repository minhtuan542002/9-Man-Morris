package action;

import game.Board;
import piece.Piece;
import game.Position;

public class MovePieceMove implements Move {
    private Piece piece;
    public MovePieceMove(Piece piece){
        this.piece =piece;
    }
    @Override
    public String execute(Piece piece, Board board, Position position) {

        this.piece.setCurrentPosition(position);
        board.addPieceAt(this.piece, position);
        board.removePiece(this.piece);
        return null;
    }

}
