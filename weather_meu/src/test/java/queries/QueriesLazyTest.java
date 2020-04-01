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
        String[] filteredStrings = QueriesLazy.from(strings)
                .filter(s -> s.length() > 4)
                .toArray(size -> new String[size]);

        assertArrayEquals(new String[]{"Sport", "Lisboa", "Benfica"}, filteredStrings);
    }

    @Test
    public void mapIteratorTest() {
        Integer[] mappedStrings = QueriesLazy.from(strings)
                .map(s -> s.length())
                .toArray(size -> new Integer[size]);

        assertArrayEquals(new Integer[]{5, 6, 1, 7}, mappedStrings);
    }

    @Test
    public void limitIteratorTest() {
        String[] limitedStrings = QueriesLazy.from(strings)
                .limit(2)
                .toArray(size -> new String[size]);

        assertArrayEquals(new String[]{"Sport", "Lisboa"}, limitedStrings);
    }

    @Test
    public void skipIteratorTest() {
        String[] skippedStrings = QueriesLazy.from(strings)
                .skip(3)
                .toArray(size -> new String[size]);

        assertArrayEquals(new String[]{"Benfica"}, skippedStrings);
    }

    @Test
    public void predicateAndMapAndLimitIteratorsTest() {
        Integer[] compositeResult = QueriesLazy.from(strings)
                .filter(s -> s.contains("a"))
                .map(s -> s.length())
                .limit(1)
                .toArray(size -> new Integer[size]);

        assertArrayEquals(new Integer[]{6}, compositeResult);
    }
}
