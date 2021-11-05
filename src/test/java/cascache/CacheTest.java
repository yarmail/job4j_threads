package cascache;

import org.junit.Test;
import static org.junit.Assert.*;

public class CacheTest {

    @Test
    public void addTest() {
        Cache cache = new Cache();
        Base model = new Base(1, 0);
        assertTrue(cache.add(model));
    }

    @Test
    public void updateTest() {
        Cache cache = new Cache();
        Base model = new Base(1, 0);
        model.setName("a");
        cache.add(model);
        Base model1 = new Base(1, 0);
        model1.setName("b");
        assertTrue(cache.update(model1));
    }

    @Test(expected = OptimisticException.class)
    public void updateTestWhenException() {
        Cache cache = new Cache();
        Base model = new Base(1, 0);
        cache.add(model);
        Base model1 = new Base(1, 1);
        cache.update(model1);
    }

    @Test
    public void deleteTest() {
        Cache cache = new Cache();
        Base model = new Base(1, 0);
        cache.add(model);
        cache.delete(model);
        Base model1 = new Base(1, 0);
        assertFalse(cache.update(model1));
    }
}