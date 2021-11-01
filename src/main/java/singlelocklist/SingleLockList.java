package singlelocklist;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Notes
 *
 * Чтобы обеспечивать потокобезопасность,
 * нужно каждый метод сделать synchonized.
 *
 * Здесь используется шаблон декоратор.
 * Мы добавляем новое поведение для любой коллекции java.util.List.
 * this.list = list.clone(); - clone не работает
 *
 *
 * (есть тесты)
 */
@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("this")
    private final List<T> list;

    public SingleLockList(List<T> list) {
        this.list = copy(list);
    }

    public synchronized void add(T value) {
        list.add(value);
    }

    public synchronized T get(int index) {
        return list.get(index);
    }

    private List<T> copy(List<T> list) {
        return new ArrayList<>(list);
    }

    /**
     * (исходный код см в описании пакета )
     * Этот код работает неверно. Он отдает ссылку на оригинальный итератор.
     * Если мы будем работать с ним, то мы получаем ошибку в share visibility.
     * Самый простой и надежный способ сделать копию данных
     * - это использовать snapshots (копирование всех элементов).     *
     * И уже с него сделать итератор.
     * Этот итератор будет работать в режиме fail-safe
     * - все изменения после получения коллекции не будут отображаться в итераторе.
     * fail-fast - другой режим. при изменении данных во время итерации,
     * коллекция выкинет исключение  ConcurrentModificationException.
     */
    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.list).iterator();
    }
}