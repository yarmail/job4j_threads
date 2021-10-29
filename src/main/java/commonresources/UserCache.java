package commonresources;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Задание
 * Программист решил расширить класс UserCache
 * и добавил в него новый метод findAll.
 * Исправьте ошибку в этом коде.
 *
 * Было:
 * public List<User> findAll() {
 * return new ArrayList<>(users.values());
 * }
 */
public class UserCache {
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    public void add(User user) {
        users.put(id.incrementAndGet(), User.of(user.getName()));
    }

    public User findById(int id) {
        return User.of(users.get(id).getName());
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        for (User user : users.values()) {
            list.add(User.of(user.getName()));
        }
        return list;
    }
}