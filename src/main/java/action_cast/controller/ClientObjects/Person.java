package action_cast.controller.ClientObjects;

/**
 * Created by bmichaud on 12/8/2015.
 */
public class Person {

    private final int id;
    private String name;

    public Person(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
