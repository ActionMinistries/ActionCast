package action_cast.widgets.dragdrop;

import action_cast.controller.ClientObjects.Song;
import action_cast.widgets.SongListView;
import action_cast.widgets.SongTableView;

import javax.activation.DataHandler;
import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * Created by bmichaud on 2/8/2016.
 */
public class SongTransferHandler extends TransferHandler {

    private final DataFlavor songFlavor;


    public SongTransferHandler() {
        songFlavor = new DataFlavor(Song.class, "Person");
    }

    public int getSourceActions(JComponent c) {
        return MOVE;
    }

    protected Transferable createTransferable(JComponent c) {
        Song toMove = null;
        final DataHandler dh = new DataHandler(c, songFlavor.getMimeType());
        if (c instanceof SongListView) {
            toMove = ((SongListView)c).getSelectedSong();

        } else if (c instanceof SongTableView) {
            toMove = ((SongTableView)c).getSelectedSong();
        }
        if (toMove == null) {
            System.out.println("null");

            return dh;
        }
        else {
            return new SongTransferable(toMove, songFlavor);//new StringSelection(c.());
        }
    }

    public boolean canImport(TransferSupport support) {

        try {
            return support.getTransferable().getTransferData(songFlavor) != null;
        } catch (UnsupportedFlavorException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    protected void exportDone(JComponent c, Transferable t, int action) {
        if (action == MOVE) {
            if (c instanceof SongListView) {
                SongListView view = (SongListView) c;
                try {
                    view.removeSong((Song)t.getTransferData(songFlavor));
                } catch (UnsupportedFlavorException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (c instanceof SongTableView) {
                SongTableView view = (SongTableView) c;
                try {
                    view.removeSong((Song)t.getTransferData(songFlavor));
                } catch (UnsupportedFlavorException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean importData(TransferSupport support) {
        if(!canImport(support)) return false;
        if (!support.isDrop()) {
            return false;
        }
        if (support.getComponent() instanceof SongTableView) {
            SongTableView view = (SongTableView) support.getComponent();
            JTable.DropLocation dl = view.getDropLocation();
            int index = dl.getRow();
            try {
                view.addSong((Song) support.getTransferable().getTransferData(songFlavor));
                return true;
            } catch (UnsupportedFlavorException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (support.getComponent() instanceof SongListView) {
            SongListView view = (SongListView)support.getComponent();
            try {
                view.addSong((Song) support.getTransferable().getTransferData(songFlavor));
            } catch (UnsupportedFlavorException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
