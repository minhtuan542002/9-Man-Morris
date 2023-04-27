package game;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The class implementing the positions inside the board and define the mouse area to click on
 */
public class Position extends JButton {
    /**
     * refers to square layer piece is on (3 layers)
     */
    private int layer;
    /**
     * refers to position on square (8 positions, counting clock-wise from top left)
     */
    private int positionNumber;

    /**
     * Constructor method for position
     * @param layer The square layer the piece is on
     * @param positionNumber The position number of the position inside the square
     */
    Position(int layer, int positionNumber){
        this.layer = layer;
        this.positionNumber = positionNumber;
        setSize(new Dimension(70, 70));
        setOpaque(false);
    }

    private void calculate_coordinate(){

    }

    /**
     * Change layer information
     * @param layer New layer
     */
    public void setLayer(int layer){
        this.layer = layer;
    }

    /**
     * Change position number information
     * @param positionNumber New position number
     */
    public void setPositionNumber(int positionNumber){
        this.positionNumber = positionNumber;
    }

    /**
     * Get the layer number of the position
     * @return The layer number
     */
    public int getLayer(){
        return this.layer;
    }

    /**
     * Get the position number of the position
     * @return The position number inside the layer
     */
    public int getPositionNumber(){
        return this.positionNumber;
    }

    /**
     * Get the positions adjacent to this position
     * @return An array list of nearby position
     */
    public List<Position> getAdjacentPositions(){
        List<Position> adjacentPositions = new ArrayList<>();

        if (this.positionNumber == 0) {
            adjacentPositions.add(new Position(this.layer, this.positionNumber+1));
            adjacentPositions.add(new Position(this.layer, 7));
        }
        else if (this.positionNumber == 7){
            adjacentPositions.add(new Position(this.layer, this.positionNumber-1));
            adjacentPositions.add(new Position(this.layer, 0));
        }
        else{
            adjacentPositions.add(new Position(this.layer, this.positionNumber+1));
            adjacentPositions.add(new Position(this.layer, this.positionNumber-1));
            if (this.positionNumber % 2 != 0) {
                if (this.layer == 0) {
                    adjacentPositions.add(new Position(this.layer+1, this.positionNumber));
                }
                if (this.layer == 1) {
                    adjacentPositions.add(new Position(this.layer+1, this.positionNumber));
                    adjacentPositions.add(new Position(this.layer-1, this.positionNumber));
                }
                if (this.layer == 2) {
                    adjacentPositions.add(new Position(this.layer-1, this.positionNumber));
                }
            }
        }
        return adjacentPositions;
    };


}
