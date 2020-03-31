package weather.queries.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LimitIterator<T> implements Iterator<T> {
    private final Iterator<T> srcIter;
    private boolean nextExists;
    private int limit;
    private T next;

    public LimitIterator(Iterable<T> src, int n) {
        this.srcIter = src.iterator();
        limit = n;
    }

    @Override
    public boolean hasNext() {
        if (nextExists)
            return true;
        if (limit > 0 && srcIter.hasNext()) {
            nextExists = true;
            next = srcIter.next();
            --limit;
            return true;
        }
        return false;
    }

    @Override
    public T next() {
        if (!hasNext())
            throw new NoSuchElementException();
        nextExists = false;
        return next;
    }
}
