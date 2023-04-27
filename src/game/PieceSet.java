package game;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PieceSet extends JPanel {
    List<Piece> pieceSet = new ArrayList<>();

    PieceSet(PieceColour pieceColour){
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
                piece.draw(g, 0, i*piece.tileSize);
            }
        }
    }

    public Piece useOnePiece(){
        Piece piece = pieceSet.get(pieceSet.size()-1);
        pieceSet.remove(pieceSet.size()-1);
        return piece;
    }

}
