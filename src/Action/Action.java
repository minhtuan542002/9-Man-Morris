package Action;

import game.Board;
import Piece.Piece;
import game.Position;

public abstract class Action {
    public abstract String execute(Piece piece, Board board, Position position);
    /**
     * Perform the Action.
     *
     * @param piece The piece performing the action.
     * @param board The game board piece is on.
     * @return a description of what happened that can be displayed to the user. (For keeping track of action)
     */
}
