package action_cast.views;

import action_cast.model.Performance;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bmichaud on 9/2/2015.
 */
public class PerformanceTableView extends JTable {

    private List<Performance> performanceList = new ArrayList<>();

    public PerformanceTableView() {
        super(new DefaultTableModel(new Object[]{"Name", "Venue", "Song", "Date"}, 0));
    }

    public void setData(List<Performance> data) {
        performanceList = data;
        ((DefaultTableModel)getModel()).setRowCount(0);
        for (Performance performance : performanceList) {
            ((DefaultTableModel)getModel()).addRow(new Object[]{performance.getName(), performance.getVenue(), performance.getSong().getName(), performance.getDate()});
        }
    }

}
