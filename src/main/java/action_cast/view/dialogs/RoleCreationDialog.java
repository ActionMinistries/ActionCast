package action_cast.view.dialogs;

import action_cast.controller.ClientObjects.Song;
import action_cast.controller.Controller;
import action_cast.model.RoleType;
import action_cast.model.exceptions.InvalidIDException;

import javax.swing.*;
import java.awt.event.*;

public class RoleCreationDialog extends JDialog {
    private final Controller controller;
    private final Song song;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nameField;
    private JTextField descriptionField;
    private JComboBox roleTypeSelector;

    public RoleCreationDialog(Controller controller, Song song) {
        this.controller = controller;
        this.song = song;

        roleTypeSelector.addItem(RoleType.MAIN);
        roleTypeSelector.addItem(RoleType.SUPPORT);
        roleTypeSelector.addItem(RoleType.BACKGROUND);

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
        try {
            controller.createRole(song.getId(), nameField.getText(), descriptionField.getText(), (RoleType) roleTypeSelector.getSelectedItem());
            dispose();
        } catch (InvalidIDException e) {
            e.printStackTrace();
        }
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        /*RoleCreationDialog dialog = new RoleCreationDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);*/
    }
}
