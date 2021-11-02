package waitnotify;

public class Barrier {
    /**
     * Переменная flag - это общий ресурс,
     * поэтому мы с ней работаем  только в  критической секции.
     */
    private boolean flag = false;
    /**
     * Синхронизация и методы nofityAll
     * и wait вызываются у объекта класса Barrier
     */
    private final Object monitor = this;

    /**
     * Метод on и off меняют флаг с true на false.
     * После каждого изменения программа будит нити,
     * которые ждут изменений.
     */
    public void on() {
        synchronized (monitor) {
            flag = true;
            monitor.notifyAll();
        }
    }

    /**
     * Когда другая нить выполнит метод on или off,
     * то у объекта монитора выполняется метод notifyAll.
     *
     * Метод notifyAll переводит
     * все нити из состояния wait в runnable.
     */
    public void off() {
        synchronized (monitor) {
            flag = false;
            monitor.notifyAll();
        }
    }

    /**
     * Когда нить заходит в метод check, то она проверяет flag.
     * Если флаг = false, то нить засыпает.
     *
     * Переключение нити из состояния wait в runnable
     * операция не атомарная. Это значит, что состояние программы
     * может поменяться, когда нить начнет выполнять полезную работа.
     * Чтобы избежать проблем с согласованностью данных, метод wait всегда
     * вызывается в цикле while, а не в условном блоке if.
     *
     * Это позволяет сделать постпроверку,
     * когда нить перешла в состояние Runnable.
     */
    public void check() {
        synchronized (monitor) {
            while (!flag) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
