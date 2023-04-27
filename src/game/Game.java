package game;
import action.Move;
import action.MovePieceMove;
import piece.*;
import player.*;
import status.Status;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;

/**
 * The class used to process and run all aspects of the game
 */
public class Game implements ActionListener {
    /**
     * The frame used to display the game
     */
    private JFrame frame = null;
    /**
     * Game's board class object
     */
    private Board board =null;
    /**
     * The display that will provide the game with graphic interaction
     */
    private Display display =null;
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
    private boolean running =false;

    /**
     * The red player's turn
     */
    private Boolean isRedTurn =true;
    /**
     * The Piece set containing all red pieces outside the board but not removed from the game
     */
    private PieceSet red_piece_panel =null;

    /**Current phrase of the game
     *
     */
    private Status gamePhase;


    /**
     * The Piece set containing all blue pieces outside the board but not removed from the game
     */
    private PieceSet blue_piece_panel = null;

    /**
     * The current Move to be processed
     */
    private Move currentMove =null;

    /**
     * Constructor method for Game, which create the obeject instance and create new game resources
     */
    public Game(){
        board = new Board();
        display = new Display();
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        gamePhase = Status.PHASE_1;
    }

    /**
     * Set up the game resources for the game start
     */
    public void init(){
        board.init(this);
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Nine Man's Morris");
        frame.setBackground(Color.WHITE);

        red_piece_panel= new PieceSet(Status.RED);

        blue_piece_panel= new PieceSet(Status.BLUE);


        display.init();
        frame.add(display);
        JPanel container =new JPanel();

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
    public void update(){

    }

    /**
     * The main game loop
     */
    public void run(){
        init();
        running=true;
        while (running){
            //System.out.println("Game loop is running");
            update();
            display.repaint();
        }
        frame.dispose();
    }

    /**
     * Change current turn
     */
    private void toggleTurn(){
        isRedTurn= !isRedTurn;
    }

    /**
     * Process the newest event based on the interaction of the users toward the interface
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for(Map.Entry<Point, Position> entry : board.positions.entrySet()){
            if(entry.getValue()==e.getSource()){
                Piece piece;
                //System.out.println(entry.getKey());
                if (gamePhase == Status.PHASE_1) {

                    if (isRedTurn) {
                        piece = red_piece_panel.useOnePiece();
                    } else piece = blue_piece_panel.useOnePiece();
                    piece.setCurrentPosition(entry.getValue());
                    board.addPieceAt(piece, entry.getValue());

                    toggleTurn();
                    if (red_piece_panel.getPieceSetSize() == 0 && blue_piece_panel.getPieceSetSize() == 0) {
                        gamePhase = Status.PHASE_2;
                        System.out.println(board.positions);
                    }
                }
                else if (gamePhase == Status.PHASE_2){
                    /*
                    System.out.println("Phase 2 starts");
                    System.out.println(entry.getValue().getLayer());
                    System.out.println(entry.getValue().getPositionNumber());
                    System.out.println(entry.getValue().getLocation());
                    System.out.println(board.getPiece(entry.getValue()).getLocation());
                    System.out.println(board.getPiece(entry.getValue()).getCurrentPosition().getLocation());
                    //targetPiece = board.getPiece(entry.getValue());
                    if (isRedTurn){
                        Action action = new RemovePieceAction();
                        action.execute(targetPiece,board,entry.getValue());
                    }else {
                        Action action = new RemovePieceAction();
                        action.execute(targetPiece,board,entry.getValue());
                    }
                    */
                    piece = board.getPiece(entry.getValue());
                    if(board.hasPieceAt(entry.getValue())) {
                        currentMove = new MovePieceMove(piece);

                    }
                    else {
                        if (currentMove != null) {
                            currentMove.execute(piece, board, entry.getValue());
                            currentMove =null;
                        }
                    }

                    if (board.getNumberOfBluePieces() == 3){
                        player2.addStatus(Status.ACTIVE_FLY);
                        gamePhase = Status.PHASE_3;
                    } else if (board.getNumberOfRedPieces() == 3){
                        player1.hasStatus(Status.ACTIVE_FLY);
                        gamePhase = Status.PHASE_3;
                    }
                    toggleTurn();
                } else if (gamePhase == Status.PHASE_3){
                    System.out.println("Phase 3 starts");


                }

            }
        }
    }


}
