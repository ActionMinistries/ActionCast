package action_cast.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by bmichaud on 8/31/2015.
 */
public class RoleTest {

    @Test
    public void testCreate() {
        String name = "partName";
        String description = "partDescription";
        RoleType type = RoleType.MAIN;

        Role role = new Role(name, description, type);
        assertNotNull(role);
        assertEquals("partName", role.getName());
        assertEquals(RoleType.MAIN, role.getType());
        assertEquals("partDescription", role.getDescription());
    }

}