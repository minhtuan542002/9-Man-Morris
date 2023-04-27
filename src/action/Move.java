package action;

import piece.Piece;
import game.Board;
import game.Position;

public interface Move {
    public String execute(Piece piece, Board board, Position position);
    /**
     * Action Execution method
     *
     * @param piece The piece performing the action.
     * @param board The game board piece is on.
     * @return a description of what happened that can be displayed to the user. (For keeping track of action)
     */
}