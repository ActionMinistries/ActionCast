package action_cast.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by bmichaud on 9/1/2015.
 */
public class PersonTest {

    @Test
    public void testCreate() {
        String name = "randomGuy";

        Person randomGuy = new Person(-1, name);
        assertEquals("randomGuy", randomGuy.getName());
    }

}