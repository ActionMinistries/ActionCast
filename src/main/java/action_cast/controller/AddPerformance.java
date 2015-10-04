package action_cast.controller;

import action_cast.model.DataModel;
import action_cast.model.Performance;
import action_cast.model.Session;
import action_cast.model.Song;
import action_cast.widgets.CardPanel;
import action_cast.widgets.PersonListView;
import action_cast.widgets.SongSelector;

import javax.smartcardio.Card;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by brian on 9/11/2015.
 */
public class AddPerformance extends BaseCardClass implements ActionListener {
    private JTextField nameField;
    private JTextField venueField;
    private JTextField dateField;
    private JTable table2;
    private JPanel mainPanel;
    private JButton createButton;
    private SongSelector songSelector1;
    private PersonListView personListView1;

    private Session currentSession;
    private Performance currentPerformance;
    private List<Song> songs = new ArrayList<>();

    public AddPerformance() {
        this(new BreadCrumb());
    }

    public AddPerformance(BreadCrumb breadCrumb) {
        super(breadCrumb);
        createButton.addActionListener(this);
        songs.add(new Song("500 miles", "But I would walk 500 miles..."));
        songSelector1.setData(songs);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {
            if (currentSession != null) {
                if (currentPerformance == null) {
                    currentSession.addPerformance(new Performance(songs.get(songSelector1.getSelectedIndex()), nameField.getText(), venueField.getText(), new Date()));
                    nameField.setText("");
                    venueField.setText("");
                    dateField.setText("");
                    songSelector1.setSelectedIndex(0);
                }
                else {
                    currentPerformance.setName(nameField.getText());
                    currentPerformance.setVenue(venueField.getText());
                    currentPerformance.setSong(songs.get(songSelector1.getSelectedIndex()));
                }
            }
        }
    }

    public void setData(Session session) {
        currentSession = session;
    }

    public void setData(Session session, Performance performance) {
        currentSession = session;
        currentPerformance = performance;
        updateDisplay();
    }

    public void updateDisplay() {
        nameField.setText(currentPerformance.getName());
        venueField.setText(currentPerformance.getVenue());
        dateField.setText(currentPerformance.getDate().toString());
        songSelector1.setSelectedIndex(0);
    }


        @Override
    public CardPanel getMainPanel() {
        return (CardPanel) mainPanel;
    }

    @Override
    public String getName() {
        return currentPerformance == null ? "Add performance" : currentPerformance.getName();
    }

    private void createUIComponents() {
        if (breadCrumb == null) {
            mainPanel = new CardPanel();
        } else {
            mainPanel = new CardPanel(breadCrumb);
        }
        getMainPanel().setIsProtected(true);
    }

}
