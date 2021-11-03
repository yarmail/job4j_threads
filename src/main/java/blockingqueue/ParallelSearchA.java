package blockingqueue;

/**
 * 2. Обеспечить остановку потребителя. [#66825]
 *
 * Представим утилиту по поиску текста в файловой системе.
 * Одна нить ищет файлы с подходящим именем.
 * Вторая нить берет эти файлы и читает.
 * Эта схема хорошо описывается шаблоном Producer-Consumer.
 * Однако есть один момент.
 * Когда первая нить заканчивает свою работу,
 * потребители переходят в режим wait.
 *
 * Задание в файле ParallelSearchB
 */
public class ParallelSearchA {

    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        final Thread consumer = new Thread(
                () -> {
                    while (true) {
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
                        }
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

        ).start();
    }
}