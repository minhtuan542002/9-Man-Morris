package Action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ActionList implements Iterable<Action>{
    private ArrayList<Action> actions = new ArrayList<>();

    /**
     * Construct an empty list
     * */
    public ActionList(){}

    /**
     * Construct a list containing non-null action
     * */
    public ActionList(Action action) {
       add(action);
    }

    /**
     * Appends a single Action to this collection, if it is non-null.  If it is null, then it is ignored.
     *
     * @param action the Action to append
     * @return true unconditionally
     */
    public boolean add(Action action) {
        if (action != null) {
            actions.add(action);
        }
        return true;
    }

    /**
     * Appends the contents of another Actions list to this one.
     *
     * @param actions the Actions to append
     */
    public void add(ActionList actions) {
        for(Action action : actions) {
            add(action);
        }
    }

    /**
     * Delete the contents of this collection, leaving it empty.
     */
    public void clear() {
        actions.clear();
    }

    /**
     * Count the number of Actions in the collection.
     *
     * @return the number of Actions in the collection.
     */
    public int size() {
        return actions.size();
    }

    /**
     * Return the <code>i</code>'th Action in the collection.
     *
     * @param i index of the Action to retrieve
     * @return the <code>i</code>'th Action in the collection
     * @throws IndexOutOfBoundsException when <code>i</code> &gt;= <code>this.size()</code>
     */
    public Action get(int i) {
        return actions.get(i);
    }

    @Override
    public Iterator<Action> iterator() {
        return Collections.unmodifiableList(actions).iterator();
    }

    @Override
    public Spliterator<Action> spliterator() {
        return Iterable.super.spliterator();
    }

    @Override
    public void forEach(Consumer<? super Action> action) {
        Iterable.super.forEach(action);
    }
}
