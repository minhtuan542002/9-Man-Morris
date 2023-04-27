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
    public void moveTo(Position position, Piece piece) {

    }

    @Override
    public void placePiece(Position position, Piece piece) {

    }

    @Override
    public void removePiece(Piece piece, Board board) {

    }

    @Override
    public void fly(Position position, Piece piece) {

    }


}
