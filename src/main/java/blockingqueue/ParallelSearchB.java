package blockingqueue;

/**
 * 2. Обеспечить остановку потребителя. [#66825]
 *
 * Задание
 * В этом задании нужно разработать механизм
 * остановки потребителя, когда производитель
 * закончил свою работу.
 *
 * ParallelSearchA
 * Если мы запустим этот код, то на консоли мы увидим,
 * что нить производитель закончила работу,
 * а нить потребитель продолжает ждать событий.
 *
 * 1. Изменить код, так, что бы потребитель
 * завершал свою работу.
 * Код нужно изменить в методе main.
 *
 */
public class ParallelSearchB {

    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);

        final Thread consumer = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        try {
                            queue.offer(index);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                    consumer.interrupt();
                }
        ).start();
    }
}