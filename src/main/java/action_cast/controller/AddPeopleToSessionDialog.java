package action_cast.controller;

import action_cast.model.DataModel;
import action_cast.model.Person;
import action_cast.model.Session;
import action_cast.widgets.PersonDisplayGrid;
import action_cast.widgets.PersonListView;
import action_cast.widgets.PersonTransferHandler;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddPeopleToSessionDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private PersonListView personListView1;
    private PersonDisplayGrid personDisplayGrid1;

    private final DataModel model;
    private final Session session;


    public AddPeopleToSessionDialog(DataModel model, Session session) {
        this.model = model;
        this.session = session;

        personListView1.setData(model.getPeople());
        personDisplayGrid1.setDragEnabled(true);

        List<Person> people = new ArrayList<>();
        people.add(new Person("one"));
        people.add(new Person("two"));
        people.add(new Person("three"));
        people.add(new Person("four"));
        personDisplayGrid1.setData(people);

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
// add your code here
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        DataModel model = new DataModel();
        model.addPerson(new Person("me"));
        model.addPerson(new Person("myself"));
        model.addPerson(new Person("I"));
        AddPeopleToSessionDialog dialog = new AddPeopleToSessionDialog(model, new Session(new Date(), new Date()));
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
