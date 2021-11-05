package cas;

import net.jcip.annotations.ThreadSafe;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Чтобы сделать Stack потокобезопасным мы будем использовать класс
 * java.util.concurrent.atomic.AtomicReference
 * Это класс поддерживает CAS операции.
 *
 * Здесь нет синхронизации, но класс потокобезопасный. Разберемся почему.
 * Вначале мы считываем указатель.
 * ref = head.get();
 *
 * Указатель могут считать несколько нитей одновременной. Мы не блокируем эту операцию.
 * while (!head.compareAndSet(ref, temp));
 *
 * Метод compareAndSet атомарный.
 * Это значит если две нити прочитали одно и тоже значение ref,
 * то первый вызов compareAndSet даст true, а второй вызов вернет false.
 *
 * Вторая нить будет заново читать ref и выполнять операцию compareAndSet.
 * Обе нити не блокируются, а выполняются параллельно.
 *
 */
@ThreadSafe
public class CASStack<T> {
    private final AtomicReference<Node<T>> head = new AtomicReference<>();

    public void push(T value) {
        Node<T> temp = new Node<>(value);
        Node<T> ref;
        do {
            ref = head.get();
            temp.next = ref;
        } while (!head.compareAndSet(ref, temp));
    }

    public T poll() {
        Node<T> ref;
        Node<T> temp;
        do {
            ref = head.get();
            if (ref == null) {
                throw new IllegalStateException("Stack is empty");
            }
            temp = ref.next;
        } while (!head.compareAndSet(ref, temp));
        ref.next = null;
        return ref.value;
    }

    private static final class Node<T> {
        final T value;

        Node<T> next;

        public Node(final T value) {
            this.value = value;
        }
    }
}