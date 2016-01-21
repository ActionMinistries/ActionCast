package action_cast.view;

import action_cast.controller.ClientObjects.Session;
import action_cast.controller.Controller;
import action_cast.widgets.CardPanel;
import action_cast.widgets.PerformanceTableView;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

/**
 * Created by bmichaud on 9/15/2015.
 */
public class ManageSessions extends BaseCardClass implements ActionListener {
    private JPanel mainPanel;
    private JButton saveButton;
    private JButton assignPeopleButton;
    private JDatePickerImpl startDate;
    private JDatePickerImpl endDate;
    private JTextField nameTextField;
    private PerformanceTableView performanceTableView1;

    private Controller controller;

    public ManageSessions() {
        this(new BreadCrumb());
    }

    public ManageSessions(BreadCrumb breadCrumb) {
        super(breadCrumb);
        saveButton.addActionListener(this);
        assignPeopleButton.addActionListener(this);
    }

    @Override
    public CardPanel getMainPanel() {
        return (CardPanel) mainPanel;
    }

    @Override
    public String getName() {
        //TODO
//        if (DataModel.instance == null) {
//            return "Current Session";
//        }
        return "Current Session";
    }

    public void setController(Controller controller) {
        this.controller = controller;
        updateDisplay();
    }

//    @Override
//    public void rowSelected(RowSelectedEvent e) {
//        if (e.getSource() == sessionTableView1) {
//            saveButton.setEnabled(true);
//            assignPeopleButton.setEnabled(true);
//        }
//    }

    protected void updateDisplay() {
        // List<Session> sessionList = new ArrayList<>();
        //sessionList.add(controller.getCurrentSession());
        ((UtilDateModel) startDate.getModel()).setValue(controller.getCurrentSession().getStartDate());
        ((UtilDateModel) endDate.getModel()).setValue(controller.getCurrentSession().getEndDate());
        nameTextField.setText(controller.getCurrentSession().getName());
        performanceTableView1.setData(controller, controller.getSessionController().getPerformances());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            Session currentSession = controller.getCurrentSession();
            currentSession.setName(nameTextField.getText());
            currentSession.setStartDate(((UtilDateModel) startDate.getModel()).getValue());
            currentSession.setEndDate(((UtilDateModel) startDate.getModel()).getValue());
            controller.updateCurrentSession(currentSession);
        } else if (e.getSource() == assignPeopleButton) {
            AddPeopleToSessionDialog peopleToSessionDialog = new AddPeopleToSessionDialog(controller);
            peopleToSessionDialog.pack();
            peopleToSessionDialog.setVisible(true);
        }
//        else if(e.getSource() == newButton) {
//            CreateSessionDialog sessionDialog = new CreateSessionDialog();
//            Session newSession = sessionDialog.showDialog();
//            if (newSession != null) {
//              //  model.addSession(newSession);
//            }
//            //sessionTableView1.setData(model.getSessions());
//        }
    }

    private void createUIComponents() {
        if (breadCrumb == null) {
            mainPanel = new CardPanel(this);
        } else {
            mainPanel = new CardPanel(this, breadCrumb);
        }
        getMainPanel().setIsProtected(true);
        JDatePanelImpl startDatePanel = new JDatePanelImpl(new UtilDateModel(), new Properties());
        JDatePanelImpl endDatePanel = new JDatePanelImpl(new UtilDateModel(), new Properties());
        startDate = new JDatePickerImpl(startDatePanel, new DateComponentFormatter());
        endDate = new JDatePickerImpl(endDatePanel, new DateComponentFormatter());
        performanceTableView1 = new PerformanceTableView(this);
    }

}
