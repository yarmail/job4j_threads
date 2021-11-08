import blockingqueue.SimpleBlockingQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * 1. Реализовать ThreadPool [#1099]
 * Пул - это хранилище для ресурсов,
 * которые можно переиспользовать.
 * Клиент берет ресурс из пула, выполняет
 * свою работу и возвращает обратно в пул.
 *
 * 1.Инициализация пула должна быть по
 * количеству ядер в системе.
 *
 * int size = Runtime.getRuntime().availableProcessors()
 * Количество нитей всегда одинаковое и равно size.
 * В каждую нить передается блокирующая очередь tasks.
 *
 * В методе run мы должны получить
 * задачу их очереди tasks.
 *
 * tasks - это блокирующая очередь.
 * Если в очереди нет элементов, то нить
 * переводиться в состоянии Thread.State.WAITING.
 *
 * Когда приходит новая задача, всем нитям
 * в состоянии Thread.State.WAITING посылается
 * сигнал проснуться и начать работу.
 *
 * 2. Создать метод work(Runnable job) -
 * этот метод должен добавлять задачи в
 * блокирующую очередь tasks.
 *
 * 3. Создать метод shutdown() - этот метод завершит
 * все запущенные задачи.
 *
 * (есть тесты)
 */
public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final int size = Runtime.getRuntime().availableProcessors();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size);

    public ThreadPool() {
        for (int i = 0; i < size; i++) {
            Thread thread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        tasks.poll().run();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            threads.add(thread);
            thread.start();
        }
    }

    /**
     * 2. Создать метод work(Runnable job) -
     * этот метод должен добавлять задачи в
     * блокирующую очередь tasks.
     */
    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    /**
     *  Создать метод shutdown() - этот метод завершит
     *  все запущенные задачи.
     *  Можно так:
     *  threads.forEach(Thread::interrupt);
     */
    public void shutdown() {
        for (Thread thread: threads) {
            thread.interrupt();
        }
    }
}