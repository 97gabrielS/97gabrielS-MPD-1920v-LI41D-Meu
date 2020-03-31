package weather.queries.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class FilterIterator<T> implements Iterator<T> {
    private final Iterator<T> srcIter;
    private final Predicate<T> pred;
    private T next;
    private boolean nextExists;

    public FilterIterator(Iterable<T> src, Predicate<T> pred) {
        this.srcIter = src.iterator();
        this.pred = pred;
    }

    @Override
    public boolean hasNext() {
        if (nextExists)  /* case when hasNext has consecutively been called multiple times */
            return true;
        while (srcIter.hasNext()) {
            next = srcIter.next();
            if (pred.test(next)) {
                nextExists = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public T next() {
        /* hasNext is called to guarantee correctness when only next is called by the client */
        if (!hasNext())  /* case when there aren't more elements to iterate */
            throw new NoSuchElementException();
        nextExists = false;
        return next;

    }
}
