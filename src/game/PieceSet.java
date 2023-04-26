
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PieceSet {
    List<Piece> pieceSet = new ArrayList<>();

    PieceSet(GamePanel gp){
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 12; y++) {
                Piece piece = new Piece(gp,x,y, x);
                this.pieceSet.add(piece);
            }
        }
    }

    public void draw(Graphics g){
        if (pieceSet != null){
            for(Piece piece : pieceSet){
                piece.draw(g);
            }
        }
    }

}
