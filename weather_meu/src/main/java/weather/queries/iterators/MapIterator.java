package weather.queries.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class MapIterator<T, R> extends BaseIterator<R> {
    private final Iterator<T> srcIter;
    private final Function<T, R> func;

    public MapIterator(Iterable<T> src, Function<T, R> func) {
        this.srcIter = src.iterator();
        this.func = func;
    }

    @Override
    protected boolean tryAdvance(Consumer<R> consumer) {
        if (srcIter.hasNext()) {
            consumer.accept(func.apply(srcIter.next()));
            return true;
        }
        return false;
    }
}
