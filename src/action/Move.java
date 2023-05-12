package action;

import piece.Piece;
import game.Board;
import game.Position;
/**
 * Move Piece adjacently
 * Created by 
 * @author Thanh Nguyen
 * Modified by: 
 * */
public interface Move {
    /**
     * Action Execution method
     *
     * @param piece The piece performing the action.
     * @param board The game board piece is on.
     * @return a description of what happened that can be displayed to the user. (For keeping track of action)
     */
    public String execute(Piece piece, Board board, Position position);

    public Piece getPiece();
}
