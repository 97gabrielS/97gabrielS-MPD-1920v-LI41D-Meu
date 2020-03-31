package weather.queries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Function;

public class QueriesEager {

    public static <T> Iterable<T> filter(Iterable<T> src, Predicate<T> pred) {
        List<T> result = new ArrayList<>();
        for (T item: src) {
            if (pred.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public static <T, R> Iterable<R> map(Iterable<T> src, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T item: src) {
            result.add(function.apply(item));
        }
        return result;
    }

}
