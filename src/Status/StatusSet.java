package Status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StatusSet implements State{
    private final Set<Enum<?>> statusSet = new HashSet<>();
    @Override
    public boolean hasStatus(Enum<?> status) {
        return statusSet.contains(status);
    }

    @Override
    public void addStatus(Enum<?> status) {
        if (!hasStatus(status)){
            statusSet.add(status);
        }
    }

    @Override
    public void removeStatus(Enum<?> status) {
        if (hasStatus(status)){
            statusSet.remove(status);
        }
    }

    @Override
    public List<Enum<?>> statusList() {
        List<Enum<?>> tenp = new ArrayList<>(statusSet);
        return tenp;
    }
}
