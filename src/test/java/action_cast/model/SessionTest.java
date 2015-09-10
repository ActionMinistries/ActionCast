package action_cast.model;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
        Session s = new Session(start, end);
        assertNotNull(s);

        Date expectedStart = new Date(startTime);
        Date expectedEnd = new Date(endTime);

        assertEquals(expectedStart, s.getStartDate());
        assertEquals(expectedEnd, s.getEndDate());
    }

    @Test
    public void testPerformanceList() {
        Date start = new Date();
        Date end = new Date();

        Session s = new Session(start, end);

       // List<Performance> performances = new ArrayList<>();
        assertEquals(0, s.getPerformances().size());
        s.addPerformance(new Performance(new Song("Cool song", "Na na, nanana!"), "The main event!", "Wouldn't you like to know?", new Date()));
        assertEquals(1, s.getPerformances().size());

        s.addPerformance(new Performance(new Song("Whatever.", "Naaaaa! :'( "), "Emo time!", "it doesn't matter anyway", new Date()));
        //s.setPerformanceList(performances);

        assertEquals(2, s.getPerformances().size());
    }

    @Test
    public void testPerformerList() {
        Date start = new Date();
        Date end = new Date();

        Session s = new Session(start, end);

        //List<Performer> performers = new ArrayList<>();
        assertEquals(0, s.getPerformers().size());
        s.addPerformer(new Performer(new Person("SomeGuy")));
        assertEquals(1, s.getPerformers().size());

    }
}