package status;

public enum Status {
    /**
     * Is on Red side
     */
    RED,
    /**
     * Is on blue side
     */
    BLUE,
    /**
     * This piece is in a Mill
     */
    IN_MILL,
    /**
     * Is not in a Mill
     */
    OUTSIDE_MILL,
    /**
     * Is a human player
     */
    HUMAN,
    /**
     * Is a computer player
     */
    COMPUTER,
    /**
     * Is in first phrase of the game (Setting pieces)
     */
    PHASE_1,
    /**
     * Is in second phrase of the game (Moving pieces)
     */
    PHASE_2,
    /**
     * Is used in third phrase of the game (Flying Pieces)
     * */
    ACTIVE_FLY,
    PHASE_3,
    GAME_OVER
}
