
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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
        setBackground(Color.BLUE);
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

}
