package weather.queries.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;

public class LimitIterator<T> extends BaseIterator<T> {
    private final Iterator<T> srcIter;
    private int limit;

    public LimitIterator(Iterable<T> src, int n) {
        this.srcIter = src.iterator();
        limit = n;
    }

    @Override
    protected boolean tryAdvance(Consumer<T> consumer) {
        if (limit > 0 && srcIter.hasNext()) {
            consumer.accept(srcIter.next());
            --limit;
            return true;
        }
        return false;
    }
}
