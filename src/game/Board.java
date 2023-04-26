
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public  class Board extends JPanel {
    private Map<Position, Piece> mapping =null;

    private Image boardImage;

    public Board(){
        getBoardImage();
    }

    public Boolean hasPieceAt(Position position){
        if(mapping.get(position)==null) return false;
        else return  true;
    }

    public Piece getPiece(Position position){
        return mapping.get(position);
    }

    public void addPieceAt(Piece piece, Position position){
        mapping.put(position, piece);
    }

    public void getBoardImage() {
        try {
            boardImage = ImageIO.read(new File("src/image/Board.png"));

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(boardImage, 0, 0, 800, 800,null);
    }
}