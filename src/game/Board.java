package game;

import piece.Piece;
import player.Player;
import status.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
     */
    public void init(){
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

                positions.put(new Point(layer, i), position);
            }
        }
        //Add control pane and layered pane to this JPanel.
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(layeredPane);
        //System.out.println(getPositions());
    }


    public void updatePlayer(Player player){
        for(Map.Entry<Point, Position> entry : positions.entrySet()){
            for( ActionListener al : entry.getValue().getActionListeners() ) {
                entry.getValue().removeActionListener( al );
            }
            entry.getValue().addActionListener(player);
        }
    }

    public void updateMills(){
        for (int layer =0; layer<3; layer++){
            for (int i=0; i<8;i++){
                if(hasPieceAt(positions.get(new Point(layer, i)))) {
                    mapping.get(positions.get(new Point(layer, i))).removeStatus(Status.IN_MILL);
                    mapping.get(positions.get(new Point(layer, i))).addStatus(Status.OUTSIDE_MILL);
                }
            }
        }
        for(int layer=0; layer<3; layer++) {
            for (int i = 0; i < 6; i += 2) {
                Boolean inMill = true;
                for (int j = 0; j < 3; j++) {
                    if (!hasPieceAt(positions.get(new Point(layer, i+j)))){
                        inMill=false;
                        break;
                    }
                    else if(!(mapping.get(positions.get(new Point(layer, i + j))).isRed==mapping.get(positions.get(new Point(layer, i))).isRed)){
                        inMill = false;
                        break;
                    }
                }
                if (inMill) {
                    for (int j = 0; j < 3; j++) {
                        if (hasPieceAt(positions.get(new Point(layer, i+j)))) {
                            mapping.get(positions.get(new Point(layer, i + j))).removeStatus(Status.OUTSIDE_MILL);
                            mapping.get(positions.get(new Point(layer, i + j))).addStatus(Status.IN_MILL);
                        }


                    }
                }
            }
            Boolean inMill=true;
            int array[] ={0,7,6};
            for(int j : array){
                if (!hasPieceAt(positions.get(new Point(layer, j)))){
                    inMill =false;
                    break;
                }
                else if(!(mapping.get(positions.get(new Point(layer, j))).isRed==mapping.get(positions.get(new Point(layer, 0))).isRed)){

                    inMill = false;
                        break;
                }
            }
            if (inMill) {
                for(int j : array){
                    if (hasPieceAt(positions.get(new Point(layer, j)))) {
                        mapping.get(positions.get(new Point(layer, j))).removeStatus(Status.OUTSIDE_MILL);
                        mapping.get(positions.get(new Point(layer, j))).addStatus(Status.IN_MILL);
                    }


                }
            }
        }
        for(int i=1; i<8; i+=2){
            Boolean inMill=true;
            for (int j=0; j<3; j++){
                if(!hasPieceAt(positions.get(new Point(j, i)))){
                    inMill =false;
                    break;
                }
                else if(!(mapping.get(positions.get(new Point(j, i))).isRed==mapping.get(positions.get(new Point(0, i))).isRed)){

                    inMill=false;
                    break;
                }
            }
            if(inMill){
                for (int j=0; j<3; j++){
                    if (hasPieceAt(positions.get(new Point(j, i)))) {
                        mapping.get(positions.get(new Point(j, i))).removeStatus(Status.OUTSIDE_MILL);
                        mapping.get(positions.get(new Point(j, i))).addStatus(Status.IN_MILL);
                    }
                }

            }

        }
        System.out.println("------------");
        for (int layer =0; layer<3; layer++){
            System.out.print(layer);
            for (int i=0; i<8;i++){
                if(hasPieceAt(positions.get(new Point(layer, i))))
                    System.out.print(mapping.get(positions.get(new Point(layer, i))).hasStatus(Status.IN_MILL));
                System.out.print(' ');
            }
            System.out.println();
        }
        System.out.println("------------");
    }

    public  boolean isAllMill(boolean isRed){
        for(int layer=0; layer<3; layer++){
            for(int i=0; i<8; i++){
                if(hasPieceAt(positions.get(new Point(layer, i)))) {
                    if(mapping.get(positions.get(new Point(layer, i))).isRed==isRed) {
                        if (mapping.get(positions.get(new Point(layer, i))).hasStatus(Status.OUTSIDE_MILL)) {
                            System.out.println("NOT ALL MILL");
                            return false;

                        }
                    }
                }
            }
        }
        System.out.println("ALL MILL");
        return  true;
    }
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
        layeredPane.remove(piece.pieceImage);
    }

    public void removePiece(Piece piece, Position position){
        mapping.remove(position, piece);
        layeredPane.remove(piece.pieceImage);
    }

    /**
     * Add a new piece to a position in the board
     * @param piece The piece to be added
     * @param position The position to add the piece at
     */
    public void addPieceAt(Piece piece, Position position){
        if(mapping.get(position)==null)mapping.put(position, piece);

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
     * Get the number of Red/Blue Pieces currently in the board
     * @return int indicate the number of piece
     */
    public int getNumberOfPieces (Status colour){
        int no=0;
        for (Map.Entry<Point,Position> entry: positions.entrySet()){
            if(hasPieceAt(entry.getValue())) {
                if (getPiece(entry.getValue()).hasStatus(colour)) {
                    no += 1;
                }
            }
        }
        return no;
    }


    public boolean isGameOver(Player player, Status gamePhase){
        //Case 1: red player has less or equal to 2 pieces
        //System.out.println("-------------------------");
        //System.out.println("Start detecting End game");
        if (gamePhase == Status.PHASE_3) {
            int numberRedPieces = getNumberOfPieces(Status.RED);
            int numberBluePieces = getNumberOfPieces(Status.BLUE);

            if (numberRedPieces <= 2) {
                System.out.println(numberRedPieces);
                System.out.println("Red Player lose");
                return true;
            }
            if (numberBluePieces <= 2) {
                System.out.println(numberBluePieces);
                System.out.print("Blue Player lose");
                return true;
            }
        }
        //Case 2: player is unable to move
        if(gamePhase==Status.PHASE_2) {
            if (player.hasStatus(Status.RED)) {
                //System.out.println("Start analyzing player red");
                if (!pieceSetHasAvailableMove(Status.RED)) {
                    //System.out.println("Red piece has more move: " + pieceSetHasAvailableMove(Status.RED));
                    //System.out.println("Red Player lose");
                    return true;
                }
            } else {
                //System.out.println("Start analyzing player blue");
                if (!pieceSetHasAvailableMove(Status.BLUE)) {
                    //System.out.println("BLue piece has more move: " + pieceSetHasAvailableMove(Status.BLUE));
                    //System.out.println("Blue Player lose");
                    return true;
                }
            }
        }

        //System.out.println("-------------------------");
        return false;
    }

    public boolean pieceSetHasAvailableMove(Status status){
        for (Map.Entry<Point, Position> entry : positions.entrySet()){
            if(hasPieceAt(entry.getValue())) {
                Piece temp = getPiece(entry.getValue());
                try {
                    if (temp.hasStatus(status)) {
                        if (temp.hasAvailableMove(this)) {
                            return true;
                        }
                    }
                } catch (Exception ignore) { }
            }
        }
        return false;
    }
}