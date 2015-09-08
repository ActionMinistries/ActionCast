package action_cast.model;

/**
 * Created by bmichaud on 9/1/2015.
 */
public class Director {
    private final Person person;

    public Director(Person person) {
        this.person = person;
    }

    public String getName() {
        return person.getName();
    }
}
