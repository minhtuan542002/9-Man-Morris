
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import static java.lang.Math.round;

public  class Board extends JPanel {
    final int BOARD_LENGTH = 800;

    final int BUTTON_SIZE = 70;

    final int LAYER_DISTANCE = 110;
    private Map<Position, Piece> mapping = new HashMap<>();

    private Image boardImage;

    public Map<Point, Position> positions = new HashMap<>();

    JPanel positionButtons = null;

    public Board(){
        getBoardImage();
        setPreferredSize(new Dimension(800, 800));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder());
        FlowLayout layout = (FlowLayout)this.getLayout();
        layout.setVgap(0);
        layout.setHgap(0);
    }

    public Boolean hasPieceAt(Position position){
        if(mapping.get(position)==null) return false;
        else return  true;

    }

    public void init(Game game){
        positionButtons =new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        positionButtons.setOpaque(false);
        positionButtons.setPreferredSize(new Dimension(800, 800));
        positionButtons.setLayout(null);
        positionButtons.setBorder(BorderFactory.createEmptyBorder());
        int cor_layer = 0;
        for(int layer =0; layer <3; layer++){
            cor_layer = layer*LAYER_DISTANCE;
            for (int i =0; i<9; i++){
                Position position = new Position(layer,i);
                positionButtons.add(position);
                System.out.println(cor_layer);
                System.out.println((BOARD_LENGTH-BUTTON_SIZE)/2);
                System.out.println(BOARD_LENGTH-cor_layer-BUTTON_SIZE);

                switch (i) {
                    case 0:
                        position.setLocation(cor_layer, cor_layer);
                        break;
                    case 1:
                        position.setLocation((round(BOARD_LENGTH-BUTTON_SIZE)/2), cor_layer);
                        break;
                    case 3:
                        position.setLocation(BOARD_LENGTH-cor_layer-BUTTON_SIZE, cor_layer);
                        break;
                    case 4:
                        position.setLocation(BOARD_LENGTH-cor_layer-BUTTON_SIZE, round((BOARD_LENGTH-BUTTON_SIZE)/2));
                        break;
                    case 5:
                        position.setLocation(BOARD_LENGTH-cor_layer-BUTTON_SIZE, BOARD_LENGTH-cor_layer-BUTTON_SIZE);
                        break;
                    case 6:
                        position.setLocation(round((BOARD_LENGTH-BUTTON_SIZE)/2), BOARD_LENGTH-cor_layer-BUTTON_SIZE);
                        break;
                    case 7:
                        position.setLocation(cor_layer, BOARD_LENGTH-cor_layer-BUTTON_SIZE);
                        break;
                    case 8:
                        position.setLocation(cor_layer, round((BOARD_LENGTH-BUTTON_SIZE)/2));
                        break;
                }
                position.setSize(70,70);
                position.addActionListener(game);
                positions.put(new Point(layer, i), position);
            }
        }
        add(positionButtons);
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