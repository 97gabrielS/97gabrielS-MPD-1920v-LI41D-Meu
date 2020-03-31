package weather.queries.iterators;

import java.util.Iterator;
import java.util.function.Function;

public class MapIterator<T, R> implements Iterator<R> {
    private final Iterator<T> srcIter;
    private final Function<T, R> func;

    public MapIterator(Iterable<T> src, Function<T, R> func) {
        this.srcIter = src.iterator();
        this.func = func;
    }

    @Override
    public boolean hasNext() {
        return srcIter.hasNext();
    }

    @Override
    public R next() {
        return func.apply(srcIter.next());
    }
}
