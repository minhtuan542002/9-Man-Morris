package Action;

import Piece.Piece;
import Status.Status;
import game.Board;
import game.Position;

import java.awt.*;

public class RemovePieceAction extends Action{
    @Override
    public String execute(Piece piece, Board board, Position position) {
        String result = "";
        if (!piece.hasStatus(Status.IN_MILL)){
            board.removePiece(position,piece);
            result += "Remove 1 piece";
        }
        return result;
    }
}
