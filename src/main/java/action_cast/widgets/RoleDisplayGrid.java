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
    }

}
