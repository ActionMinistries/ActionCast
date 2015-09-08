package action_cast.model;

/**
 * Created by bmichaud on 9/1/2015.
 */
public class Performer {
    private final Person person;

    public Performer(Person person) {
        this.person = person;
    }

    public String getName() {
        return person.getName();
    }
}
