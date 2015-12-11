package action_cast.model;

import action_cast.model.exceptions.InvalidIDException;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by bmichaud on 9/10/2015.
 */
public class SessionTest {

    @Test
    public void testCreate() throws InterruptedException {
        Long startTime = System.currentTimeMillis();
        Date start = new Date(startTime);
        Thread.sleep(5);
        Long endTime = System.currentTimeMillis();
        Date end = new Date(endTime);
        Session s = new Session("sessionName", start, end);
        assertNotNull(s);

        Date expectedStart = new Date(startTime);
        Date expectedEnd = new Date(endTime);

        assertEquals(expectedStart, s.getStartDate());
        assertEquals(expectedEnd, s.getEndDate());
        assertEquals("sessionName", s.getName());
    }

    @Test
    public void testPerformanceList() throws InvalidIDException {
        Date start = new Date();
        Date end = new Date();

        Session s = new Session("sessionName", start, end);

       // List<Performance> performances = new ArrayList<>();
        DataModel model = new DataModel();
        Song first = model.addSong("Cool song", "Na na, nanana!");
        Song second = model.addSong("Whatever.", "Naaaaa! :'( ");
        assertEquals(0, s.getPerformances().size());
        s.addPerformance(first, "The main event!", "Wouldn't you like to know?", new Date());
        assertEquals(1, s.getPerformances().size());

        s.addPerformance(second, "Emo time!", "it doesn't matter anyway", new Date());
        //s.setPerformanceList(performances);

        assertEquals(2, s.getPerformances().size());
    }

    @Test
    public void testPerformerList() {
        Date start = new Date();
        Date end = new Date();

        Session s = new Session("theSession", start, end);

        //List<Performer> performers = new ArrayList<>();
        assertEquals(0, s.getPerformers().size());
        s.addPerformer(new Performer(new Person(-1, "SomeGuy")));
        assertEquals(1, s.getPerformers().size());

    }

    @Test
    public void testPeopleList() throws InvalidIDException {
        Date start = new Date();
        Date end = new Date();

        Session s = new Session("someName", start, end);

        //List<Performer> performers = new ArrayList<>();
        DataModel model = new DataModel();
        Person person = model.addPerson("SomeGuy");
        assertEquals(0, s.getPeople().size());
        s.addPerson(model.getPerson(person.getIndex()));
        assertEquals(1, s.getPeople().size());

    }

    @Ignore
    @Test
    public void testPeopleList_NonExistentPerson()  {
        Date start = new Date();
        Date end = new Date();

        Session s = new Session("someName", start, end);

        //List<Performer> performers = new ArrayList<>();

        assertEquals(0, s.getPeople().size());
        boolean thrown = false;
        s.addPerson(new Person(-1, "guy"));

        assertTrue(thrown);
        assertEquals(0, s.getPeople().size());

    }
}