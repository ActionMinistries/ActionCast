package action_cast.view;

import action_cast.controller.Controller;
import action_cast.model.DataModel;
import action_cast.model.Session;
import action_cast.widgets.PersonListView;
import action_cast.widgets.PersonTileView;

import javax.swing.*;
import java.awt.event.*;
import java.util.Date;

public class AddPeopleToSessionDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private PersonListView personListView1;
    private PersonTileView personTileView1;

    private final Controller controller;
    private final Session session;


    public AddPeopleToSessionDialog(Controller controller, Session session) {
        this.controller = controller;
        this.session = session;

        //TODO replace the following line:
        personListView1.setData(controller.getPeople());
        personTileView1.setData(session.getPeople());

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
        dispose();
    }

//    private void createUIComponents() {
//        JTileView1 = new JTileView();
//    }

    public static void main(String[] args) {
        DataModel model = new DataModel();
        model.setCurrentSession(new Session("dialogCheck", new Date(), new Date()));
        model.addPerson("me");
        model.addPerson("myself");
        model.addPerson("I");
        AddPeopleToSessionDialog2 dialog = new AddPeopleToSessionDialog2(model, model.getCurrentSession());
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
