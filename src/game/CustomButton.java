package game;

import javax.swing.*;
import java.awt.*;

public class CustomButton {

    private final JLabel customButton;

    public CustomButton(String imagePath){
        this.customButton = new JLabel(getImageIcon(imagePath));
    }

    private ImageIcon getImageIcon(String imagePath){
        ImageIcon image = new ImageIcon(imagePath);
        Image imageTransform = image.getImage().getScaledInstance(48*8, 48*2, Image.SCALE_SMOOTH);
        image = new ImageIcon(imageTransform);
        return image;
    }

    public JLabel getCustomButton(){
        return customButton;
    }
}
