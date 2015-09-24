package action_cast.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bmichaud on 9/14/2015.
 */
public class DataModel {

    private final List<Person> people = new ArrayList<>();
    //private final List<Session> sessions = new ArrayList<>();

    private Session currentSession;

    public DataModel(Session s) {
        currentSession = s;
    }

   /* public void addSession(Session session) {
        sessions.add(session);
    }*/

   /* public List<Session> getSessions() {
        return sessions;
    }*/

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
