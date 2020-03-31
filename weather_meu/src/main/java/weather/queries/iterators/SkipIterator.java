package weather.queries.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

public class SkipIterator<T> implements Iterator<T> {
    private final Iterator<T> srcIter;
    private int nrToSkip;
    private Optional<T> next = Optional.empty();

    public SkipIterator(Iterable<T> src, int n) {
        this.srcIter = src.iterator();
        this.nrToSkip = n;
    }

    @Override
    public boolean hasNext() {
        if (next.isPresent())
            return true;
        /* on the first call to hasNext skip the first nrToSkip elements of the collection */
        while (nrToSkip > 0 && srcIter.hasNext()) {
            srcIter.next();
            --nrToSkip;
        }
        if (srcIter.hasNext()) {
            next = Optional.of(srcIter.next());
            return true;
        }
        return false;
    }

    @Override
    public T next() {
        if (!hasNext())
            throw new NoSuchElementException();
        final T aux = next.get();
        next = Optional.empty();
        return aux;
    }
}
