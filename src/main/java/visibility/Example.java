package visibility;

/**
 * Демонстрация проблемы видимости
 * Операция инкремента выполняется в критической секции.
 * Метод get по своей природе тоже атомарный.
 *
 * Но такой код использовать нельзя.
 * Дело в том, что методы get и increase может быть
 * вызваны параллельно из разных нитей.
 *
 * В этом случае одна нить увеличит счетчик,
 * а вторая эти данные не успеет прочитать.
 * Такая ситуация называется проблемой видимости (visilibity).
 *
 * Чтобы обе нити видели изменения друг другая
 * с общими ресурсами нужно работать только
 * в критической секции.
 *
 */
public class Example {
    private int value;

    public synchronized void increment() {
        value++;
    }

    public int get() {
        return value;
    }
}
