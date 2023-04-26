import javax.swing.*;
import java.awt.*;

public class Game {
    private JFrame frame = null;
    private Board board =null;
    private Display display =null;

    private boolean running =false;

    private Boolean isRedTurn =true;
    private JPanel red_piece_panel =null;
    private JPanel text_panel =null;
    private JPanel blue_piece_panel = null;
    public Game(){
        board = new Board();
        display = new Display();

    }
    public void init(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Nine Man's Morris");

        red_piece_panel= new PieceSet(PieceColour.RED);

        blue_piece_panel= new PieceSet(PieceColour.BLUE);

        frame.add(display);
        display.add(red_piece_panel);
        display.add(board);
        display.add(blue_piece_panel);


        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //display.setGameThread();
    }

    public void run(){
        init();
        running=true;
        while (running){
            System.out.println("Game loop is running");
            display.repaint();
        }
    }
}
