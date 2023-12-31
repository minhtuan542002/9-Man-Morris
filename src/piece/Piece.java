package piece;
import game.Board;
import status.*;
import game.Position;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * The class representing pieces in the 9 Man's Morris game
 */
public class Piece extends JLabel implements State{
    /**
     * The colour of the piece. True if red and false if blue
     */
    private boolean isRed;
    /**
     * The set of piece's statuses
     */
    private final StatusSet statusSet = new StatusSet();

    /**
     * The size of piece displayed on the board
     */
    private final int tileSize;

    /**
     * The Jlabel containing the piece's image
     */
    private JLabel pieceImage;
    /**
     * Corresponding position of the piece. Can be null when it is not on the board
     */
    private Position currentPosition;
    /**
     * Image of the piece in BufferedImage format
     */
    private BufferedImage tokenPiece;

    /**
     * Constructor method for piece
     * @param position
     * @param status
     */
    public Piece(Position position, Status status){
        tileSize =70;
        setSize(tileSize,tileSize);
        currentPosition=position;
        this.isRed = status == Status.RED;
        statusSet.addStatus(Status.OUTSIDE_MILL);
        statusSet.addStatus(status);
        //setBackground(Color.PINK);
        setOpaque(false);
        parsePieceImage();

    }
    private void parsePieceImage() {
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

    public JLabel getPieceImage() {
        return pieceImage;
    }

    public boolean isRed() {
        return isRed;
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

    public Boolean hasAvailableMove(Board board){
        List<Position> availbaleMove = currentPosition.getAdjacentPositions(board);
        for (Position position: availbaleMove){
            if (!board.hasPieceAt(position)){
                return true;
            }
        }
        return false;
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
