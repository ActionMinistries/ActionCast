package action_cast.view.dialogs;

import action_cast.controller.ClientObjects.Song;
import action_cast.controller.Controller;
import action_cast.model.RoleType;
import action_cast.model.exceptions.InvalidIDException;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class RoleCreationDialog extends ActionCastDialog implements ActionListener {
    private final Controller controller;
    private final Song song;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nameField;
    private JTextField descriptionField;
    private JComboBox roleTypeSelector;

    private JTextField minAssignmentsField;
    private JTextField maxAssignmentsField;
    private JCheckBox isOptionalField;

    public RoleCreationDialog(Controller controller, Song song) {
        setupUI();
        this.controller = controller;
        this.song = song;

        roleTypeSelector.addItem(RoleType.MAIN);
        roleTypeSelector.addItem(RoleType.SUPPORT);
        roleTypeSelector.addItem(RoleType.BACKGROUND);

        roleTypeSelector.addActionListener(this);
        updateRoleTypeFields();
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
            RoleType roleType = (RoleType)roleTypeSelector.getSelectedItem();
            switch (roleType) {
                case MAIN:
                    controller.createMainRole(song.getId(), nameField.getText(), descriptionField.getText());
                    break;
                case SUPPORT:
                    controller.createSupportRole(song.getId(), nameField.getText(), descriptionField.getText(), Integer.parseInt(minAssignmentsField.getText()), Integer.parseInt(minAssignmentsField.getText()));
                    break;
                case BACKGROUND:
                    controller.createBackgroundRole(song.getId(), nameField.getText(), descriptionField.getText(), Integer.parseInt(minAssignmentsField.getText()), isOptionalField.isSelected());
                    break;
            }
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
//        RoleCreationDialog dialog = new RoleCreationDialog();
//        dialog.showDialog();
//        System.exit(0);
    }

    private void setupUI() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(4, 2, new Insets(10, 10, 10, 10), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(buttonPanel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonCancel = new JButton();
        buttonCancel.setText("Cancel");
        buttonPanel.add(buttonCancel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonOK = new JButton();
        buttonOK.setText("OK");
        buttonPanel.add(buttonOK, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        //fieldPanel
        final JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(fieldPanel, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        //name
        final JLabel label1 = new JLabel();
        label1.setText("Name");
        fieldPanel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameField = new JTextField();
        fieldPanel.add(nameField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        //description
        final JLabel label2 = new JLabel();
        label2.setText("Description");
        fieldPanel.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        descriptionField = new JTextField();
        fieldPanel.add(descriptionField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        //Rolepanel
        final JPanel rolePanel = new JPanel();
        rolePanel.setLayout(new GridLayoutManager(1, 2, new Insets(0,0,0,0), -1, -1));
        contentPane.add(rolePanel, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        //Role
        final JLabel label3 = new JLabel();
        label3.setText("Role Type");
        rolePanel.add(label3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        roleTypeSelector = new JComboBox();
        rolePanel.add(roleTypeSelector, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        //RoleTypeFieldsPanel
        final JPanel roleTypeFieldsPanel = new JPanel();
        roleTypeFieldsPanel.setLayout(new GridLayoutManager(3, 2, new Insets(0,0,0,0), -1, -1));
        contentPane.add(roleTypeFieldsPanel, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        //minAssignments
        final JLabel minAssignLabel = new JLabel();
        minAssignLabel.setText("Min Assignments");
        roleTypeFieldsPanel.add(minAssignLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        minAssignmentsField = new JTextField();
        roleTypeFieldsPanel.add(minAssignmentsField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        //maxAssignments
        final JLabel maxAssignLabel = new JLabel();
        maxAssignLabel.setText("Max Assignments");
        roleTypeFieldsPanel.add(maxAssignLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        maxAssignmentsField = new JTextField();
        roleTypeFieldsPanel.add(maxAssignmentsField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        //isOptional
        final JLabel isOptionalLabel = new JLabel();
        isOptionalLabel.setText("Optional?");
        roleTypeFieldsPanel.add(isOptionalLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        isOptionalField = new JCheckBox();
        roleTypeFieldsPanel.add(isOptionalField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        //Final spacer
        final Spacer spacer2 = new Spacer();
        contentPane.add(spacer2, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));

    }

    /**
     * @noinspection ALL
     */
    public JComponent getRootComponent() {
        return contentPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateRoleTypeFields();
    }

    private void updateRoleTypeFields() {
        RoleType roleType = (RoleType)roleTypeSelector.getSelectedItem();
        switch (roleType) {
            case MAIN:
                minAssignmentsField.setText("1");
                maxAssignmentsField.setText("1");
                isOptionalField.setSelected(false);
                minAssignmentsField.setEnabled(false);
                maxAssignmentsField.setEnabled(false);
                isOptionalField.setEnabled(false);
                break;
            case SUPPORT:
                isOptionalField.setSelected(false);
                minAssignmentsField.setEnabled(true);
                maxAssignmentsField.setEnabled(true);
                isOptionalField.setEnabled(false);
                break;
            case BACKGROUND:
                maxAssignmentsField.setText("0");
                minAssignmentsField.setEnabled(true);
                maxAssignmentsField.setEnabled(false);
                isOptionalField.setEnabled(true);
                break;
        }
    }
}
