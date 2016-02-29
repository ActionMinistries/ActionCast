package action_cast.widgets.dragdrop;

import action_cast.controller.ClientObjects.Song;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * Created by bmichaud on 2/8/2016.
 */
public class SongTransferable implements Transferable{

    private final Song person;
    private final DataFlavor dataFlavor;

    public SongTransferable(Song person, DataFlavor dataFlavor) {
        this.person = person;
        this.dataFlavor = dataFlavor;
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
