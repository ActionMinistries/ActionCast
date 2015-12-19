package action_cast.model;

import action_cast.model.exceptions.InvalidIDException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by bmichaud on 8/31/2015.
 */
public class PerformanceTest {

    @Test
    public void testCreate() throws InvalidIDException {
        DataModel model = new DataModel();

        String name = "perfName";
        String venue = "perfLocation";
        Song song = model.addSong("testSong", "with a test description");
        Long time = System.currentTimeMillis();

        Performance performance = new Performance(-1, song, name, venue, new Date(time));
        assertNotNull(performance);
        assertNotNull(performance.getAssignments());
        assertEquals("perfName", performance.getName());
        assertEquals("perfLocation", performance.getVenue());
        assertEquals(new Date(time), performance.getDate());
        assertEquals(model.getSong(song.getIndex()), performance.getSong());
    }

    @Test
    public void testAssignments() throws InvalidIDException {
        DataModel model = new DataModel();

        String name = "perfName";
        String venue = "perfLocation";
        Song song = model.addSong("testSong", "with a test description");
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("TestRole", "This is merely a test role", RoleType.SUPPORT));
        model.getSong(song.getIndex()).setRoles(roles);
        Long time = System.currentTimeMillis();

        Performance performance = new Performance(-1, song, name, venue, new Date(time));
        Person randomGuy = new Person(-1, "randomGuy");
        performance.assign(randomGuy, model.getSong(song.getIndex()).getRoles().get(0));
        assertTrue(performance.getAssignments().containsKey(randomGuy));
        assertEquals(performance.getAssignments().get(randomGuy), model.getSong(song.getIndex()).getRoles().get(0));
    }

    @Test
    public void testSetDirector() throws InvalidIDException {
        DataModel model = new DataModel();

        String name = "perfName";
        String venue = "perfLocation";
        Song song = model.addSong("testSong", "with a test description");
        Long time = System.currentTimeMillis();

        Performance performance = new Performance(-1, song, name, venue, new Date(time));

        performance.setDirector(new Director(new Person(-1, "someGuy")));
        assertEquals("someGuy", performance.getDirector().getName());
    }

}