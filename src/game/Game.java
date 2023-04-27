package game;
import Piece.*;
import Player.*;
import Status.Status;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

public class Game implements ActionListener {
    private JFrame frame = null;
    private Board board =null;
    private Player_1 player1;
    private Player_2 player2;







    private JPanel panelHomeBase = new JPanel();

    // Initiating startPanel instance
    private StartPanel startPanel = new StartPanel();

    // Initiating gamePanel instance
    private Display display =null;

    // Creating custom start button (JLabel)
    private CustomButton customJLabelStartButton = new CustomButton("src/image/startButton.png");
    private JLabel customStartButton = customJLabelStartButton.getCustomButton();

    // Creating custom back button (JLabel)
    private CustomButton customJLabelBackButton = new CustomButton("src/image/backButton.png");
    private JLabel customBackButton = customJLabelBackButton.getCustomButton();

    // Initiating Card Layout for JFrame
    private CardLayout cardLayout = new CardLayout();















    private boolean running =false;

    private Boolean isRedTurn =true;
    private PieceSet red_piece_panel =null;

    private PieceSet blue_piece_panel = null;
    public Game(){
        board = new Board();
        display = new Display();
        player1 = new Player_1("Player 1");
        player2 = new Player_2("Player 2");
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


        panelHomeBase.setLayout(cardLayout);

        startPanel.add(customStartButton);
        display.add(customBackButton);

        panelHomeBase.add(startPanel,"1");
        panelHomeBase.add(display,"2");


        customStartButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(panelHomeBase, "2");
            }
        });


        customBackButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(panelHomeBase, "1");
            }
        });

        frame.add(panelHomeBase);
        JPanel container =new JPanel();

        container.add(red_piece_panel);
        container.add(board);
        container.add(blue_piece_panel);
        container.add(customBackButton);


        display.add(container);


        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //display.setGameThread();
    }

    public void run(){
        init();
        running=true;
        while (running){
            //System.out.println("Game loop is running");
            display.repaint();
        }
    }

    private void toggleTurn(){
        isRedTurn= !isRedTurn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(Map.Entry<Point, Position> entry : board.positions.entrySet()){
            if(entry.getValue()==e.getSource()){
                System.out.println(entry.getKey());
                Piece piece;
                if(isRedTurn){
                    piece =red_piece_panel.useOnePiece();
                }
                else piece =blue_piece_panel.useOnePiece();
                piece.setCurrentPosition(entry.getValue());
                board.addPieceAt(piece, entry.getValue());

                toggleTurn();
            }
        }
    }
}
