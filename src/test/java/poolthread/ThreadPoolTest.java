package poolthread;

import org.junit.Ignore;
import org.junit.Test;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Тест работает некоректно, по возможности исправить
 */
@Ignore
public class ThreadPoolTest {

    @Test
    public void workTest() {
        final int size = Runtime.getRuntime().availableProcessors();
        List<Runnable> list = new ArrayList<>();
        List<Integer> listCount = new ArrayList<>();
        ThreadPool threadPool = new ThreadPool();
        for (int i = 0; i < size; i++) {
            Runnable job = new Runnable() {
                int count;

                @Override
                public void run() {
                    count++;
                    listCount.add(count);
                }
            };
            list.add(job);
        }

        for (Runnable job : list) {
            try {
                threadPool.work(job);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threadPool.shutdown();
    }

    @Test
    public void whenMoreThanSize() {
        Set<String> set = new LinkedHashSet<>();
        ThreadPool threadPool = new ThreadPool();
        for (int i = 0; i < 100; i++) {
            try {
                threadPool.work(() -> {
                    String name = Thread.currentThread().getName();
                    System.out.println(name + " working");
                    set.add(name);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threadPool.shutdown();
     }
}