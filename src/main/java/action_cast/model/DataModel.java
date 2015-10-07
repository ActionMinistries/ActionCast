package action_cast.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bmichaud on 9/14/2015.
 */
@XmlRootElement
public class DataModel {

    @XmlElement
    private final List<Person> people = new ArrayList<>();

    private Session currentSession = null;

    public static DataModel instance = null;

    public DataModel() {
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

    public void setCurrentSession(Session s) {
        currentSession = s;
    }
}
