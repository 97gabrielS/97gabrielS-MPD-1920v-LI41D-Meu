package weather.queries.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;

public class FilterIterator<T> implements Iterator<T> {
    private final Iterator<T> srcIter;
    private final Predicate<T> pred;
    private Optional<T> next = Optional.empty();

    public FilterIterator(Iterable<T> src, Predicate<T> pred) {
        this.srcIter = src.iterator();
        this.pred = pred;
    }

    @Override
    public boolean hasNext() {
        if (next.isPresent())  /* case when hasNext has consecutively been called multiple times */
            return true;
        while (srcIter.hasNext()) {
            next = Optional.of(srcIter.next());
            if (pred.test(next.get())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public T next() {
        /* hasNext is called to guarantee correctness when only next is called by the client */
        if (!hasNext())
            throw new NoSuchElementException();
        T aux = next.get();
        next = Optional.empty();
        return aux;
    }
}
