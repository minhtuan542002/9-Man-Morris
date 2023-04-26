package Move;


import game.Piece;
import game.Position;
import game.Board;

public interface Move {
    void moveTo(Position position, Piece piece);

    void placePiece(Position position, Piece piece);
    void removePiece(Piece piece, Board board);

    void fly(Position position, Piece piece);

}
