package queries;

import org.junit.Test;
import weather.queries.QueriesLazy;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static weather.queries.QueriesLazy.*;

public class QueriesLazyTest {
    private final List<String> strings = Arrays.asList("Sport", "Lisboa", "e", "Benfica");

    @Test
    public void predicateIteratorTest() {
        Iterable<String> filteredIterable = filter(strings, s -> s.length() > 4);
        assertNotNull(filteredIterable);
        String[] filteredArray = toArray(filteredIterable, size -> new String[size]);
        assertArrayEquals(new String[]{"Sport", "Lisboa", "Benfica"}, filteredArray);
    }

    @Test
    public void mapIteratorTest() {
        Iterable<Integer> mappedIterable = map(strings, s -> s.length());
        assertNotNull(mappedIterable);
        Integer[] mappedArray = toArray(mappedIterable, size -> new Integer[size]);
        assertArrayEquals(new Integer[]{5, 6, 1, 7}, mappedArray);
    }

    @Test
    public void limitIteratorTest() {
        Iterable<String> limitedIterable = limit(strings, 2);
        assertNotNull(limitedIterable);
        String[] limitedArray = toArray(limitedIterable, size -> new String[size]);
        assertArrayEquals(new String[]{"Sport", "Lisboa"}, limitedArray);
    }

    @Test
    public void skipIteratorTest() {
        Iterable<String> skippedIterable = skip(strings, 3);
        assertNotNull(skippedIterable);
        String[] skippedArray = toArray(skippedIterable, size -> new String[size]);
        assertArrayEquals(new String[]{"Benfica"}, skippedArray);
    }

    @Test
    public void predicateAndMapAndLimitIteratorsTest() {
        /* test tooo much complicated; QueriesLay class must be changed to make composite operations calls cleaner */

        Iterable<String> filteredIterable = filter(strings, s -> s.contains("a"));
        assertNotNull(filteredIterable);
        Iterable<Integer> mappedIterable = map(filteredIterable, s -> s.length());
        assertNotNull(mappedIterable);
        Iterable<Integer> limitedIterable = limit(mappedIterable, 1);
        assertNotNull(limitedIterable);
        Integer[] limitedArray = toArray(limitedIterable, size -> new Integer[size]);
        assertArrayEquals(new Integer[]{6}, limitedArray);

        // ou....
        assertArrayEquals(new Integer[]{6}, toArray(limit(map(filter(strings, s -> s.contains("a")), s -> s.length()), 1), size -> new Integer[size]));

    }
}
