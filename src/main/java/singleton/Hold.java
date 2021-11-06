package singleton;

public class Hold {
    private Hold() {
    }

    public static Hold getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final Hold INSTANCE = new Hold();
    }

    public static void main(String[] args) {
        Hold tracker = Hold.getInstance();
    }
}
