package action_cast.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bmichaud on 9/14/2015.
 */
@XmlRootElement
public class DataModel {

    private final List<Person> people = new ArrayList<>();

    private Session currentSession;

    public static DataModel instance = null;

    public DataModel(Session s) {
        currentSession = s;
        instance = this;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void addPerson(Person person) {
        people.add(person);
    }

    public Session getCurrentSession() {
        return currentSession;
    }
}
