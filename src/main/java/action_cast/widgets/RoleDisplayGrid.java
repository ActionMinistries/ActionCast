package action_cast.widgets;

import action_cast.model.Performer;
import action_cast.model.Role;

import java.util.Map;

/**
 * Created by bmichaud on 10/15/2015.
 */
public class RoleDisplayGrid extends DisplayTable {

    private Map<Performer, Role> assignments;

    public RoleDisplayGrid(Object[] colNames) {
        super(colNames);
    }

    public RoleDisplayGrid(int numRows, int numCols) {
        super(numRows, numCols);
    }

    public void setData(Map<Performer, Role> assignments) {
        this.assignments = assignments;
        updateDisplay();
    }

    public void updateDisplay() {
        int size = assignments.values().size();
        size =(int)Math.round(Math.ceil(Math.sqrt(size)));
        /*for (int row = 0; row < size/2; row++) {
            for (int col = 0; col < size/2; col++) {
                int index = getColumnCount() * row + col;
                if (index < assignments.values().size()) {
                    ((UneditableTableModel) getModel()).setValueAt(assignments.v, row, col);
                }
                else {
                    ((UneditableTableModel) getModel()).setValueAt("", row, col);
                }
            }
        }*/
    }

}
