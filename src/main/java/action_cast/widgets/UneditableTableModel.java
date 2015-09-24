package action_cast.widgets;

import javax.swing.table.DefaultTableModel;

/**
 * Created by bmichaud on 9/15/2015.
 */
class UneditableTableModel extends DefaultTableModel {

    public UneditableTableModel(Object[] colNames, int rowCount) {
        super(colNames, rowCount);
    }

    public UneditableTableModel(int numRows, int numCols) {
        super(numRows, numCols );
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }
}
