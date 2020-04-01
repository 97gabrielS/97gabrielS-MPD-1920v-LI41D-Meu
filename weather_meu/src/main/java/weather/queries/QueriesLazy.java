package weather.queries;

import weather.queries.iterators.FilterIterator;
import weather.queries.iterators.LimitIterator;
import weather.queries.iterators.MapIterator;
import weather.queries.iterators.SkipIterator;

import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;

public class QueriesLazy<T> {
    private Iterable<T> src;

    private QueriesLazy(Iterable<T> src) {
        this.src = src;
    }

    public static <T> QueriesLazy<T> from(Iterable<T> src) {
        return new QueriesLazy<>(src);
    }

    public <R> QueriesLazy<R> map(Function<T, R> func) {
        return new QueriesLazy<>(() -> new MapIterator<>(src, func));
    }

    public QueriesLazy<T> filter(Predicate<T> pred) {
        return new QueriesLazy<>(() -> new FilterIterator<>(src, pred));
    }

    public QueriesLazy<T> skip(int n) {
        return new QueriesLazy<>(() -> new SkipIterator<>(src, n));
    }

    public QueriesLazy<T> limit(int n) {
        return new QueriesLazy<>(() -> new LimitIterator<>(src, n));
    }

    public int count() {
        int counter = 0;
        for (T ignored : src) {
            ++counter;
        }
        return counter;
    }

    public T[] toArray(IntFunction<T[]> arrayFactory) {
        T[] destArray = arrayFactory.apply(count());
        int idx = 0;
        for (T elem: src) {
            destArray[idx++] = elem;
        }
        return destArray;

    }
}
