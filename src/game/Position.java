package game;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Position extends JButton {
    int layer; // refers to square layer piece is on (3 layers)
    int positionNumber; // refers to position on square (8 positions, counting clock-wise from top left)

    int x_coordinate;

    int y_coordinate;

    Position(int layer, int positionNumber){
        this.layer = layer;
        this.positionNumber = positionNumber;
        setSize(new Dimension(70, 70));
        setOpaque(false);
    }

    private void calculate_coordinate(){

    }

    public void setLayer(int layer){
        this.layer = layer;
    }

    public void setPositionNumber(int positionNumber){
        this.positionNumber = positionNumber;
    }

    public int getLayer(){
        return this.layer;
    }

    public int getPositionNumber(){
        return this.positionNumber;
    }

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
