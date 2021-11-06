package singleton;

/**
 * Поле экземпляра обозначено volatile.
 * Это обеспечит решение проблемы видимости
 * после инициализации поля.
 * Первая проверка экземпляра идет до
 * блока синхронизации, что позволяет улучшить
 * скорость работы по сравнению с
 * single checked locking реализацией.
 * В критической секции происходит инициализация
 * экземпляра и запись его в переменную.
 */
public class DoubleLocking {
    private static volatile DoubleLocking instance;

    private DoubleLocking() {
    }

    public static DoubleLocking getInstance() {
        if (instance == null) {
            synchronized (DoubleLocking.class) {
                if (instance == null) {
                    instance = new DoubleLocking();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        DoubleLocking tracker = DoubleLocking.getInstance();
    }
}
