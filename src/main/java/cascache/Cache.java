package cascache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * В кеше должна быть возможность проверять актуальность данных.
 * Для этого в модели данных используется поле int version.
 * Это поле должно увеличиваться на единицу каждый раз,
 * когда модель изменили, то есть вызвали метод update.
 * Даже если все поля остались не измененными поле version
 * должно увеличиться на 1.
 *
 * Например. Два пользователя прочитали объект task ID = 1.
 * Первый пользователь изменил поле имя
 * и второй сделал то же самое.
 * Теперь пользователи сохраняют изменения.
 * Для этого они вызывают метод update.
 *
 * В этом случае возникает ситуация, которая
 * называется "последний выиграл".
 * То есть в кеше сохраняться данные
 * только последнего пользователя.
 *
 * (есть тесты)
 *
 */
public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    /**
     * Давайте реализуем метод add.
     *
     * public boolean add(Base model) {
     *     if (memory.containsKey(model.getId())) {
     *         return false;
     *     }
     *     memory.put(model.getId(), model);
     *     return true;
     * }
     * Эта реализация не потокобезопасная.
     * Ее использовать нельзя. Почему?
     * Хоть сама ConccurentHashMap потокобезопасная,
     * но последовательные вызовы методов нет.
     * Это не атомарные операции.
     *
     * Для решения этих задач нужно использовать CAS методы.
     *
     * Метод putIfAbsent
     * выполняет методы сравнения и вставки,
     * только делает это атомарно.
     *
     */
    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    /**
     * В кеше же нужно перед обновлением данных
     * проверить поле version. Если version у модели
     * и в кеше одинаковы, то можно обновить.
     * Если нет, то выбросить OptimisticException.
     *
     * Перед обновлением данных необходимо проверять
     * текущую версию и ту что обновляем и увеличивать
     * на единицу каждый раз, когда произошло обновление.
     * Если версии не равны -  кидать исключение.
     *
     * Сложность в нашем кеше возникает в методе update.
     * ConcurentHashMap имеет метод computeIfPresent.
     * Этот метод принимает функцию BiFunction и
     * позволяет атомарно обновить нужную ячейку.
     * В нашем случае, если version отличаются
     * нужно выкинуть исключение.
     *
     * Напомню, что вместо конструкции if-else-throw нужно
     * использовать if-throw.
     */
    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(), (a, b) -> {
            if (model.getVersion() != memory.get(model.getId()).getVersion()) {
                throw new OptimisticException("No value in cache");
            }
            return  new Base(model.getId(), model.getVersion() + 1);
        }) != null;
    }

    /**
     *
     * Метод delete работает по аналогии c методом add
     */
    public void delete(Base model) {
        memory.remove(model.getId());
    }
}
