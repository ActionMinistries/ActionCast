package action_cast.widgets;

import action_cast.model.Session;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bmichaud on 9/15/2015.
 */
public class SessionTableView extends DisplayTable {

    private List<Session> sessionList = new ArrayList<>();

    public SessionTableView() {
        super(new Object[]{"Name", "Start", "End"});
    }


    public void setData(List<Session> data) {
        sessionList = data;
        ((DefaultTableModel)getModel()).setRowCount(0);
        for (Session session : sessionList) {
            ((DefaultTableModel)getModel()).addRow(new Object[]{"session names NYI!", session.getStartDate(), session.getEndDate()});
        }
    }
}
