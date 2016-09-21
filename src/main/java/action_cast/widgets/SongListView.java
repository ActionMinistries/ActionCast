package action_cast.widgets;

import action_cast.controller.ClientObjects.Song;
import action_cast.controller.Controller;
import action_cast.controller.events.SongsUpdateEvent;
import action_cast.widgets.dragdrop.SongTransferHandler;
import com.google.common.eventbus.Subscribe;

import javax.swing.*;

/**
 * Created by bmichaud on 1/25/2016.
 */
public class SongListView extends JList {

    private Controller controller;

    public SongListView() {
        super(new DefaultListModel());
        setTransferHandler(new SongTransferHandler());
        setDragEnabled(true);
        setDropMode(DropMode.INSERT);
    }

    public void setController(Controller controller) {
        this.controller = controller;
        this.controller.getEventBus().register(this);
        updateDisplay();
    }

    public void updateDisplay() {
        ((DefaultListModel) getModel()).clear();
            for (Song song : controller.getSongs()) {
                ((DefaultListModel) getModel()).addElement(song.getName());
            }
    }

    public Song getSelectedSong() {
        return controller.getSongs().get(getSelectedIndex());
    }

    public void addSong(Song song) {
        controller.getSongs().add(song);
        updateDisplay();
    }

    public void removeSong(Song song) {
        controller.getSongs().remove(song);
        updateDisplay();
    }

    @Subscribe
    public void handleSongCreation(SongsUpdateEvent event) {
        updateDisplay();
    }
}
