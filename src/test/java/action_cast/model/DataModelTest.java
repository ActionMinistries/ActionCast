package action_cast.model;

import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by bmichaud on 9/14/2015.
 */
public class DataModelTest {

    @Test
    public void testCreate() {
        Session session = new Session(new Date(), new Date());
        DataModel model = new DataModel(session);
        assertNotNull(model);
        assertEquals(session, model.getCurrentSession());
    }

    /*@Test
    public void testAddSession() {
        DataModel model = new DataModel();
        List<Session> sessions = model.getSessions();
        assertEquals(0, sessions.size());
        model.addSession(new Session(new Date(), new Date()));
        sessions = model.getSessions();
        assertEquals(1, sessions.size());
    }*/

    /*@Test
    public void testSetCurrentSession() {
        Session session = new Session(new Date(), new Date());
        Session session1 = new Session(new Date(), new Date());
        DataModel model = new DataModel(session);
        assertNotNull(model);
        assertEquals(session, model.getCurrentSession());
        //model.setCurrentSession();
        assertEquals(session1, model.getCurrentSession());
        assertNotEquals(session, model.getCurrentSession());
    }*/

    @Test
    public void addPerson() {
        Session session = new Session(new Date(), new Date());
        DataModel model = new DataModel(session);
        List<Person> people = model.getPeople();
        assertEquals(0, people.size());
        model.addPerson(new Person("random guy"));
        people = model.getPeople();
        assertEquals(1, people.size());
    }
}