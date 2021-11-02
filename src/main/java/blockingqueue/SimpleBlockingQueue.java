package blockingqueue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Каждой нити нужно передать объект:
 * new SimpleBlockingQueue<Integer>()
 * Этот объект будет общим ресурсом между этими нитями.
 *
 * Метод poll() должен вернуть объект
 * из внутренней коллекции. Если в коллекции объектов нет,
 * то нужно перевести текущую нить в состояние ожидания.
 *
 * Важный момент, когда нить переводить в состояние ожидания,
 * то она отпускает объект монитор и другая нить
 * тоже может выполнить этот метод.
 *
 * Реализовать методы poll() и offer().
 * Написать тесты. В тестах должны быть
 * две нити: одна производитель, другая потребитель.
 * (есть тесты)
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int limit;

    public SimpleBlockingQueue(int limit) {
        this.limit = limit;
    }

    public synchronized int getQueueSize() {
        return queue.size();
    }

    /**
     * Метод offer() добавляет элементы
     * в очередь в синхронизированном режиме
     */
    public void offer(T value) throws InterruptedException {
        synchronized (this) {
            if (queue.size() == limit) {
                this.wait();
            }
            queue.offer(value);
            this.notify();
        }
    }

    /**
     * Метод poll() извлекает элементы из очереди в синхронизированном режиме.
     * Если в коллекции объектов нет, то текущий поток переводится в состояние ожидания.
     * когда поток переводится в состояние ожидания, то он отпускает объект монитор
     * и другой поток тоже может выполнить этот метод.
     */
    public T poll() throws InterruptedException {
        synchronized (this) {
            if (queue.isEmpty()) {
                this.wait();
            }
            T value = queue.poll();
            this.notify();
            return value;
        }
    }
}