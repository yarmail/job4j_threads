package cas;

import org.junit.Test;
import java.util.stream.IntStream;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CASCountTest {

    @Test
    public void incrementTest() throws InterruptedException {

        CASCount casCount = new CASCount();
        Thread first = new Thread(() ->
                IntStream.range(1, 8).forEach(a -> casCount.increment()));
        Thread second = new Thread(() ->
                IntStream.range(0, 10).forEach(a -> casCount.increment()));
        Thread third = new Thread(() ->
                IntStream.range(1, 10).forEach(a -> casCount.increment()));

        first.start();
        second.start();
        third.start();

        first.join();
        second.join();
        third.join();

        assertThat(casCount.getVal(), is(27));
    }
}