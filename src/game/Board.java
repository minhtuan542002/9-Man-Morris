package game;

import piece.Piece;
import player.Player;
import status.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.round;

/**
 * The class that logically and visually implement 9 Man's Morris game board
 *
 */
public  class Board extends JPanel {
    /**
     * Optimal size of the board. The board is a square
     */
    final int BOARD_LENGTH = 800;

    /**
     * Size of click area in the board, indicating one side of a square
     */
    final int BUTTON_SIZE = 70;

    /**
     * The distance between each layers of position in a board
     */
    final int LAYER_DISTANCE = 110;

    /**
     * Layered Pane to implement multiple feature on the board (including stacking positions,
     * pieces and board on top of one another
     */
    private JLayeredPane layeredPane;

    /**
     * Hash map connecting positions to the corresponding pieces
     */
    private Map<Position, Piece> mapping = new HashMap<>();

    /**
     * Image of the board to display on the program
     */
    private ImageIcon boardImage = getImageIcon();

    /**
     * List of all positions on the map, keyed by layers and position code nested in Point class
     */
    public Map<Point, Position> positions = new HashMap<>();


    /**
     * Constructor to create a new Board instances. Some initial attribute to define board visual are added
     */
    public Board(){
        //setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(800, 800));
        setBackground(Color.WHITE);
        FlowLayout layout = (FlowLayout)this.getLayout();
        layout.setVgap(0);
        layout.setHgap(0);

    }

    /**
     * Check if A position in the board contain any piece
     * @param position position to be checked
     * @return whether there is a piece in the position. Return true if available and false otherwise
     */
    public Boolean hasPieceAt(Position position){
        if(mapping.get(position)==null) return false;
        else return  true;

    }

    /**
     * Initiate board to be ready to start the game
     * @param game The game instance that will be running the board
     */
    public void init(Game game){
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 800));
        layeredPane.setBorder(BorderFactory.createEmptyBorder());
        JLabel boardImageLabel =new JLabel(boardImage);
        layeredPane.add(boardImageLabel,3);
        boardImageLabel.setBounds(0, 0, BOARD_LENGTH, BOARD_LENGTH);

        int cor_layer = 0;
        for(int layer =0; layer <3; layer++){
            cor_layer = layer*LAYER_DISTANCE;
            for (int i =0; i<8; i++){
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

    /*
    private void addControl(Player player){
        for(Map.Entry<Point, Position> entry : positions.entrySet()){
            entry.getValue().addActionListener(player);
        }
    }
    */

    /**
     * Get all the positions in the board
     * @return Return a Hash map of Point connecting to Position
     */
    public Map<Point, Position> getPositions() {
        return positions;
    }

    /**
     * Fetch the piece placed in a certain position in the board.
     * @param position The position searched
     * @return The piece stationed in that position. Null if no piece is found
     */
    public Piece getPiece(Position position){
        return mapping.get(position);
    }

    /**
     * Remove a piece from a position in the board
     * @param piece The piece to be removed
     */
    public void removePiece(Piece piece){
        if(piece.getCurrentPosition() == null){
            throw new RuntimeException("Piece do ot have position");
        }
        mapping.remove(piece.getCurrentPosition(), piece);
    }

    /**
     * Add a new piece to a position in the board
     * @param piece The piece to be added
     * @param position The position to add the piece at
     */
    public void addPieceAt(Piece piece, Position position){
        mapping.put(position, piece);
        //positionButtons.remove(position);
        //piece.setCurrentPosition(position);
        layeredPane.add(piece,1);
        layeredPane.add(piece.pieceImage, 0);
        piece.setBounds(position.getBounds());
        piece.pieceImage.setBounds(position.getBounds());
        System.out.println(mapping.size());
        //if()
    }

    /**
     * Load the Image of the board into the program
     * @return The ImageIcon containing the board's visual
     */
    public ImageIcon getImageIcon() {
        if(new ImageIcon("src/image/Board.png")==null)System.out.println("HH");
        return new ImageIcon("src/image/Board.png");


    }

    /**
     * Overwrite from Jpanel, used to implement program visual
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        //g.drawImage(boardImage, 0, 0, 800, 800,null);
        for(Map.Entry<Position, Piece> entry : mapping.entrySet()){
            Point location = entry.getValue().getLocation();
            //System.out.println(location);
            entry.getValue().draw(g, location.x, location.y);
        }

    }

    /**
     * Get the number of Red Pieces currently in the board
     * @return int indicate the number of red piece
     */
    public int getNumberOfRedPieces (){
        int redNo=0;
        for (Map.Entry<Position,Piece> i: mapping.entrySet()){
            if (i.getValue().hasStatus(Status.RED)){
                redNo +=1;
            }
        }
        return redNo;
    }

    /**
     * Get the number of Blue Pieces currently in the board
     * @return int indicate the number of blue piece
     */
    public int getNumberOfBluePieces (){
        int blueNo=0;
        for (Map.Entry<Position,Piece> i: mapping.entrySet()){
            if (i.getValue().hasStatus(Status.BLUE)){
                blueNo +=1;
            }
        }
        return blueNo;
    }

    public boolean isGameOver(Boolean isRedTurn){
        //Case 1: red player has less or equal to 2 pieces

        int numberRedPieces = getNumberOfRedPieces();
        int numberBluePieces = getNumberOfBluePieces();
        if (numberRedPieces <= 2 ){
            System.out.println("Red Player lose");
            return true;
        }else if (numberBluePieces <= 2){
            System.out.print("Blue Player lose");
            return true;
        }
        //Case 2: player is unable to move
        if (isRedTurn) {
            if (!pieceSetHasAvailableMove(Status.RED)){
                System.out.println("Red Player lose");
                return true;
            }
        } else {
            if (!pieceSetHasAvailableMove(Status.BLUE)){
                System.out.println("Blue Player lose");
                return true;
            }
        }
        return false;
    }

    public boolean pieceSetHasAvailableMove(Status status){
        for (Map.Entry<Position,Piece> i: mapping.entrySet()){
            Piece temp = i.getValue();
            if (temp.hasStatus(status)){
                if (temp.hasAvailableMove(this)){
                    return true;
                }
            }
        }
        return false;
    }
}