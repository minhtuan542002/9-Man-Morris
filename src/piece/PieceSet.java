package piece;
import status.Status;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The set of piece waiting to be placed on the board in phrase 1 and is graphic visual
 */
public class PieceSet extends JPanel {
    /**
     * The ArrayList containing the unused pieces
     */
    private List<Piece> pieceSet = new ArrayList<>();

    /**
     * Constructor method for piece set, create new pieces for the game
     * @param pieceColour The color of the piece set and pieces inside
     */
    public PieceSet(Status pieceColour){
        for (int i = 0; i < 9; i++) {
            Piece piece = new Piece(null, pieceColour);
            this.pieceSet.add(piece);
        }
        setPreferredSize(new Dimension(100, 800));
        setBackground(Color.WHITE);
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        if (pieceSet != null){
            for(int i =0; i< pieceSet.size(); i++){
                Piece piece =pieceSet.get(i);
                piece.draw(g, 0, i*70);
            }
        }
    }

    /**
     * Get a piece in the piece to place it on the board
     * @return the last piece on the piece Set
     */
    public Piece useOnePiece(){
        Piece piece = pieceSet.get(pieceSet.size()-1);
        pieceSet.remove(pieceSet.size()-1);
        return piece;
    }

    /**
     * Calculate the remaining piece count inside the piece set
     * @return Number of remaining pieces
     */
    public int getPieceSetSize(){
        return pieceSet.size();
    }
}
