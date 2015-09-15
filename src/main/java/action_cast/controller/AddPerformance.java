package action_cast.controller;

import action_cast.model.Performance;
import action_cast.model.Session;
import action_cast.model.Song;
import action_cast.widgets.SongSelector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by brian on 9/11/2015.
 */
public class AddPerformance implements ActionListener {
    private JTextField nameField;
    private JTextField venueField;
    private JTextField dateField;
    private JTable table1;
    private JTable table2;
    private JPanel mainPanel;
    private JButton createButton;
    private SongSelector songSelector1;

    private Session currentSession;
    private List<Song> songs = new ArrayList<>();

    public AddPerformance() {
        createButton.addActionListener(this);
        songs.add(new Song("500 miles", "But I would walk 500 miles..."));
        songSelector1.setData(songs);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {
            if (currentSession != null) {
                currentSession.addPerformance(new Performance(songs.get(songSelector1.getSelectedIndex()), nameField.getText(), venueField.getText(), new Date()));
                nameField.setText("");
                venueField.setText("");
                dateField.setText("");
                songSelector1.setSelectedIndex(0);
            }
        }
    }

    public void setData(Session session) {
        currentSession = session;
    }

}
