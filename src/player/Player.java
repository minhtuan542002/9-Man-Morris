package player;

import status.State;
import status.StatusSet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 * Player object class
 * Created by 
 * @author Thanh Nguyen
 * Modified by: 
 * */
public class Player implements State, ActionListener {

    private String name;
    private final StatusSet statusSet = new StatusSet();

     /**
     * Constructor
     *
     * @param name
     * */
    public Player(String name) {

        this.name = name;
    }
     /**
     * Print Player name
     *
     * @param 
     * */

    @Override
    public String toString(){
        return name;
    }
     /**
     * Get Player name
     *
     * @param 
     * */
    public String getName() {
        return name;
    }
     /**
     * set Player name
     *
     * @param name
     * */
    public void setName(String name) {
        this.name = name;
    }
     /**
     * Check if actor has a status
     *
     * @param status
     * */
    @Override
    public boolean hasStatus(Enum<?> status) {
        return statusSet.hasStatus(status);
    }

    /**
     * Add stattus to StatusSet
     *
     * @param status
     * */
    @Override
    public void addStatus(Enum<?> status) {
        statusSet.addStatus(status);
    }
    /**
     * Remove Status from statusSet
     *
     * @param status
     * */
    @Override
    public void removeStatus(Enum<?> status) {
        statusSet.removeStatus(status);
    }
    /**
     * get the Status list
     *
     * @param
     * */
    @Override
    public List<Enum<?>> statusList() {
        return statusSet.statusList();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
