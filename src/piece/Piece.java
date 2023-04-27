package piece;
import status.*;
import game.Position;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Piece extends JLabel implements State{
    private boolean isRed;
    private final StatusSet statusSet = new StatusSet();

    private int tileSize;

    public JLabel pieceImage;

    private Position currentPosition;
    public BufferedImage tokenPiece;

    public Piece(Position position, Status status){
        tileSize =70;
        setSize(tileSize,tileSize);
        this.isRed = status == Status.RED;
        statusSet.addStatus(Status.OUTSIDE_MILL);
        //setBackground(Color.PINK);
        setOpaque(false);
        getPieceImage();

    }
    private void getPieceImage() {
        try {
            if(this.isRed){
                statusSet.addStatus(Status.RED);
                tokenPiece = ImageIO.read(new File("src/image/red_token.png"));
                pieceImage = new JLabel(new ImageIcon("src/image/red_token.png"));
            }
            else{
                statusSet.addStatus(Status.BLUE);
                tokenPiece = ImageIO.read(new File("src/image/blue_token.png"));
                pieceImage = new JLabel(new ImageIcon("src/image/blue_token.png"));
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void setCurrentPosition(Position position){this.currentPosition=position;}

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void toggleColour(){
        this.isRed = !this.isRed;
    }


    public void draw(Graphics g, int x_position, int y_position) {
        BufferedImage image = tokenPiece;
        g.drawImage(image, x_position, y_position, tileSize, tileSize, null);
    }

    public List<Position> availableMove(){
        List<Position> temp = new ArrayList<>();
        return temp;
    }

    @Override
    public boolean hasStatus(Enum<?> status) {
        return statusSet.hasStatus(status);
    }

    @Override
    public void addStatus(Enum<?> status) {
        statusSet.addStatus(status);
    }
    @Override
    public void removeStatus(Enum<?> status) {
        statusSet.removeStatus(status);
    }
    @Override
    public List<Enum<?>> statusList() {
        return statusSet.statusList();
    }

}
