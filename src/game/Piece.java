
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Piece extends JButton {
    int xp;
    int yp;
    boolean isRed;

    int tileSize;


    Position currentPosition;
    GamePanel gp;
    public BufferedImage tokenPiece;

    public Piece(Position position, PieceColour pieceColour){
        this.isRed = pieceColour == PieceColour.RED;
        getPieceImage();
    }
    public void getPieceImage() {
        try {
            tileSize =16*5;
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

    public void setCurrentPosition(Position position){this.currentPosition=position;}

    public void toggleColour(){
        this.isRed = !this.isRed;
    }

    public void draw(Graphics g) {
        BufferedImage image = tokenPiece;
        g.drawImage(image, xp, yp, gp.tileSize, gp.tileSize, null);
    }

    public void draw(Graphics g, int x_position, int y_position) {
        BufferedImage image = tokenPiece;
        g.drawImage(image, x_position, y_position, tileSize, tileSize, null);
    }
}
