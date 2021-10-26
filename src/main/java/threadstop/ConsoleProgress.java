package threadstop;

/**
 * Задание к теме Прерывание нити
 * Создайте класс ConsoleProgress.
 * Этот класс будет использован для вывода
 * процесса загрузки в консоль
 *
 * Этот класс должен реализовывать интерфейс java.lang.Runnable.
 * Внутри метода run нужно добавить цикл  с проверкой флага.
 *
 * Внутри цикла - сделать задержку в 500 мс.
 * В тело цикла добавьте вывод в консоль.
 */
public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        String[] symbol = {"\\", "|", "/", "-"};
        while (!Thread.currentThread().isInterrupted()) {
            for (String s : symbol) {
                System.out.print("\r load: " + s);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Thread.sleep(1000) -
     * симулируем выполнение
     * параллельной задачи в течение 1 секунды.
     */
    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(1000);
        progress.interrupt();
    }
}