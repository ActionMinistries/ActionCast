package action_cast.widgets;

import action_cast.controller.Data.Person;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * Created by bmichaud on 9/17/2015.
 */
public class PersonTransferable implements Transferable {

    private final Person person;
    private final DataFlavor dataFlavor;

    public PersonTransferable(Person person, DataFlavor flavor) {
        this.person = person;
        this.dataFlavor = flavor;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        DataFlavor[] flavors = new DataFlavor[1];
        flavors[0] = dataFlavor;
        return flavors;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(dataFlavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (dataFlavor.equals(flavor)) {
            return person;
        }
        return null;
    }
}
