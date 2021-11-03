package blockingqueue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * 3. Junit тест для блокирующей очереди. [#68589]
 * В тесте должны быть две нити. Надо обеспечить,
 * что бы главная нить ждала выполнение других нитей.
 *
 * Дополнительно нам нужно обеспечивать
 * параллельное выполнение потребителя
 * и производителя.
 *
 * Так же сложность возникает в блокировке
 * потребителя или производителя, если очередь
 * пустая или переполненная.
 *
 * Если она пустая нужно произвести остановку
 * нити и проверить результат.
 * Если очередь переполненная нужно
 * приостановить производителя и дать
 * возможность просчитать потребителю.
 *
 * Примечания
 * buffer - нужен, чтобы собрать все данные
 * в список и проверить их в конце выполнения
 * теста.
 * while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
 * Здесь мы проверяем, что очередь пустая или нить выключили.
 *
 * Зачем нужна двойная проверка.
 * Если производитель закончил свою работу
 * и сразу подаст сигнал об отключении
 * потребителя, то мы не сможем прочитать
 * все данные.
 *
 * С другой стороны, если мы успели прочитать
 * все данные и находимся в режиме wait
 * пришедший сигнал запустит нить
 * и проверит состояние очереди и завершит цикл.
 * Потребитель закончит свою работу.
 *
 * Давайте добавим проверку и запустим наш тест.
 * assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
 *
 * Если мы запустим наш тест несколько раз,
 * то мы увидим. что наш тест иногда работает,
 * а иногда нет.
 *
 * java.lang.AssertionError:
 * Expected: is <[0, 1, 2, 3, 4]>
 * but: was <[]>
 *
 * Это связано с тем. что главная нить
 * не дожидается выполнения потребителя
 * и производителя.
 *
 * Давайте добавим ожидание.
 * producer.join();
 * consumer.interrupt();
 * consumer.join();
 *
 * Сначала дожидаемся завершения работы
 * производителя.
 * Далее посылаем сигнал, что потребителю
 * можно остановиться.
 * Ждем пока потребитель прочитает
 * все данные и завершит свою работу.
 */
public class SimpleBlockingQueueTest {

    @Test
    public void offerTest() {
        SimpleBlockingQueue<Integer> sb = new SimpleBlockingQueue<>(5);
        Runnable  producer = () -> {
            for (int i = 1; i < 6; i++) {
                try {
                    sb.offer(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread p = new Thread(producer);
        p.start();
        try {
            p.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(5, sb.getQueueSize());
    }

    @Test
    public void offerAndPollTest() {
        List<Integer> expected = List.of(1, 2, 3, 4, 5);
        List<Integer> actual = new ArrayList<>();
        SimpleBlockingQueue<Integer> sb = new SimpleBlockingQueue<>(5);
        Runnable producer = () -> {
            for (int i = 1; i < 6; i++) {
                try {
                    sb.offer(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable consumer = () -> {
            while (sb.getQueueSize() != 0 || Thread.currentThread().isInterrupted()) {
                try {
                    actual.add(sb.poll());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread p = new Thread(producer);
        Thread c = new Thread(consumer);
        p.start();
        c.start();
        try {
            p.join();
            c.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(expected, actual);
    }

    @Test
    public void whenFetchAllThenGetIt() {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        Thread producer = new Thread(
                () -> {
                    for (int i = 1; i < 6; i++) {
                        try {
                            queue.offer(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (queue.getQueueSize() != 0
                            || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (Exception e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        try {
            producer.join();
            consumer.interrupt();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(buffer, is(Arrays.asList(1, 2, 3, 4, 5)));
    }
}