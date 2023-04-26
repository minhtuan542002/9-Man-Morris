
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

    public JLabel pieceImage;

    Position currentPosition;
    GamePanel gp;
    public BufferedImage tokenPiece;

    public Piece(Position position, PieceColour pieceColour){
        tileSize =70;
        setSize(tileSize,tileSize);
        this.isRed = pieceColour == PieceColour.RED;
        setBackground(Color.PINK);
        getPieceImage();

    }
    public void getPieceImage() {
        try {
            if(this.isRed){
                tokenPiece = ImageIO.read(new File("src/image/red_token.png"));
                pieceImage = new JLabel(new ImageIcon("src/image/red_token.png"));
            }
            else{
                tokenPiece = ImageIO.read(new File("src/image/blue_token.png"));
                pieceImage = new JLabel(new ImageIcon("src/image/red_token.png"));
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void setCurrentPosition(Position position){this.currentPosition=position;}

    public void toggleColour(){
        this.isRed = !this.isRed;
    }


    public void draw(Graphics g, int x_position, int y_position) {
        BufferedImage image = tokenPiece;
        g.drawImage(image, x_position, y_position, tileSize, tileSize, null);
    }
}
