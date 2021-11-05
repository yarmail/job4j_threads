package cascache;

/**
 * Базовая модель данных описывается
 * двумя полями: id, version.
 * ID - уникальный идентификатор.
 * В системе будет только один объект с таким ID.
 *
 * version - определяет достоверность
 * версии в кеше. Подробнее ниже.
 *
 * Поле name - это поля бизнес модели.
 * В нашем примере это одно поле name.
 * Это поле имеет get set методы.
 *
 */
public class Base {
    private final int id;
    private final int version;
    private String name;

    public Base(int id, int version) {
        this.id = id;
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Base{"
                + "id=" + id
                + ", version="
                + version
                + ", name='" + name + '\''
                + '}';
    }
}
