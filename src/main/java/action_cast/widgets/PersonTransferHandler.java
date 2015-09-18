package action_cast.widgets;

import action_cast.model.Person;

import javax.activation.DataHandler;
import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * Created by bmichaud on 9/17/2015.
 */
public class PersonTransferHandler extends TransferHandler {

    private final DataFlavor personFlavor;

    public PersonTransferHandler() {
        //personFlavor = new ActivationDataFlavor();
        personFlavor = new DataFlavor(Person.class, "Person");
        //personFlavor = new DataFlavor(PersonDisplayGrid.class, );
    }

    public int getSourceActions(JComponent c) {
        return MOVE;
    }

    protected Transferable createTransferableComponent(JComponent c) {
        Person toMove = null;
        final DataHandler dh = new DataHandler(c, personFlavor.getMimeType());
        if (c instanceof PersonListView) {
            toMove = ((PersonListView)c).getSelectedPerson();

        }
        if (toMove == null) {
            System.out.println("null");

            return dh;
        }
        else {
            System.out.println("created transferable");
            return new PersonTransferable(toMove, personFlavor);//new StringSelection(c.());
        }
    }

    protected void exportDone(JComponent c, Transferable t, int action) {
        if (c instanceof PersonListView) {

//            Person toMove = ((PersonListView)c).getSelectedPerson();
//            final DataHandler dh = new DataHandler(c, personFlavor.getMimeType());
//            if (toMove == null) return dh;
        }
    }

    public boolean canImport(TransferSupport support) {
        return true;
    }

    public boolean importData(TransferSupport support) {
        if(!canImport(support)) return false;
        if (!support.isDrop()) {
            return false;
        }
        if (support.getComponent() instanceof PersonDisplayGrid) {
            try {
                ((PersonDisplayGrid)support.getComponent()).addPerson((Person)support.getTransferable().getTransferData(personFlavor), 0,0);
            } catch (UnsupportedFlavorException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (support.getComponent() instanceof PersonListView) {
            PersonListView view = (PersonListView)support.getComponent();
            DefaultListModel listModel = (DefaultListModel)view.getModel();
            JList.DropLocation dl = (JList.DropLocation)view.getDropLocation();
            int index = dl.getIndex();
            boolean insert = dl.isInsert();
        }
        return false;
    }
}
