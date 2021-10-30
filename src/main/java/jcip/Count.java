package jcip;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * есть тесты
 */
@ThreadSafe
public class Count {
    @GuardedBy("this")
    private int value;

    public void increment() {
        this.value++;
    }

    public int get() {
        return this.value;
    }
}
