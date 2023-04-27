package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StartPanel extends JPanel{
    // Tile
    final int originalTileSize = 16; // Tile size
    final int scale = 3; // Tile scale
    final int tileSize = originalTileSize * scale;

    // 23 x 13 square
    final int maxScreenCol = 32;
    final int maxScreenRow = 18;

    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    private boolean gameStart;

    public BufferedImage backgroundImage;



    public StartPanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.gameStart = false;

        getMenuBackground();
    }

    public StartPanel getMenuForReal(){
        return this;
    }

    public void getMenuBackground() {
        try {
            backgroundImage = ImageIO.read(new File("src/image/background.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }


    public boolean getGameState(){
        return this.gameStart;
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(this.backgroundImage, 0, 0, this.getWidth(), this.getHeight(), null);

    }
}