
import java.util.Map;

public  class Board {
    private Map<Position, Piece> mapping =null;


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
}