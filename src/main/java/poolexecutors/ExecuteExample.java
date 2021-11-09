package poolexecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 2. ExecutorService рассылка почты. [#63097]
 * В предыдущем задании мы познакомились,
 * как в примитивном случае можно реализовать пул нитей.
 * (см blockingqueue.SimpleBlockingQueue)
 * Пояснения:
 * ExecutorService pool - Создает пул нитей по количеству доступных процессоров.
 * pool.submit(new Runnable() - Добавляет задачу в пул и сразу ее выполняет.
 */
public class ExecuteExample {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );

        pool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Execute " + Thread.currentThread().getName());
            }
        });
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Execute " + Thread.currentThread().getName());
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}

