package action_cast.data_store;

import action_cast.model.*;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by bmichaud on 10/6/2015.
 */
public class DataStoreTest {

    DataStore dataStore;

    @Before
    public void setUp() throws JAXBException, SAXException {
        ClassLoader classLoader = getClass().getClassLoader();
        dataStore = new DataStore(classLoader.getResource("test.xml").getFile());
        dataStore.load();
    }

    @Test
    public void testCreate() {
        DataStore store = new DataStore(new DataModel());
        assertNotNull(store);
    }

    @Test
    public void testSaveSession() throws JAXBException, SAXException {
        DataStore store = new DataStore(new DataModel());
        long time = System.currentTimeMillis();
        Date date1 = new Date(time);
        Date date2 = new Date(time + 5000);

        store.getModel().setCurrentSession(new Session("savedSession", date1, date2));
        store.save();
        DataStore store2 = new DataStore("file.xml");
        store2.load();
        assertEquals("savedSession", store2.getModel().getCurrentSession().getName());
        assertEquals(date1, store2.getModel().getCurrentSession().getStartDate());
        assertEquals(date2, store2.getModel().getCurrentSession().getEndDate());
    }

    @Test
    public void testLoad() {
        DataModel dataModel = dataStore.getModel();
        assertNotNull(dataModel);
        assertNotNull(dataModel.getCurrentSession());
        assertNotNull(dataModel.getPeople());
        assertEquals(4, dataModel.getPeople().size());
        assertEquals("random guy", dataModel.getPeople().get(0).getName());
        assertEquals("odd job", dataModel.getPeople().get(1).getName());
        assertEquals("ted", dataModel.getPeople().get(2).getName());
        assertEquals("fred", dataModel.getPeople().get(3).getName());
        Session currentSession = dataModel.getCurrentSession();
        assertEquals("savedSession", currentSession.getName());
        assertEquals(2, currentSession.getPeople().size());
        assertEquals("random guy", currentSession.getPeople().get(0).getName());
        assertEquals("odd job", currentSession.getPeople().get(1).getName());

        assertEquals(3, currentSession.getPerformances().size());
        assertEquals(1, currentSession.getPerformers().size());
        assertEquals("random guy", currentSession.getPerformers().get(0).getName());
    }

    @Test
    public void testLoad_DataCorrelation() {
        DataModel dataModel = dataStore.getModel();
        Session currentSession = dataModel.getCurrentSession();
        assertTrue(dataModel.getPeople().get(0) == currentSession.getPeople().get(0));
        assertTrue(dataModel.getPeople().get(0).getName() == currentSession.getPeople().get(0).getName());
    }

    @Test
    public void testSaveSession_extended() throws JAXBException, SAXException {
        DataStore store = new DataStore(new DataModel());

        store.getModel().addPerson(new Person("random guy"));
        store.getModel().addPerson(new Person("odd job"));
        store.getModel().addPerson(new Person("ted"));
        store.getModel().addPerson(new Person("fred"));

        long time = System.currentTimeMillis();
        Date date1 = new Date(time);
        Date date2 = new Date(time + 5000);

        Session session = new Session("savedSession", date1, date2);
        session.addPerson(store.getModel().getPeople().get(0));
        session.addPerson(store.getModel().getPeople().get(1));

        Song fiveHundredMiles = new Song("500 miles", "500 more");
        Song run = new Song("I just wanna run.", "throw it away");

        List<Role> roles = new ArrayList<>();
        roles.add(new Role("Runner", "The guy who runs", RoleType.MAIN));
        run.setRoles(roles);

        Performance runPerformance = new Performance(run, "primary", "OTS", new Date());
        runPerformance.setDirector(new Director(store.getModel().getPeople().get(1)));

        session.addPerformer(new Performer(session.getPeople().get(0)));
        runPerformance.assign(session.getPerformers().get(0), roles.get(0));

        session.addPerformance(new Performance(fiveHundredMiles, "encore", "OTS", new Date()));
        session.addPerformance(runPerformance);
        session.addPerformance(new Performance(fiveHundredMiles, "never", "OTS", new Date()));

        store.getModel().setCurrentSession(session);
        store.save();
        DataStore store2 = new DataStore("file.xml");
        store2.load();
        assertEquals("savedSession", store2.getModel().getCurrentSession().getName());
        assertEquals(date1, store2.getModel().getCurrentSession().getStartDate());
        assertEquals(date2, store2.getModel().getCurrentSession().getEndDate());
        assertEquals(session.getPeople().size(), store2.getModel().getCurrentSession().getPeople().size());
        assertEquals(session.getPerformances().size(), store2.getModel().getCurrentSession().getPerformances().size());
        assertEquals(session.getPerformers().size(), store2.getModel().getCurrentSession().getPerformers().size());
    }

    @Test
    public void testSavePeople() throws JAXBException, SAXException {
        DataStore store = new DataStore(new DataModel());
        store.getModel().addPerson(new Person("bob"));
        store.getModel().addPerson(new Person("ted"));
        store.getModel().addPerson(new Person("fred"));
        store.save();

        DataStore store2 = new DataStore("file.xml");
        store2.load();
        assertEquals(3, store2.getModel().getPeople().size());
    }
}