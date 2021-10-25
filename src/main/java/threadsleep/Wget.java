package threadsleep;

/**
 * Создайте класс Wget и метод main.
 * В методе main необходимо симулировать процесс загрузки.
 * Для этого воспользуйтесь особенностью вывода на консоль.
 * System.out.print("\rLoading : " + index  + "%");
 * Метод print печатает символы в строку без перевода каретки.
 * Символ \r указывает, что каретку каждый раз нужно вернуть в начало строки.
 * Это позволяет через промежуток времени обновить строчку.
 *
 * Thread.sleep переводит нить в состояние TIMED_WAITING
 */
public class Wget {
    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    try {
                        for (int index = 0; index <= 100; index++) {
                            System.out.print("\rLoading : " + index + "%");
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                );
        thread.start();
    }
}