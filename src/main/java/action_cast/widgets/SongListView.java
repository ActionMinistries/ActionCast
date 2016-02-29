package action_cast.widgets;

import action_cast.controller.ClientObjects.Song;
import action_cast.widgets.dragdrop.SongTransferHandler;

import javax.swing.*;
import java.util.List;

/**
 * Created by bmichaud on 1/25/2016.
 */
public class SongListView extends JList {

    List<Song> songs;

    public SongListView() {
        super(new DefaultListModel());
        setTransferHandler(new SongTransferHandler());
        setDragEnabled(true);
        setDropMode(DropMode.INSERT);
    }

    public void setData(List<Song> songs ) {
        this.songs = songs;
        updateDisplay();
    }

    public void updateDisplay() {
        ((DefaultListModel) getModel()).clear();
        if (songs != null) {
            for (Song song : songs) {
                ((DefaultListModel) getModel()).addElement(song.getName());
            }
        }
    }

    public Song getSelectedSong() {
        return songs.get(getSelectedIndex());
    }

    public void addSong(Song song) {
        songs.add(song);
        updateDisplay();
    }

    public void removeSong(Song song) {
        songs.remove(song);
        updateDisplay();
    }

}
