package src.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Piece {
    int xp;
    int yp;
    boolean isRed;


    Position currentPosition;
    GamePanel gp;
    public BufferedImage tokenPiece;

    public Piece(GamePanel gp){
        this.gp = gp;
        this.xp = gp.tileSize;
        this.yp = gp.tileSize;
        this.isRed = true;
        this.currentPosition = new Position(1,2);
        getPieceImage();

    }

    public Piece(GamePanel gp, int x, int y, int colour){
        this.currentPosition = new Position(x,y);
        this.gp = gp;

        this.xp = x * gp.tileSize;
        this.yp = y * gp.tileSize;

        this.isRed = colour != 1;

        getPieceImage();
    }

    public void getPieceImage() {
        try {
            if(this.isRed){
                tokenPiece = ImageIO.read(new File("src/image/red_token.png"));
            }
            else{
                tokenPiece = ImageIO.read(new File("src/image/blue_token.png"));
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }


    public void toggleColour(){
        this.isRed = !this.isRed;
    }

    public void draw(Graphics g) {
        BufferedImage image = tokenPiece;
        g.drawImage(image, xp, yp, gp.tileSize, gp.tileSize, null);
    }
}
