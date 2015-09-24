package action_cast.widgets;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bmichaud on 9/15/2015.
 */
public abstract class DisplayTable extends JTable {

    List<RowSelectedListener> rowSelectedListeners = new ArrayList<>();
    int selectedRow = -1;

    public DisplayTable(Object[] colNames) {
        super(new UneditableTableModel(colNames, 0));
        getSelectionModel().addListSelectionListener(this);
    }

    public DisplayTable(int numRows, int numCols) {
        super(new UneditableTableModel(numRows, numCols));
        getSelectionModel().addListSelectionListener(this);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        super.valueChanged(e);
        if (getSelectedRow() != selectedRow) {
            for (RowSelectedListener listener : rowSelectedListeners) {
                listener.rowSelected(new RowSelectedEvent(this, getSelectedRow()));
            }
            selectedRow = getSelectedRow();
        }
    }

    public void addRowSelectedListener(RowSelectedListener listener) {
        rowSelectedListeners.add(listener);
    }
}
