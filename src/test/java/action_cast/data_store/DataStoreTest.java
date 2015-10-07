package action_cast.data_store;

import action_cast.model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by bmichaud on 10/6/2015.
 */
public class DataStoreTest {

    @Test
    public void testCreate() {
        DataStore store = new DataStore(new DataModel());
        assertNotNull(store);
    }

    @Test
    public void testSaveSession() {
        DataStore store = new DataStore(new DataModel());
        long time = System.currentTimeMillis();
        Date date1 = new Date(time);
        Date date2 = new Date(time + 5000);

        store.getModel().setCurrentSession(new Session("savedSession", date1, date2));
        store.save();
        DataStore store2 = new DataStore();
        store2.load();
        assertEquals("savedSession", store2.getModel().getCurrentSession().getName());
        assertEquals(date1, store2.getModel().getCurrentSession().getStartDate());
        assertEquals(date2, store2.getModel().getCurrentSession().getEndDate());

    }

    @Test
    public void testSaveSession_extended() {
        DataStore store = new DataStore(new DataModel());

        store.getModel().addPerson(new Person("random guy"));
        store.getModel().addPerson(new Person("odd job"));
        store.getModel().addPerson(new Person("ted"));
        store.getModel().addPerson(new Person("fred"));

        long time = System.currentTimeMillis();
        Date date1 = new Date(time);
        Date date2 = new Date(time + 5000);

        Session session = new Session("savedSession", date1, date2);
        session.addPerson(new Person("blargh"));
        session.addPerson(new Person("someone"));

        Song fiveHundredMiles = new Song("500 miles", "500 more");
        Song run = new Song("I just wanna run.", "throw it away");

        List<Role> roles = new ArrayList<>();
        roles.add(new Role("Runner", "The guy who runs", RoleType.MAIN));
        run.setRoles(roles);

        Performance runPerformance = new Performance(run, "primary", "OTS", new Date());
        runPerformance.setDirector(new Director(store.getModel().getPeople().get(1)));

        session.addPerformer(new Performer(store.getModel().getPeople().get(0)));
        runPerformance.assign(session.getPerformers().get(0), roles.get(0));

        session.addPerformance(new Performance(fiveHundredMiles, "encore", "OTS", new Date()));
        session.addPerformance(runPerformance);
        session.addPerformance(new Performance(fiveHundredMiles, "never", "OTS", new Date()));


        store.getModel().setCurrentSession(session);
        store.save();
        DataStore store2 = new DataStore();
        store2.load();
        assertEquals("savedSession", store2.getModel().getCurrentSession().getName());
        assertEquals(date1, store2.getModel().getCurrentSession().getStartDate());
        assertEquals(date2, store2.getModel().getCurrentSession().getEndDate());
        assertEquals(session.getPeople().size(), store2.getModel().getCurrentSession().getPeople().size());
        assertEquals(session.getPerformances().size(), store2.getModel().getCurrentSession().getPerformances().size());
        assertEquals(session.getPerformers().size(), store2.getModel().getCurrentSession().getPerformers().size());
    }


    @Test
    public void testSavePeople() {
        DataStore store = new DataStore(new DataModel());
        store.getModel().addPerson(new Person("bob"));
        store.getModel().addPerson(new Person("ted"));
        store.getModel().addPerson(new Person("fred"));
        store.save();

        DataStore store2 = new DataStore();
        store2.load();
        assertEquals(3, store2.getModel().getPeople().size());
    }

    @Test
    public void testLoad() {

    }
}