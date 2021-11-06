package cas;

import java.util.concurrent.atomic.AtomicReference;
import net.jcip.annotations.ThreadSafe;

/**
 * Задание
 * 1. Реализовать неблокирующий счетчик.
 * механизм оптимистичной блокировки и позволяющий
 * изменить значение счетчика только в том случае,
 * если оно равно ожидаемому значению.
 *
 * (есть тесты)
 */
@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>(1);

    public void increment() {
        int a;
        do {
            a = count.get();
        }
        while (!count.compareAndSet(a, a + 1));
    }

    public int getVal() {
        return count.get();
    }
}