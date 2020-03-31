package weather.queries.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;

public class SkipIterator<T> extends BaseIterator<T> {
    private final Iterator<T> srcIter;
    private int nrToSkip;

    public SkipIterator(Iterable<T> src, int n) {
        this.srcIter = src.iterator();
        this.nrToSkip = n;
    }

    @Override
    protected boolean tryAdvance(Consumer<T> consumer) {
        /* on the first call to hasNext skip the first nrToSkip elements of the collection */
        while (nrToSkip > 0 && srcIter.hasNext()) {
            srcIter.next();
            --nrToSkip;
        }
        if (srcIter.hasNext()) {
            consumer.accept(srcIter.next());
            return true;
        }
        return false;
    }


}
