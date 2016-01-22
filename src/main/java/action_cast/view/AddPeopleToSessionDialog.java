package action_cast.view;

import action_cast.controller.Controller;
import action_cast.model.DataModel;
import action_cast.model.Session;
import action_cast.model.exceptions.InvalidIDException;
import action_cast.widgets.PersonListView;
import action_cast.widgets.PersonTileView;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class AddPeopleToSessionDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private PersonListView personListView1;
    private PersonTileView personTileView1;

    private final Controller controller;

    public AddPeopleToSessionDialog(Controller controller) {
        this.controller = controller;

        //TODO replace the following line:
        personListView1.setData(controller.getPeopleNotInCurrentSession());
        personTileView1.setData(controller.getSessionController().getPeople());

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onOK();
            }
        });

// call onOK() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        try {
            controller.getSessionController().setPeople(personTileView1.getPeople());
        } catch (InvalidIDException e) {
            e.printStackTrace();
        }
        dispose();
    }

//    private void createUIComponents() {
//        JTileView1 = new JTileView();
//    }

    public static void main(String[] args) {
//        DataModel model = new DataModel();
//        model.setCurrentSession(new Session("dialogCheck", new Date(), new Date()));
//        model.addPerson("me");
//        model.addPerson("myself");
//        model.addPerson("I");
        AddPeopleToSessionDialog dialog = new AddPeopleToSessionDialog(null);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

}
