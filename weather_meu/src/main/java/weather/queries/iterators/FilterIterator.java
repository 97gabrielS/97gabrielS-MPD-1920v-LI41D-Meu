package weather.queries.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class FilterIterator<T> extends BaseIterator<T> {
    private final Iterator<T> srcIter;
    private final Predicate<T> pred;

    public FilterIterator(Iterable<T> src, Predicate<T> pred) {
        this.srcIter = src.iterator();
        this.pred = pred;
    }

    @Override
    protected boolean tryAdvance(Consumer<T> consumer) {
        while (srcIter.hasNext()) {
            T next = srcIter.next();
            if (pred.test(next)) {
                consumer.accept(next);
                return true;
            }
        }
        return false;
    }
}
