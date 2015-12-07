package action_cast.widgets;

import action_cast.model.Person;
import action_cast.model.modelinterface.PersonView;
import action_cast.widgets.custom.JTileView;
import action_cast.widgets.custom.PersonTile;

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
        personFlavor = new DataFlavor(PersonView.class, "PersonView");
    }

    public int getSourceActions(JComponent c) {
        return MOVE;
    }

    protected Transferable createTransferable(JComponent c) {
        PersonView toMove = null;
        final DataHandler dh = new DataHandler(c, personFlavor.getMimeType());
        if (c instanceof PersonListView) {
            toMove = ((PersonListView)c).getSelectedPerson();

        }
        else if (c instanceof PersonDisplayGrid) {
            toMove = ((PersonDisplayGrid)c).getSelectedPerson();
        } else if (c instanceof PersonTile)
            toMove = ((PersonTile)((PersonTile)c)).getPerson();
        if (toMove == null) {
            System.out.println("null");

            return dh;
        }
        else {
            return new PersonTransferable(toMove, personFlavor);//new StringSelection(c.());
        }
    }

    protected void exportDone(JComponent c, Transferable t, int action) {
        if (action == MOVE) {
            if (c instanceof PersonListView) {
                PersonListView view = (PersonListView) c;
                view.removeSelectedPerson();
            } else if (c instanceof PersonDisplayGrid) {
                PersonDisplayGrid view = (PersonDisplayGrid) c;
                view.removeSelectedPerson();
            } else if (c instanceof  PersonTile) {
                PersonTile view = (PersonTile)c;
                ((PersonTile) c).removeTile();
            }

        }
    }

    public boolean canImport(TransferSupport support) {

        try {
            return support.getTransferable().getTransferData(personFlavor) != null;
        } catch (UnsupportedFlavorException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean importData(TransferSupport support) {
        if(!canImport(support)) return false;
        if (!support.isDrop()) {
            return false;
        }
        if (support.getComponent() instanceof PersonDisplayGrid) {
            try {
                PersonDisplayGrid grid = ((PersonDisplayGrid)support.getComponent());
                grid.addPerson((Person) support.getTransferable().getTransferData(personFlavor), grid.getDropLocation().getRow(), grid.getDropLocation().getColumn());
                return true;
            } catch (UnsupportedFlavorException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (support.getComponent() instanceof PersonListView) {
            PersonListView view = (PersonListView) support.getComponent();
            JList.DropLocation dl = (JList.DropLocation) view.getDropLocation();
            int index = dl.getIndex();
            try {
                view.addPerson(index, (Person) support.getTransferable().getTransferData(personFlavor));
                return true;
            } catch (UnsupportedFlavorException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (support.getComponent() instanceof JTileView){
            JTileView tileView = (JTileView) support.getComponent();
            try {
                tileView.add(new PersonTile(tileView, (Person) support.getTransferable().getTransferData(personFlavor)));
                return true;
            } catch (UnsupportedFlavorException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
