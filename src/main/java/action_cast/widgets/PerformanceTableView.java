package action_cast.widgets;

import action_cast.controller.ClientObjects.Performance;
import action_cast.controller.ClientObjects.Session;
import action_cast.controller.SessionController;
import action_cast.view.AddPerformance;
import action_cast.view.BaseCardClass;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bmichaud on 9/2/2015.
 */
public class PerformanceTableView extends DisplayTable implements MouseListener {

    private List<Performance> performanceList = new ArrayList<>();
    private BaseCardClass card;
    private SessionController sessionController;

    public PerformanceTableView(BaseCardClass card) {
        super(new Object[]{"Name", "Venue", "Song", "Date"});
        this.card = card;
        addMouseListener(this);
    }

    public void setData(SessionController sessionController, List<Performance> data) {
        performanceList = data;
        this.sessionController = sessionController;
        ((DefaultTableModel)getModel()).setRowCount(0);
        for (Performance performance : performanceList) {
            ((DefaultTableModel)getModel()).addRow(new Object[]{performance.getName(), performance.getVenue(), performance.getSong().getName(), performance.getDate()});
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent me) {
        JTable table = (JTable) me.getSource();
        Point p = me.getPoint();
        int row = table.rowAtPoint(p);
        if (me.getClickCount() == 2) {
            if (row < performanceList.size()) {
                AddPerformance newAddPerformance = new AddPerformance(card.getBreadCrumb());
                newAddPerformance.setData(sessionController, performanceList.get(row));
                card.addCard(newAddPerformance);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
