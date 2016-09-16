package action_cast.data_store;

import action_cast.model.*;
import action_cast.model.exceptions.InvalidIDException;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.util.Date;

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
        dataStore.loadWithValidation();
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
        store2.loadWithValidation();
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
        assertEquals("29358", dataModel.getPerson(0).getPhoneNumber());
        assertEquals("whatever@...", dataModel.getPerson(1).getEmail());

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
        assertEquals(dataModel.getPerson(currentSession.getPeople().get(0).getIndex()).getName(), currentSession.getPeople().get(0).getName());
        assertEquals(dataModel.getPerson(currentSession.getPeople().get(1).getIndex()).getName(), currentSession.getPeople().get(1).getName());

        assertEquals(2, currentSession.getSongs().size());
        //assertEquals(1, currentSession.getPerformers().size());
        //assertEquals("random guy", currentSession.getPerformers().get(0).getName());
    }

    @Test
    public void testLoad_DataCorrelation() throws InvalidIDException {
        DataModel dataModel = dataStore.getModel();
        Session currentSession = dataModel.getCurrentSession();
        assertTrue(currentSession.hasPerson(dataModel.getPerson(0)));
        assertTrue(currentSession.hasPerson(dataModel.getPerson(1)));
    }

    @Test
    public void testLoad_DataCorrelation_Songs() throws InvalidIDException {
        DataModel dataModel = dataStore.getModel();
        Session currentSession = dataModel.getCurrentSession();
        assertTrue(dataModel.getSongs().contains(currentSession.getSongs().get(1)));
        assertTrue(dataModel.getSongs().contains(currentSession.getSongs().get(0)));
    }


    @Test
    public void testSaveSession_extended() throws JAXBException, SAXException, InvalidIDException {
        DataStore store = new DataStore(new DataModel());

        Person randomGuy = store.getModel().addPerson("random guy", "29358", "");
        Person oddJob = store.getModel().addPerson("odd job", "", "whatever@...");
        store.getModel().addPerson("ted", "", "");
        store.getModel().addPerson("fred", "", "");

       // Song fiveHundredMiles = new Song();
        //Song run = new Song("I just wanna run.", "throw it away");

        Song fiveHundredMiles = store.getModel().addSong("500 miles", "500 more");
        Song run = store.getModel().addSong("I just wanna run.", "throw it away");
        //List<Role> roles = new ArrayList<>();
        //roles.add(new Role("Runner", "The guy who runs", RoleType.MAIN));
        run.addRole("Runner", "The guy who runs", RoleType.MAIN);
        //store.getModel().getSong(run.getIndex()).setRoles(roles);
       // store.getModel().addSong(fiveHundredMiles);


        long time = System.currentTimeMillis();
        Date date1 = new Date(time);
        Date date2 = new Date(time + 5000);

        Session session = new Session("savedSession", date1, date2);
        session.addPerson(store.getModel().getPerson(randomGuy.getIndex()));
        session.addPerson(store.getModel().getPerson(oddJob.getIndex()));


        session.addSong(fiveHundredMiles);

        session.addSong(run);
        session.getSongCast(run).assign(randomGuy, run.getRole(0));
        //fail();
//        runSongCast.assign(randomGuy, run.getRoles().get(0));
//
//
        store.getModel().setCurrentSession(session);
        store.save();
        DataStore store2 = new DataStore("file.xml");
        store2.loadWithValidation();
        assertEquals("savedSession", store2.getModel().getCurrentSession().getName());
        assertEquals(date1, store2.getModel().getCurrentSession().getStartDate());
        assertEquals(date2, store2.getModel().getCurrentSession().getEndDate());
        assertEquals(session.getPeople().size(), store2.getModel().getCurrentSession().getPeople().size());
        assertEquals(session.getSongs().size(), store2.getModel().getCurrentSession().getSongs().size());
        assertEquals(store2.getModel().getPerson(randomGuy.getIndex()).getPhoneNumber(), "29358");
    }

    @Test
    public void testSavePeople() throws JAXBException, SAXException, InvalidIDException {
        DataStore store = new DataStore(new DataModel());
        Person bob = store.getModel().addPerson("bob", "","");
        Person ted = store.getModel().addPerson("ted","","");
        Person fred = store.getModel().addPerson("fred","","");
        store.save();

        DataStore store2 = new DataStore("file.xml");
        store2.loadWithValidation();
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