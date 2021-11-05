package cascache;

/**
 * Насколько я помню, это самострочный эксепшен
 *
 * В кеше же нужно перед обновлением данных
 * проверить поле version. Если version у модели
 * и в кеше одинаковы, то можно обновить.
 *
 * Если нет, то выбросить OptimisticException.
 * Перед обновлением данных необходимо проверять
 * текущую версию и ту что обновляем и увеличивать
 * на единицу каждый раз, когда произошло обновление.
 * Если версии не равны -  кидать исключение.
 */
public class OptimisticException extends RuntimeException {

    public OptimisticException(String message) {
        super(message);
    }
}