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

public class Game implements ActionListener {
    private JFrame frame = null;
    private Board board =null;
    private Display display =null;
    private Player player1;
    private Player player2;

    private boolean running =false;

    private Boolean isRedTurn =true;
    private PieceSet red_piece_panel =null;
    private Status gamePhase;
    private Piece targetPiece = null;

    private PieceSet blue_piece_panel = null;

    private Move currentMove =null;

    public Game(){
        board = new Board();
        display = new Display();
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        gamePhase = Status.PHASE_1;
    }
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
    public void update(){

    }

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

    private void toggleTurn(){
        isRedTurn= !isRedTurn;
    }

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
                    /**
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
                        if (currentMove == null) {
                            currentMove = new MovePieceMove(piece);
                        }
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
