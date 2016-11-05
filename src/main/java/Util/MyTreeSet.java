package Util;

import java.util.Iterator;
import java.util.TreeSet;

public class MyTreeSet extends TreeSet<State> {
    public State pop() {
        State result = null;
        Iterator<State> iterator = iterator();
        if (iterator.hasNext()) {
            result = iterator.next();
            iterator.remove();
        }

        return result;
    }

    public State peek() {
        State result = null;
        Iterator<State> iterator = iterator();
        if (iterator.hasNext()) {
            result = iterator.next();
        }

        return result;
    }
}
