package action_cast.view;

import action_cast.controller.ClientObjects.Performance;
import action_cast.controller.Controller;
import action_cast.model.exceptions.InvalidIDException;
import action_cast.widgets.*;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

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
    private CastingWidget castingWidget1;
   // private PersonListView personListView1;
   // private RoleAssignmentGrid roleAssignmentGrid1;

    private Controller controller;
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
            if (controller != null) {
                //TODO
                if (currentPerformance == null) {
                    try {
                        controller.getSessionController().addPerformance(controller.getSong(songSelector1.getSelectedIndex()), nameField.getText(), venueField.getText(), new Date());
                    } catch (InvalidIDException e1) {
                        e1.printStackTrace();
                    }
                    nameField.setText("");
                    venueField.setText("");
                    dateField.setText("");
                    songSelector1.setSelectedIndex(0);
                } else {
                    currentPerformance.setName(nameField.getText());
                    currentPerformance.setVenue(venueField.getText());
                    currentPerformance.setSong(controller.getSongs().get(songSelector1.getSelectedIndex()));
                    try {
                        controller.getSessionController().updatePerformance(currentPerformance);
                    } catch (InvalidIDException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setData(Controller controller, Performance performance) {
        this.controller = controller;
        currentPerformance = performance;
        updateDisplay();
    }

    public void updateDisplay() {
        nameField.setText(currentPerformance.getName());
        venueField.setText(currentPerformance.getVenue());
        dateField.setText(currentPerformance.getDate().toString());
        songSelector1.setData(controller.getSongs());
        songSelector1.setSelectedIndex(0);
        try {
            castingWidget1.setData(controller, controller.getSong(songSelector1.getSelectedIndex()));
        } catch (InvalidIDException e) {
            e.printStackTrace();
        }
        //  personListView1.setData(controller.getSessionController().getPeople());
     //   try {
        //    roleAssignmentGrid1.setData(controller.getRoles(currentPerformance.getSong().getId()));
      //  } catch (InvalidIDException e) {
      //      e.printStackTrace();
       // }
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
