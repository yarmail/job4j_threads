package commonresources;

public class User {
    private int id;
    private String name;

    /**
     * ???
     * это статический метод, как-то связан
     * со статическим фабричным методом
     *
     */
    public static User of(String name) {
        User user = new User();
        user.name = name;
        return user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}