package weather.queries.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

public class LimitIterator<T> implements Iterator<T> {
    private final Iterator<T> srcIter;
    private int limit;
    private Optional<T> next = Optional.empty();

    public LimitIterator(Iterable<T> src, int n) {
        this.srcIter = src.iterator();
        limit = n;
    }

    @Override
    public boolean hasNext() {
        if (next.isPresent())
            return true;
        if (limit > 0 && srcIter.hasNext()) {
            next = Optional.of(srcIter.next());
            --limit;
            return true;
        }
        return false;
    }

    @Override
    public T next() {
        if (!hasNext())
            throw new NoSuchElementException();
        T aux = next.get();
        next = Optional.empty();
        return aux;
    }
}
