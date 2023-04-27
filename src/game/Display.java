package game;
import javax.swing.*;
import java.awt.*;

public class Display extends JPanel{

    private JPanel text_panel =null;

    private JLabel text_field = null;
    //public Display()

    public void init(){
        setLayout(new BorderLayout());

        text_panel =new JPanel();
        text_field = new JLabel();

        text_panel.setLayout(new BorderLayout());
        text_panel.setBounds(0,0,1000,100);

        text_field.setBackground(Color.WHITE);
        text_field.setForeground(new Color(25,255,0));
        text_field.setFont(new Font("Ink Free",Font.BOLD,75));
        text_field.setHorizontalAlignment(JLabel.CENTER);
        text_field.setText("Red Player Turn");

        text_panel.add(text_field);
        add(text_panel, BorderLayout.NORTH);


    }


}
