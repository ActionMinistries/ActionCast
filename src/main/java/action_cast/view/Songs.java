package action_cast.view;

import action_cast.controller.ClientObjects.Song;
import action_cast.controller.Controller;
import action_cast.model.exceptions.InvalidIDException;
import action_cast.widgets.RoleTileView;
import action_cast.widgets.SongListView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bmichaud on 1/25/2016.
 */
public class Songs implements ListSelectionListener, ActionListener {
    private SongListView songListView1;
    private JPanel mainPanel;
    private JTextField nameTextField;
    private JTextField descriptionTextField;
    private RoleTileView roleTileView1;
    private JButton saveButton;
    private JButton newButton;
    private Controller controller;

    public Songs() {
        songListView1.addListSelectionListener(this);
        saveButton.addActionListener(this);
        newButton.addActionListener(this);
    }

    public void setController(Controller controller) {
        this.controller = controller;
        songListView1.setData(controller.getSongs());
        updateDisplay();
    }

    public void updateDisplay() {
        try {
            if (!songListView1.isSelectionEmpty()) {
                nameTextField.setText(controller.getSong(songListView1.getSelectedIndex()).getName());
                descriptionTextField.setText(controller.getSong(songListView1.getSelectedIndex()).getDescription());
                roleTileView1.setData(controller.getSongRoles(songListView1.getSelectedIndex()));
            } else {
                clearDisplay();
            }
        } catch (InvalidIDException e) {
            e.printStackTrace();
        }
    }

    private void clearDisplay() {
        nameTextField.setText("");
        descriptionTextField.setText("");
        roleTileView1.setData(new ArrayList<>());
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (songListView1.isSelectionEmpty()) {
            clearDisplay();
        }
        updateDisplay();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            Song song = new Song(songListView1.getSelectedIndex(), nameTextField.getText(), descriptionTextField.getText());
            try {
                controller.updateSong(song);
                int selectedIndex = songListView1.getSelectedIndex();
                songListView1.setData(controller.getSongs());
                songListView1.setSelectedIndex(selectedIndex);
                updateDisplay();
            } catch (InvalidIDException e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == newButton) {
            controller.addSong("Untitled", "");
            List<Song> songs = controller.getSongs();
            songListView1.setData(songs);
            songListView1.setSelectedIndex(songs.size()-1);
            updateDisplay();
        }
    }
}
