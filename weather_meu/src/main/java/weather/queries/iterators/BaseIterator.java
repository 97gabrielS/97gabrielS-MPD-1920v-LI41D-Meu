package weather.queries.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class BaseIterator<T> implements Iterator<T> {
    private Optional<T> next;

    @Override
    public boolean hasNext() {
        if (next.isPresent()) /* case when hasNext has consecutively been called multiple times */
            return true;
        return tryAdvance(t -> next = Optional.of(t));
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

    protected abstract boolean tryAdvance(Consumer<T> consumer);
}
