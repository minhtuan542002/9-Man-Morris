package Status;

import java.util.List;

public interface State {
    boolean hasStatus(Enum<?> status);

    void addStatus(Enum<?> status);
    void removeStatus(Enum<?> status);

    List<Enum<?>> statusList();
}
