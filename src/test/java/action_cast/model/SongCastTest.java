package action_cast.model;

import action_cast.model.exceptions.InvalidIDException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bmichaud on 8/31/2015.
 */
public class SongCastTest {

    @Test
    public void testCreate() throws InvalidIDException {
        DataModel model = new DataModel();

        Song song = model.addSong("testSong", "with a test description");

        SongCast songCast = new SongCast(-1, song);
        assertNotNull(songCast);
        assertNotNull(songCast.getAssignmentMap());
        assertEquals(model.getSong(song.getIndex()), songCast.getSong());
    }

    @Test
    public void testAssignments() throws InvalidIDException {
        DataModel model = new DataModel();

        Song song = model.addSong("testSong", "with a test description");
        //List<Role> roles = new ArrayList<>();
        //roles.add(new Role("TestRole", "This is merely a test role", RoleType.SUPPORT));
        song.addRole("TestRole", "This is merely a test role", RoleType.SUPPORT, 1, 2, false);
        //model.getSong(song.getIndex()).setRoles(roles);

        SongCast songCast = new SongCast(-1, song);
        Person randomGuy = new Person(-1, "randomGuy", "", "");
        RoleAssignment assignment = songCast.assign(randomGuy, model.getSong(song.getIndex()).getRoles().get(0));
        assertTrue(songCast.getAssignmentMap().containsKey(assignment.getRole()));
        assertEquals(assignment.getRole(), model.getSong(song.getIndex()).getRoles().get(0));
        assertEquals(songCast.getAssignmentMap().get(assignment.getRole()).getPerson(), randomGuy);
    }
}