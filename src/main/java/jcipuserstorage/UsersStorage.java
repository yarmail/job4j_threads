package jcipuserstorage;

import net.jcip.annotations.ThreadSafe;
import net.jcip.annotations.GuardedBy;

import java.util.Map;
import java.util.HashMap;


/**
 * Примечания
 * Повторить как подключается jcip
 */
@ThreadSafe
public class UsersStorage {
    @GuardedBy("this")
    private final Map<Integer, User> storage = new HashMap<>();

    public synchronized boolean add(User user) {
        return storage.putIfAbsent(user.getId(), user) == null;
    }

    public synchronized boolean delete(User user) {
        return storage.remove(user.getId(), user);
    }

    public synchronized boolean update(User user) {
        return storage.replace(user.getId(), storage.get(user.getId()), user);
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        User userFrom = storage.get(fromId);
        User userTo = storage.get(toId);
        if (userFrom.getAmount() >= userTo.getAmount()) {
            userFrom.setAmount(userTo.getAmount() - amount);
            userTo.setAmount(userTo.getAmount() + amount);
            result = true;
        }
        return result;
    }

    /**
     * пример использования
     */
    public static void main(String[] args) {
        UsersStorage storage = new UsersStorage();
        storage.add(new User(1, 100));
        storage.add(new User(2, 200));
        storage.transfer(1, 2, 50);
    }
}