package action_cast.view;

import action_cast.controller.ClientObjects.Song;
import action_cast.controller.Controller;
import action_cast.model.exceptions.InvalidIDException;
import action_cast.widgets.CardPanel;
import action_cast.widgets.CastingWidget;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by brian on 9/11/2015.
 */
public class EditSessionSong extends BaseCardClass implements ActionListener {
    private JPanel mainPanel;
    private JButton createButton;
    private CastingWidget castingWidget1;
   // private PersonListView personListView1;
   // private RoleAssignmentGrid roleAssignmentGrid1;

    private Controller controller;
    private Song song;
    //TODO
    // private List<Song> songs = new ArrayList<>();

    public EditSessionSong() {
        this(new BreadCrumb());
    }

    public EditSessionSong(BreadCrumb breadCrumb) {
        super(breadCrumb);
        createButton.addActionListener(this);
        //TODO?
        //songs.add(new Song(-1, "500 miles", "But I would walk 500 miles..."));
        //songSelector1.setData(songs);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {
            if (controller != null) {
                //TODO
                //if (currentPerformance == null) {
//                    try {
//                        //controller.assignSongToSession(controller.getSong(songSelector1.getSelectedIndex()));
//
//                    } catch (InvalidIDException e1) {
//                        e1.printStackTrace();
//                    }
            }
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setData(Controller controller, Song song) {
        this.controller = controller;
        this.song = song;
        updateDisplay();
    }

    public void updateDisplay() {
        castingWidget1.setData(controller, song);
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
