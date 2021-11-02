package waitnotify;

/**
 * 1. Разработайте класс, который блокирует выполнение по условию счетчика.
 * Переменная total содержит количество вызовов метода count().
 * Метод count изменяет состояние программы. Это значит, что
 * внутри метода count нужно вызывать метод notifyAll.
 *
 * Нити, которые выполняют метод await, могут начать работу
 * если поле count >= total. Если оно не равно,
 * то нужно перевести нить в состояние wait.
 *
 * Здесь нужно использовать цикл while для
 * проверки состояния, а не оператор if.
 *
 */
public class CountBarrier {
    private final Object monitor = this;
    private final int total;
    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
            monitor.notifyAll();
            count++;
        }

    }

    public void await() {
        synchronized (monitor) {
            while (count < total) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}