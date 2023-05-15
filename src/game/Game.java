package game;
import action.Move;
import piece.*;
import player.*;
import status.Status;


import javax.swing.*;
import java.awt.*;

/**
 * The class used to process and run all aspects of the game
 */
public class Game {
    /**
     * The frame used to display the game
     */
    private JFrame frame = null;
    /**
     * Game's board class object
     */
    private Board board = null;
    /**
     * The display that will provide the game with graphic interaction
     */
    private Display display = null;
    /**
     * First player (red)
     */
    private Player player1;
    /**
     * second player (blue)
     */
    private Player player2;

    /**
     * The state of the game. True if the game is running, false otherwise.
     */
    private boolean running = false;

    /**
     * The red player's turn
     */
    private Boolean isRedTurn = true;
    /**
     * The Piece set containing all red pieces outside the board but not removed from the game
     */
    private PieceSet red_piece_panel = null;

    /**
     * Current phrase of the game
     */
    //private Status gamePhase;


    /**
     * The Piece set containing all blue pieces outside the board but not removed from the game
     */
    private PieceSet blue_piece_panel = null;

    /**
     * The current Move to be processed
     */
    //private Move currentMove = null;

    /**
     * Constructor method for Game, which create the obeject instance and create new game resources
     */
    public Game() {
        board = new Board();
        display = new Display();

        //gamePhase = Status.PHASE_1;
    }

    /**
     * Set up the game resources for the game start
     */
    public void init() {
        board.init();
        board.updatePlayer(player1);
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Nine Man's Morris");
        frame.setBackground(Color.WHITE);

        red_piece_panel = new PieceSet(Status.RED);

        blue_piece_panel = new PieceSet(Status.BLUE);
        player1 = new Player("Red Player", board, red_piece_panel);
        player2 = new Player("Blue Player", board, blue_piece_panel);
        player1.newTurn();


        display.init();
        frame.add(display);
        JPanel container = new JPanel();

        container.add(red_piece_panel);
        container.add(board);
        container.add(blue_piece_panel);


        display.add(container);


        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //display.setGameThread();
    }

    /**
     * Update the game states and resources
     */
    public void update() {
        if(isRedTurn){
            if(player1.finishedTurn()){
                toggleTurn();
                player2.newTurn();
            }
        }
        else {
            if(player2.finishedTurn()){
                toggleTurn();
                player1.newTurn();
            }
        }
    }

    /**
     * The main game loop
     */
    public void run() {
        init();
        running = true;
        while (running) {
            //System.out.println("Game loop is running");
            update();
            display.repaint();
        }
        frame.dispose();
    }

    /**
     * Change current turn
     */
    private void toggleTurn() {
        isRedTurn = !isRedTurn;
    }
    /*
    public Status getGamePhase() {
        return gamePhase;
    }

    */
}

