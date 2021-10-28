package volatilefield;

/**
 * 2. Модель памяти Java [#267917 #221548]
 * Может возникнуть ситуация, что главная
 * нить запишет новое значение переменной в кеш процессора,
 * а дополнительная нить будет продолжать читать
 * переменную из регистра.
 *
 * Эта ситуация называется проблемой видимости
 * (share visibility problem).
 *
 * Чтобы ее решить, можно использовать синхронизацию.
 * Но, в данном случае, она избыточна.
 *
 * В Java есть облегченный механизм синхронизации - volatile.
 *
 * Его можно использовать только в том случае, когда общий
 * ресурс не обновляется в зависимости от своего состояния.
 *
 * Например, для инкремента его использовать нельзя.
 *
 * volаtile - это ключевое слово,
 * которое используется для полей класса.
 *
 * Если поле класса обозначено volatile,
 * то чтение и запись переменной будет происходить
 * только из RAM памяти процессора.
 *
 * Задание
 * 1. Ниже приведен код синглтона - double check locking.
 * Исправьте в нем ошибку
 * Было:
 * private static DCLSingleton inst;
 *
 * Стало:
 * private volatile static DCLSingleton inst;
 *
 *
 *
 */
public final class DCLSingleton {
    private volatile static DCLSingleton inst;

    private DCLSingleton() {
    }

    public static DCLSingleton instOf() {
        if (inst == null) {
            synchronized (DCLSingleton.class) {
                if (inst == null) {
                    inst = new DCLSingleton();
                }
            }
        }
        return inst;
    }
}