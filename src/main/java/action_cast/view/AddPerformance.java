package action_cast.view;

import action_cast.controller.ClientObjects.Performance;
import action_cast.controller.SessionController;
import action_cast.widgets.CardPanel;
import action_cast.widgets.PersonListView;
import action_cast.widgets.PersonTileView;
import action_cast.widgets.SongSelector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by brian on 9/11/2015.
 */
public class AddPerformance extends BaseCardClass implements ActionListener {
    private JTextField nameField;
    private JTextField venueField;
    private JTextField dateField;
    private JPanel mainPanel;
    private JButton createButton;
    private SongSelector songSelector1;
    private PersonListView personListView1;
    private PersonTileView personTileView1;

    private SessionController sessionController;
    private Performance currentPerformance;

    //TODO
    // private List<Song> songs = new ArrayList<>();

    public AddPerformance() {
        this(new BreadCrumb());
    }

    public AddPerformance(BreadCrumb breadCrumb) {
        super(breadCrumb);
        createButton.addActionListener(this);
        //TODO?
        //songs.add(new Song(-1, "500 miles", "But I would walk 500 miles..."));
        //songSelector1.setData(songs);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {
            if (sessionController != null) {
                //TODO
                if (currentPerformance == null) {
                    /*try {
                        sessionController.addPerformance(new Performance(songSelector1.getSelectedIndex(), nameField.getText(), venueField.getText(), new Date()));
                    } catch (InvalidIDException e1) {
                        e1.printStackTrace();
                    }
                    nameField.setText("");
                    venueField.setText("");
                    dateField.setText("");
                    songSelector1.setSelectedIndex(0);*/
                }
                else {
                 /*   currentPerformance.setName(nameField.getText());
                    currentPerformance.setVenue(venueField.getText());
                    currentPerformance.setSong(songs.get(songSelector1.getSelectedIndex()));*/
                }
            }
        }
    }

    public void setController(SessionController session) {
        sessionController = session;
    }

    public void setData(SessionController session, Performance performance) {
        sessionController = session;
        currentPerformance = performance;
        updateDisplay();
    }

    public void updateDisplay() {
        nameField.setText(currentPerformance.getName());
        venueField.setText(currentPerformance.getVenue());
        dateField.setText(currentPerformance.getDate().toString());
        songSelector1.setSelectedIndex(0);
        personListView1.setData(sessionController.getPeople());
    }


        @Override
    public CardPanel getMainPanel() {
        return (CardPanel) mainPanel;
    }

    @Override
    public String getName() {
        //TODO
       // return currentPerformance == null ? "Add performance" : currentPerformance.getName();
        return "Add performance";
    }

    private void createUIComponents() {
        if (breadCrumb == null) {
            mainPanel = new CardPanel(this);
        } else {
            mainPanel = new CardPanel(this, breadCrumb);
        }
        getMainPanel().setIsProtected(true);
    }

}
