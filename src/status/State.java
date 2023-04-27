package status;

import java.util.List;

public interface State {
    /**
     * An Interface containing neccessary function for maintaning and managing StatusSet
     *
     * Created by 
    * @author Thanh Nguyen
    * Modified by: 
     * */
    boolean hasStatus(Enum<?> status);

    void addStatus(Enum<?> status);
    void removeStatus(Enum<?> status);

    List<Enum<?>> statusList();
}
