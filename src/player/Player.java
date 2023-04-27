package player;

import status.State;
import status.StatusSet;

import java.util.List;

public class Player implements State {

    private String name;
    private final StatusSet statusSet = new StatusSet();

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

    @Override
    public boolean hasStatus(Enum<?> status) {
        return statusSet.hasStatus(status);
    }

    @Override
    public void addStatus(Enum<?> status) {
        statusSet.addStatus(status);
    }

    @Override
    public void removeStatus(Enum<?> status) {
        statusSet.removeStatus(status);
    }

    @Override
    public List<Enum<?>> statusList() {
        return statusSet.statusList();
    }

}
