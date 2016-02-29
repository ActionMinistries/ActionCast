package action_cast.widgets;

import action_cast.controller.ClientObjects.Song;
import action_cast.controller.Controller;
import action_cast.model.exceptions.InvalidIDException;
import action_cast.view.EditSessionSong;
import action_cast.view.BaseCardClass;
import action_cast.widgets.dragdrop.SongTransferHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bmichaud on 9/2/2015.
 */
public class SongTableView extends DisplayTable implements MouseListener {

    private List<Song> songList = new ArrayList<>();
    private BaseCardClass card;
    private Controller controller;

    public SongTableView(BaseCardClass card) {
        super(new Object[]{"Songs", "Casting Status"});
        this.card = card;
        addMouseListener(this);
        setDragEnabled(true);
        setTransferHandler(new SongTransferHandler());
        setDropMode(DropMode.INSERT);
        setRowSelectionAllowed(false);
        this.setCellSelectionEnabled(true);
        this.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setFillsViewportHeight(true);
    }

    public void setData(Controller controller) {
        songList = controller.getSessionController().getSongs();
        this.controller = controller;
        updateDisplay();
    }

    public void addSong(Song song) {
        //songList.add(song);
        try {
            controller.assignSongToSession(song);
        } catch (InvalidIDException e) {
            e.printStackTrace();
        }
        updateDisplay();
    }

    public void removeSong(Song song) {
        try {
            controller.removeSongFromSession(song);
        } catch (InvalidIDException e) {
            e.printStackTrace();
        }
        updateDisplay();
    }

    public void updateDisplay() {
        ((DefaultTableModel)getModel()).setRowCount(0);
        songList = controller.getSessionController().getSongs();
        for (Song song : songList) {
            String castingStatus = null;
            try {
                castingStatus = (controller.isSongCast(song)) ? "✓" : "✗";
            } catch (InvalidIDException e) {
                e.printStackTrace();
            }
            ((DefaultTableModel)getModel()).addRow(new Object[]{song.getName(), castingStatus});
        }
    }

    public Song getSelectedSong() {
        return songList.get(getSelectedRow());
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent me) {
        JTable table = (JTable) me.getSource();
        Point p = me.getPoint();
        int row = table.rowAtPoint(p);
        if (me.getClickCount() == 2) {
            if (row < songList.size()) {
                EditSessionSong newEditSessionSong = new EditSessionSong(card.getBreadCrumb());
                newEditSessionSong.setData(controller, songList.get(row));
                card.addCard(newEditSessionSong);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
