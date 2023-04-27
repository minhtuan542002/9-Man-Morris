package Move;


import Piece.Piece;
import game.Position;
import game.Board;

public interface Move {
    void moveAdjancent(Position position, Piece piece, Board board);

    void removePiece(Piece piece, Board board);

    void fly(Position position, Piece piece, Board board);

}
