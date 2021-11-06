package singleton;

public class SingleLocking {
    private static SingleLocking instance;

    private SingleLocking() {
    }

    public static synchronized SingleLocking getInstance() {
        if (instance == null) {
            instance = new SingleLocking();
        }
        return instance;
    }

    public static void main(String[] args) {
        SingleLocking tracker = SingleLocking.getInstance();
    }
}