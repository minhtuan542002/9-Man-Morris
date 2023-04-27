package piece;
import status.Status;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PieceSet extends JPanel {
    List<Piece> pieceSet = new ArrayList<>();

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

    public Piece useOnePiece(){
        Piece piece = pieceSet.get(pieceSet.size()-1);
        pieceSet.remove(pieceSet.size()-1);
        return piece;
    }

    public int getPieceSetSize(){
        return pieceSet.size();
    }
}
