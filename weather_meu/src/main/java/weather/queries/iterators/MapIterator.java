package weather.queries.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

public class MapIterator<T, R> implements Iterator<R> {
    private final Iterator<T> srcIter;
    private final Function<T, R> func;
    private Optional<R> next = Optional.empty();

    public MapIterator(Iterable<T> src, Function<T, R> func) {
        this.srcIter = src.iterator();
        this.func = func;
    }

    @Override
    public boolean hasNext() {
        if (next.isPresent())
            return true;
        if (srcIter.hasNext()) {
            next = Optional.of(func.apply(srcIter.next()));
            return true;
        }
        return false;
    }

    @Override
    public R next() {
        if (!hasNext())
            throw new NoSuchElementException();
        R aux = next.get();
        next = Optional.empty();
        return aux;
    }
}
