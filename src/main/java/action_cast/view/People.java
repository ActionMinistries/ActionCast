package action_cast.view;

import action_cast.controller.Controller;
import action_cast.model.DataModel;
import action_cast.widgets.PersonListView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by bmichaud on 9/11/2015.
 */
public class People implements ActionListener {
    private JTextField textField1;
    private JButton addPersonButton;
    private JPanel mainPanel;
    private PersonListView personListView1;
    private Controller controller;


    public People() {
        addPersonButton.addActionListener(this);
    }

    public void setController(Controller controller) {
        this.controller = controller;
        //TODO replace the following line:
        personListView1.setData(controller.getPeople());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addPersonButton) {
            if (controller != null) {
                controller.addPerson(textField1.getText());
                personListView1.updateDisplay();
                textField1.setText("");
            }
        }
    }
}
