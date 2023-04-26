package Action;

import game.Board;
import game.Piece;

public abstract class Action {
    public abstract String execute(Piece piece, Board board);
    /**
     * Perform the Action.
     *
     * @param piece The piece performing the action.
     * @param board The game board piece is on.
     * @return a description of what happened that can be displayed to the user. (For keeping track of action)
     */
//    public abstract String execute(Piece piece, Board board);
}
