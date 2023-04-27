package action;

import piece.Piece;
import status.Status;
import game.Board;
import game.Position;

public class RemovePieceMove implements Move {
    @Override
    public String execute(Piece piece, Board board, Position position) {
        String result = "";
        if (!piece.hasStatus(Status.IN_MILL)){
            board.removePiece(piece);
            result += "Remove 1 piece";
        }
        return result;
    }
}
