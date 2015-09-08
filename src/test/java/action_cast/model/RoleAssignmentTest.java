package action_cast.model;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by bmichaud on 9/1/2015.
 */
public class RoleAssignmentTest {

    @Test
    public void testCreate() {
        Performer randomGuy = new Performer(new Person("randomGuy"));
        Role role = new Role("TestRole", "no real description", RoleType.BACKGROUND);
        Performance performance = new Performance(new Song("Terrible action_cast.model.Song", "nobody likes it"), "performance has a name?", "no and where", new Date());
        RoleAssignment assignment = new RoleAssignment(randomGuy, role, performance);
        assertNotNull(assignment);

        assertEquals("randomGuy", assignment.getPerformer().getName());
        assertEquals("TestRole", assignment.getRole().getName());
        assertEquals("performance has a name?", assignment.getPerformance().getName());
    }

}