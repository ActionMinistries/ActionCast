package action_cast.widgets;

import action_cast.controller.ClientObjects.Song;
import action_cast.controller.Controller;
import action_cast.view.EditSessionSong;
import action_cast.view.BaseCardClass;

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
    }

    public void setData(Controller controller, List<Song> data) {
        songList = data;
        this.controller = controller;
        ((DefaultTableModel)getModel()).setRowCount(0);
        for (Song song : songList) {
            ((DefaultTableModel)getModel()).addRow(new Object[]{song.getName(), "X"});
        }
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
