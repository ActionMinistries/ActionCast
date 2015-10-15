package action_cast.controller;

import action_cast.widgets.ButtonRow;
import action_cast.widgets.TableButton;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.*;

public class ButtonRowTestDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable table1;

    public ButtonRowTestDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        if (table1 != null) {
            String[] columnNames = {"First Name", "Last Name", ""};/*
            Object[][] data =
                    {
                            {"Homer", "Simpson", "delete Homer"},
                            {"Madge", "Simpson", "delete Madge"},
                            {"Bart",  "Simpson", "delete Bart"},
                            {"Lisa",  "Simpson", "delete Lisa"},
                    };
*/
            DefaultTableModel model = new DefaultTableModel(columnNames, 2);
            table1.setModel(model);

            TableButton button = new TableButton("blah");
            model.setValueAt(button, 0, 0);
            TableColumn col = new TableColumn(1, 80);
            col.setCellRenderer(button);
            //col.setCellEditor(button);
            table1.addColumn(col);

            //ButtonRow buttonColumn = new ButtonRow(table1, 2);
        }
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
        ButtonRowTestDialog dialog = new ButtonRowTestDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
