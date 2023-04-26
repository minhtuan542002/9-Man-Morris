import javax.swing.*;
import java.awt.*;

public class Game {
    private JFrame frame = null;
    private Board board =null;
    private Display display =null;

    private boolean running =false;

    private Boolean isRedTurn =true;
    private JPanel red_piece_panel =null;

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
        frame.setBackground(Color.WHITE);

        red_piece_panel= new PieceSet(PieceColour.RED);

        blue_piece_panel= new PieceSet(PieceColour.BLUE);


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

    public void run(){
        init();
        running=true;
        while (running){
            System.out.println("Game loop is running");
            display.repaint();
        }
        frame.dispose();
    }
}
