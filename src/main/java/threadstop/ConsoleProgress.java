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
    /**
     * 3.1. Прерывание блокированной нити. [#267413]
     * Метод Thread.interrupt() не выставляет флаг прерывания,
     * если нить находится в режиме WAIT, JOIN.
     * В этом случае методы sleep(), join(), wait()
     * выкинут исключение. Поэтому нужно дополнительно
     * проставить флаг прерывания.
     *
     * В блоке catch нужно дополнительно вызывать
     * прерывание нити для того чтобы прерывания
     * действительно произошло.
     * Было:
     * catch (InterruptedException e) {
     * e.printStackTrace();
     *
     * Стало:
     * catch (InterruptedException e) {
     * Thread.currentThread().interrupt();
     *
     * Эта схема является шаблоном.
     * Запомните, если используются методы
     * sleep(), join() или wait(), то нужно
     * в блоке catch вызвать прерывание.
     *
     * Задание.
     * Поправьте код в классе ConsoleProgress.
     * В блоке catch уберите исключение и добавьте прерывание.
     *
     */
    @Override
    public void run() {
        String[] symbol = {"\\", "|", "/", "-"};
        while (!Thread.currentThread().isInterrupted()) {
            for (String s : symbol) {
                System.out.print("\r load: " + s);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    /**
     * Thread.sleep(1000) -
     * симулируем выполнение
     * параллельной задачи в течение 1 секунды.
     *
     * Примечание:
     * Вероятно прерывание после sleep() выдает ошибку
     */
    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(1000);
        progress.interrupt();
    }
}