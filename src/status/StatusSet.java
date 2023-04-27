package status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A list of Status of a Player / Piece
 * Created by 
 * @author Thanh Nguyen
 * Modified by: 
 * */
public class StatusSet implements State{
    private final Set<Enum<?>> statusSet = new HashSet<>();
     /**
     * Check if actor has a status
     *
     * @param status
     * */
    @Override
    public boolean hasStatus(Enum<?> status) {
        return statusSet.contains(status);
    }

    /**
     * Add stattus to StatusSet
     *
     * @param status
     * */
    @Override
    public void addStatus(Enum<?> status) {
        if (!hasStatus(status)){
            statusSet.add(status);
        }
    }
    /**
     * Remove Status from statusSet
     *
     * @param status
     * */
    @Override
    public void removeStatus(Enum<?> status) {
        if (hasStatus(status)){
            statusSet.remove(status);
        }
    }
    /**
     * get the Status list
     *
     * @param
     * */
    @Override
    public List<Enum<?>> statusList() {
        List<Enum<?>> tenp = new ArrayList<>(statusSet);
        return tenp;
    }
}
