package action_cast.controller;

import action_cast.model.Session;
import org.jdatepicker.impl.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.Date;
import java.util.Properties;

public class CreateSessionDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JDatePanelImpl startDatePanel;
    private JDatePanelImpl endDatePanel;
    private JDatePickerImpl startDate;
    private JDatePickerImpl endDate;

    private Session createdSession = null;

    public CreateSessionDialog() {
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

    public Session showDialog() {
        pack();
        setVisible(true);
        return createdSession;
    }

    private void onOK() {
// add your code here
        Date start = (Date)startDate.getModel().getValue();
        Date end = (Date)endDate.getModel().getValue();
        if (start != null && end != null) {
            createdSession = new Session(start, end);
            dispose();
        }
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        CreateSessionDialog dialog = new CreateSessionDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void createUIComponents() {

    }
}
