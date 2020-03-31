package weather.queries;

import weather.queries.iterators.FilterIterator;
import weather.queries.iterators.LimitIterator;
import weather.queries.iterators.MapIterator;
import weather.queries.iterators.SkipIterator;

import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;

public class QueriesLazy {

    public static <T, R> Iterable<R> map(Iterable<T> src, Function<T, R> func) {
        return () -> new MapIterator<>(src, func);
    }

    public static <T> Iterable<T> filter(Iterable<T> src, Predicate<T> pred) {
        return () -> new FilterIterator<>(src, pred);
    }

    public static <T> Iterable<T> skip(Iterable<T> src, int n) {
        return () -> new SkipIterator<>(src, n);
    }

    public static <T> Iterable<T> limit(Iterable<T> src, int n) {
        return () -> new LimitIterator<>(src, n);
    }

    public static <T> int count(Iterable<T> src) {
        int counter = 0;
        for (T elem : src) {
            ++counter;
        }
        return counter;
    }

    public static <T> T[] toArray(Iterable<T> src, IntFunction<T[]> arrayFactory) {
        T[] destArray = arrayFactory.apply(count(src));
        int idx = 0;
        for (T elem: src) {
            destArray[idx++] = elem;
        }
        return destArray;

    }
}
