package immutabobj;

/**
 * 3. Immutable объекты [#267918]
 * Проблемы в многопоточной среде возникают из-за объектов,
 * которые могут менять состояния.
 *
 * Правила создания Immutable объекта.
 * 1. Все поля отмечены final.
 * 2. Состояние объекта не изменяется после создания объекта.
 *
 * Задание
 * приведен код не потокобезопасного класса,
 * описывающего узел односвязного списка.
 * Сделайте этот класс Immutable.
 *
 * Было:
 * private Node<T> next;
 * private T value;
 *
 * Стало:
 * private final Node<T> next;
 * private final T value;
 *
 * (пришлось сделать конструктор вместо сеттеров?)
 */
public class Node<T> {
    private final Node<T> next;
    private final T value;

    public Node(Node<T> next, T value) {
        this.next = next;
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public T getValue() {
        return value;
    }
}