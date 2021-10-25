package threadstart;

/**
 * Thread.currentThread().getName() -
 * Этот статический метод позволяет получить
 * экземпляр текущей нити выполнения.
 * То есть той нити в который выполняется этот оператор.
 *
 * Обратите внимание, что создание переменной
 * another идет в нити main,
 * но вызов выражения описанного в конструкторе идет
 * (вывод на консоль) уже в нити с именем Thread-0.
 *
 * Метод Thread#start() указывает виртуальной машине,
 * что операторы описанные в конструкторе нужно запустить
 * в отдельной нити.
 * Если убрать этот оператор, another.start(); то вывода
 * имени второй нити не будет.
 *
 * Concurrent - паралельный
 */
public class ThreadStart {

    /**
     * Задание
     * В метод main класса создайте еще один объект Thread.
     * Присвойте имя переменной second.
     *
     * В конструкторе нового объекта задайте вывод
     * на консоль имени новой нити.
     *  Для этого воспользуйтесь оператором.
     * Thread.currentThread().getName();
     *
     * Запустите нить на выполнение.
     * Для этого вызовите у объекта метод Thread#start().
     * Запустите метод main несколько раз и убедитесь,
     * что последовательность вывода имен нитей всегда произвольная.
     */
    public static void main(String[] args) {
        Thread another = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        another.start();
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        second.start();
        System.out.println(Thread.currentThread().getName());
    }
}
/*
Вывод:
Thread-0
main
Thread-1
 */