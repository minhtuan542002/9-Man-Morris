
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // Tile
    final int originalTileSize = 16; // Tile size
    final int scale = 3; // Tile scale
    final int tileSize = originalTileSize * scale;

    // 23 x 13 square
    final int maxScreenCol = 23;
    final int maxScreenRow = 13;

    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    Thread gameThread;
    PieceSet pieceSet = new PieceSet(PieceColour.BLUE);

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
    }

    public void setGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while(gameThread != null) {
            System.out.println("Game loop is running");
            // UPDATE:
            update();

            // DRAW:
            repaint();
        }
    }

    public void update(){
    }


}
