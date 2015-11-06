package action_cast.controller;

import action_cast.model.DataModel;
import action_cast.model.Person;
import action_cast.model.Session;
import action_cast.widgets.PersonDisplayGrid;
import action_cast.widgets.PersonListView;
import action_cast.widgets.PersonTransferHandler;
import action_cast.widgets.custom.JTileView;
import action_cast.widgets.custom.PersonTile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddPeopleToSessionDialog2 extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private PersonListView personListView1;
    private JTileView JTileView1;

    private final DataModel model;
    private final Session session;


    public AddPeopleToSessionDialog2(DataModel model, Session session) {
        this.model = model;
        this.session = session;
        JTileView1.addTile(new PersonTile(new Person("boy")));
        //TODO replace the following line:
        personListView1.setData(model.getPeople());

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

    private void createUIComponents() {
        JTileView1 = new JTileView();
    }

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
