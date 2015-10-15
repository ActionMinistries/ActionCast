package action_cast.widgets;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bmichaud on 10/15/2015.
 */
public class RoleDisplayGridTest {

    @Test
    public void testRound() {
        int test = (int)Math.round(Math.ceil(5.1));
        assertEquals(5, test);
    }

}