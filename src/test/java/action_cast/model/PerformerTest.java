package action_cast.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by bmichaud on 9/1/2015.
 */
public class PerformerTest {

    @Test
    public void testCreate() {
        String name = "randomGuy";

        Person randomGuy = new Person(name);
        Performer performer = new Performer(randomGuy);
        assertEquals("randomGuy", performer.getName());
    }

}