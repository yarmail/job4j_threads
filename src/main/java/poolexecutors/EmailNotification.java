package poolexecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * pool - Создает пул нитей по количеству доступных процессоров.
 *
 */
public class EmailNotification {
    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    /**
     * В классе будет метод emailTo(User user)
     * он должен через ExecutorService отправлять почту.
     */
    public void emailTo(User user) {
        pool.submit(new Thread(() -> {
            String subject = String.format("Notification %s to email %s", user.getUsername(), user.getEmail());
            String body = String.format("Add a new event to %s", user.getUsername());
            send(subject, body, user.getEmail());
            System.out.println(subject);
            System.out.println(body);
        }));
    }

    /**
     * Так же добавьте метод close() - он должен закрыть pool.
     */
    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String subject, String body, String email) {
    }
}