package Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Player implements ActionListener {

    private String name;
    private PlayerType playerType;

    public Player(String name) {

        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
