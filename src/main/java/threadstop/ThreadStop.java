package threadstop;

/**
 * Если запустить этот код несколько раз,
 * то в консоли можно увидеть разное количество чисел.
 * 1, 2, 3
 * Планировщик выделяет разное время для каждой нити,
 * по этой причине флаг прерывания выставляется
 * в произвольное время.
 * Главная нить выставляет прерывание.
 * thread.interrupt();
 *
 * В свою очередь во второй нити идет проверка этого флага.
 * while (!Thread.currentThread().isInterrupted()) {
 * Если он выставлен, то мы не заходим больше в тело цикла
 * и выходим из метода run().
 *
 */
public class ThreadStop {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(
                () -> {
                    int count = 0;
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.println(count++);
                    }
                }
        );
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}

