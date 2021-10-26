package threadstate;

/**
 * Примечания:
 * Обратите внимание, что тело цикла
 * while выполняется произвольное
 * количество раз:
 * NEW
 * RUNNABLE
 * RUNNABLE
 * RUNNABLE
 * TERMINATED
 *
 * За управление нитями в Java отвечает планировщик
 * задач. Он решает, сколько времени отвести на выполнение одной задачи.
 * Это время зависит от текущей ситуации.
 * Если задач много, то переключение между нитями будет частое.
 *
 * В цикле мы проверяем состояние запущенной нити.
 * while (first.getState() != Thread.State.TERMINATED)
 *      System.out.println(first.getState());
 *
 * Главная нить исполнения main будет работать до тех пор,
 * пока нить first не завершит свою работу.
 * Приведенное здесь решение ожидания завершения
 * нити не является эффективным, но оно показывает,
 * как нить переходит между своими состояниями
 *
 *
 */
public class ThreadState {

    /**
     * Задание
     * Поправьте класс ThreadState там образом,
     * чтобы в нем создавалось две нити.
     * Каждая нить должна вывести свое имя на консоль.
     * <p>
     * Нить main должна дождаться завершения этих нитей
     * и вывести на консоль сообщение, что работа завершена.
     */
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> {
                }
        );
        System.out.println(first.getName());
        first.start();

        Thread second = new Thread(
                () -> {
                }
        );
        System.out.println(second.getName());
        second.start();

        while (true) {
            if (first.getState() == Thread.State.TERMINATED
                    && second.getState() == Thread.State.TERMINATED) {
                System.out.println("Работа завершена");
                break;
            }
        }
    }
}
/*
Вывод
Thread-0
Thread-1
Работа завершена
 */

/*
состояние main до начала задания
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> {}
        );
        System.out.println(first.getState());
        first.start();
        while (first.getState() != Thread.State.TERMINATED) {
            System.out.println(first.getState());
        }
        System.out.println(first.getState());
    }
 */