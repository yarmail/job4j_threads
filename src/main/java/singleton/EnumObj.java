package singleton;

/**
 * Объект enum создается при загрузке
 * класса и безопасно публикуется всем клиентам.
 */
public enum EnumObj {
    INSTANCE;

    public static void main(String[] args) {
        EnumObj tracker = EnumObj.INSTANCE;
    }
}