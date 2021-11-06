package singleton;

public class FinalObj {
    private static final FinalObj INSTANCE = new FinalObj();

    private FinalObj() {
    }

    public static FinalObj getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        FinalObj tracker = FinalObj.getInstance();
    }
}
