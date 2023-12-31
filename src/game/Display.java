package game;
import player.Player;
import status.Status;

import javax.swing.*;
import java.awt.*;

/**
 * The display to generate the Graphic user interface of the program
 */
public class Display extends JPanel{

    /**
     * The text title of the program
     */
    private JPanel text_panel =null;

    /**
     * Text field inside the text title
     */
    private JLabel text_field = null;

    /**
     * Initiate settings and visual configurations for the game
     */
    public void init(){
        setLayout(new BorderLayout());

        text_panel =new JPanel();
        text_field = new JLabel();

        text_panel.setLayout(new BorderLayout());
        text_panel.setBounds(0,0,1000,100);

        text_field.setBackground(Color.WHITE);
        text_field.setForeground(Color.red);
        text_field.setFont(new Font("Ink Free",Font.BOLD,75));
        text_field.setHorizontalAlignment(JLabel.CENTER);
        text_field.setText("Red Player Turn");

        text_panel.add(text_field);
        add(text_panel, BorderLayout.NORTH);


    }

    /**
     * Change the text_label to the current player turn
     * @param isRedTurn
     * */

    public void changeLabel(Boolean isRedTurn){
        if (isRedTurn){
            text_field.setText("Red Player Turn");
            text_field.setForeground(Color.red);
        } else {
            text_field.setText("Blue Player Turn");
            text_field.setForeground(Color.blue);
        }
    }

    /**
     * Display "Game over" to announce the game is over.
     * */
    public void announceEndGame(Player winner){

        text_field.setText("Game Over: "+winner.toString()+" win");
        text_field.setFont(new Font("Ink Free",Font.BOLD,65));
        if(winner.hasStatus(Status.RED)){
            text_field.setForeground(Color.red);
        }
        else text_field.setForeground(Color.blue);
    }

    public void removeAnnounce(Boolean isRedTurn){
        if (isRedTurn){
            text_field.setText("Remove one Blue Piece");
            text_field.setForeground(Color.red);
        } else {
            text_field.setText("Remove one Red Piece");
            text_field.setForeground(Color.blue);
        }
    }

}
