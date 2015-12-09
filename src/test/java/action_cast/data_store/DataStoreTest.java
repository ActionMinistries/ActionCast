package action_cast.data_store;

import action_cast.model.*;
import action_cast.model.exceptions.InvalidIDException;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

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
    public void testLoad() throws InvalidIDException {
        DataModel dataModel = dataStore.getModel();
        assertNotNull(dataModel);
        assertNotNull(dataModel.getCurrentSession());
        //assertNotNull(dataModel.getPeople());
        //assertEquals(4, dataModel.getPeople().size());
        assertEquals("random guy", dataModel.getPerson(0).getName());
        assertEquals("odd job", dataModel.getPerson(1).getName());
        assertEquals("ted", dataModel.getPerson(2).getName());
        assertEquals("fred", dataModel.getPerson(3).getName());
        boolean thrown = false;
        try {

            dataModel.getPerson(4);
        }
        catch (Throwable t) {
            thrown = true;
        }
        assertTrue(thrown);
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
    public void testLoad_DataCorrelation() throws InvalidIDException {
        DataModel dataModel = dataStore.getModel();
        Session currentSession = dataModel.getCurrentSession();
        assertTrue(dataModel.getPerson(0) == currentSession.getPeople().get(0));
        assertTrue(dataModel.getPerson(0).getName() == currentSession.getPeople().get(0).getName());
    }

    @Test
    public void testLoad_DataCorrelation_Songs() throws InvalidIDException {
        DataModel dataModel = dataStore.getModel();
        Session currentSession = dataModel.getCurrentSession();
        assertTrue(dataModel.getSong(1) == currentSession.getPerformances().get(1).getSong());
        assertTrue(dataModel.getSong(0) == currentSession.getPerformances().get(0).getSong());
        assertTrue(dataModel.getSong(0) == currentSession.getPerformances().get(2).getSong());
    }


    @Test
    public void testSaveSession_extended() throws JAXBException, SAXException, InvalidIDException {
        DataStore store = new DataStore(new DataModel());

        Person randomGuy = store.getModel().addPerson("random guy");
        Person oddJob = store.getModel().addPerson("odd job");
        store.getModel().addPerson("ted");
        store.getModel().addPerson("fred");

       // Song fiveHundredMiles = new Song();
        //Song run = new Song("I just wanna run.", "throw it away");



        Song fiveHundredMiles = store.getModel().addSong("500 miles", "500 more");
        Song run = store.getModel().addSong("I just wanna run.", "throw it away");
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("Runner", "The guy who runs", RoleType.MAIN));
        store.getModel().getSong(run.getIndex()).setRoles(roles);
       // store.getModel().addSong(fiveHundredMiles);


        long time = System.currentTimeMillis();
        Date date1 = new Date(time);
        Date date2 = new Date(time + 5000);

        Session session = new Session("savedSession", date1, date2);
        session.addPerson(store.getModel().getPerson(randomGuy.getIndex()));
        session.addPerson(store.getModel().getPerson(oddJob.getIndex()));



        Performance runPerformance = new Performance(run, "primary", "OTS", new Date());
        runPerformance.setDirector(new Director(store.getModel().getPerson(oddJob.getIndex())));

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
    public void testSavePeople() throws JAXBException, SAXException, InvalidIDException {
        DataStore store = new DataStore(new DataModel());
        Person bob = store.getModel().addPerson("bob");
        Person ted = store.getModel().addPerson("ted");
        Person fred = store.getModel().addPerson("fred");
        store.save();

        DataStore store2 = new DataStore("file.xml");
        store2.load();
        assertEquals("bob", store.getModel().getPerson(bob.getIndex()).getName());
        assertEquals("ted", store.getModel().getPerson(ted.getIndex()).getName());
        assertEquals("fred", store.getModel().getPerson(fred.getIndex()).getName());
        boolean thrown = false;
        try {
            store.getModel().getPerson(3);
        }
        catch (Throwable t) {
            thrown = true;
        }
        assertTrue("Exception not thrown!!!", thrown);
        //assertEquals(3, store2.getModel().getPeople().size());
    }
}