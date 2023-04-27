import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class Game implements ActionListener {
    private JFrame frame = null;
    private Board board =null;
    private Display display =null;

    private Player player=null;

    private boolean running =false;

    private Boolean isRedTurn =true;
    private PieceSet red_piece_panel =null;

    private PieceSet blue_piece_panel = null;
    public Game(){
        board = new Board();
        display = new Display();
        player = new Player();
    }
    public void init(){
        board.init(this);
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
            //System.out.println("Game loop is running");
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
