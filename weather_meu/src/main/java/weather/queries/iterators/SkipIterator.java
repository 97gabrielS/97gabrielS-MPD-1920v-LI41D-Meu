package weather.queries.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SkipIterator<T> implements Iterator<T> {
    private final Iterator<T> srcIter;
    private int nrToSkip;
    private boolean nextExists;
    private T next;

    public SkipIterator(Iterable<T> src, int n) {
        this.srcIter = src.iterator();
        this.nrToSkip = n;
    }

    @Override
    public boolean hasNext() {
        if (nextExists)
            return true;
        /* on the first call to hasNext skip the first nrToSkip elements of the collection */
        while (nrToSkip > 0 && srcIter.hasNext()) {
            srcIter.next();
            --nrToSkip;
        }
        if (srcIter.hasNext()) {
            nextExists = true;
            next = srcIter.next();
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
