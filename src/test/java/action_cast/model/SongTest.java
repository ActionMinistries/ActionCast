package action_cast.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by bmichaud on 8/31/2015.
 */
public class SongTest {

    @Test
    public void testCreate() {
        String name = "songName";
        String description = "A well-formatted description of a song.";
        Song song = new Song(-1, name, description);
        assertNotNull(song);
        assertEquals("songName", song.getName());
        assertEquals("A well-formatted description of a song.", song.getDescription());
    }

    @Test
    public void testRoles() {
        String name = "songName";
        String description = "A well-formatted description of a song.";
        Song song = new Song(-1, name, description);
        List<Role> roles = new ArrayList<>();
        //roles.add(new Role("SomeGuy", "A random gentleman from Colorado.", RoleType.BACKGROUND));
        song.addRole("SomeGuy", "A random gentleman from Colorado.", RoleType.BACKGROUND, 0, 0, true);
        //song.setRoles(roles);
        assertNotNull(song.getRoles());
        assertEquals(1, song.getRoles().size());
    }
}