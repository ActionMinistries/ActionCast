package action_cast.data_store;

import action_cast.model.DataModel;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by bmichaud on 10/6/2015.
 */
public class DataStoreTest {

    @Test
    public void testCreate() {
        DataStore store = new DataStore(new DataModel(null));
        assertNotNull(store);
    }

    @Test
    public void testSave() {
        DataStore store = new DataStore(new DataModel(null));
        store.save();
    }

    @Test
    public void testLoad() {

    }
}