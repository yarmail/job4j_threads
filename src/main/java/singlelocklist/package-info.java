package singlelocklist;

/*
4. ThreadSafe динамический список [#1105]
В этой задаче Вам нужно создать коллекцию,
которая будет корректно работать
в многопоточный среде.
То есть сама коллекция будет общим ресурсом
между нитями.
 */

/*
Класс задание
кстати this.list = (List) list.clone(); clone не работает
----
@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    private final List<T> list;

    public SingleLockList(List<T> list) {
        this.list = (List) list.clone();
    }

    public void add(T value) {
    }

    public T get(int index) {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
 */