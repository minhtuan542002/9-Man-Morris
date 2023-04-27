package game;

import piece.Piece;
import status.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.round;

public  class Board extends JPanel {
    final int BOARD_LENGTH = 800;

    final int BUTTON_SIZE = 70;

    final int LAYER_DISTANCE = 110;

    private JLayeredPane layeredPane;
    private Map<Position, Piece> mapping = new HashMap<>();

    private ImageIcon boardImage = getImageIcon();

    public Map<Point, Position> positions = new HashMap<>();



    public Board(){
        //setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(800, 800));
        setBackground(Color.WHITE);
        FlowLayout layout = (FlowLayout)this.getLayout();
        layout.setVgap(0);
        layout.setHgap(0);
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 800));
        layeredPane.setBorder(BorderFactory.createEmptyBorder());
    }

    public Boolean hasPieceAt(Position position){
        if(mapping.get(position)==null) return false;
        else return  true;

    }

    public void init(Game game){
        JLabel boardImageLabel =new JLabel(boardImage);
        layeredPane.add(boardImageLabel,3);
        boardImageLabel.setBounds(0, 0, BOARD_LENGTH, BOARD_LENGTH);

        int cor_layer = 0;
        for(int layer =0; layer <3; layer++){
            cor_layer = layer*LAYER_DISTANCE;
            for (int i =0; i<9; i++){
                Position position = new Position(layer,i);
                layeredPane.add(position,2);

                switch (i) {
                    case 0:
                        position.setBounds(cor_layer, cor_layer, BUTTON_SIZE, BUTTON_SIZE);
                        break;
                    case 1:
                        position.setBounds((round(BOARD_LENGTH-BUTTON_SIZE)/2), cor_layer, BUTTON_SIZE, BUTTON_SIZE);
                        break;
                    case 2:
                        position.setBounds(BOARD_LENGTH-cor_layer-BUTTON_SIZE, cor_layer, BUTTON_SIZE, BUTTON_SIZE);
                        break;
                    case 3:
                        position.setBounds(BOARD_LENGTH-cor_layer-BUTTON_SIZE, round((BOARD_LENGTH-BUTTON_SIZE)/2), BUTTON_SIZE, BUTTON_SIZE);
                        break;
                    case 4:
                        position.setBounds(BOARD_LENGTH-cor_layer-BUTTON_SIZE, BOARD_LENGTH-cor_layer-BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE);
                        break;
                    case 5:
                        position.setBounds(round((BOARD_LENGTH-BUTTON_SIZE)/2), BOARD_LENGTH-cor_layer-BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE);
                        break;
                    case 6:
                        position.setBounds(cor_layer, BOARD_LENGTH-cor_layer-BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE);
                        break;
                    case 7:
                        position.setBounds(cor_layer, round((BOARD_LENGTH-BUTTON_SIZE)/2), BUTTON_SIZE, BUTTON_SIZE);
                        break;
                }
                position.addActionListener(game);
                positions.put(new Point(layer, i), position);
            }
        }
        //Add control pane and layered pane to this JPanel.
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(layeredPane);
        //System.out.println(getPositions());
    }

    public Boolean containPosition(Position position){
        if (!positions.containsValue(position)){
            return false;
        }
        return true;
    }

    public Map<Point, Position> getPositions() {
        return positions;
    }

    public Piece getPiece(Position position){
        return mapping.get(position);
    }

    public void removePiece(Piece piece){
        if(piece.getCurrentPosition() == null){
            throw new RuntimeException("Piece do ot have position");
        }
        mapping.remove(piece.getCurrentPosition(), piece);
    }


    public void addPieceAt(Piece piece, Position position){
        mapping.put(position, piece);
        //positionButtons.remove(position);
        piece.setCurrentPosition(position);
        layeredPane.add(piece,1);
        layeredPane.add(piece.pieceImage, 0);
        piece.setBounds(position.getBounds());
        piece.pieceImage.setBounds(position.getBounds());
        System.out.println(mapping.size());
        //if()
    }

    public ImageIcon getImageIcon() {
        if(new ImageIcon("src/image/Board.png")==null)System.out.println("HH");
        return new ImageIcon("src/image/Board.png");


    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        //g.drawImage(boardImage, 0, 0, 800, 800,null);
        for(Map.Entry<Position, Piece> entry : mapping.entrySet()){
            Point location = entry.getValue().getLocation();
            //System.out.println(location);
            entry.getValue().draw(g, location.x, location.y);
        }

    }

    public int getNumberOfRedPieces (){
        int redNo=0;
        for (Map.Entry<Position,Piece> i: mapping.entrySet()){
            if (i.getValue().hasStatus(Status.RED)){
                redNo +=1;
            }
        }
        return redNo;
    }

    public int getNumberOfBluePieces (){
        int blueNo=0;
        for (Map.Entry<Position,Piece> i: mapping.entrySet()){
            if (i.getValue().hasStatus(Status.BLUE)){
                blueNo +=1;
            }
        }
        return blueNo;
    }

}