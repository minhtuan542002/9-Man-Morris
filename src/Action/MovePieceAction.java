package Action;

import Move.Move;
import game.Board;
import Piece.Piece;
import game.Position;

public class MovePieceAction extends Action implements Move {
    @Override
    public String execute(Piece piece, Board board) {
        return null;
    }

    @Override
    public void moveAdjancent(Position position, Piece piece, Board board) {
        /* List<Position> temp = piece.getCurrentPosition().availableMove();
        if (temp.contains(position)){
            piece.setCurrentPosition(position);
        }*/
    }


    @Override
    public void removePiece(Piece piece, Board board) {
        board.removePiece(piece.getCurrentPosition(),piece);
    }

    @Override
    public void fly(Position position, Piece piece, Board board) {

    }
}
