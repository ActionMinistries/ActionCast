package action_cast.widgets;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by bmichaud on 10/15/2015.
 */
public class TableButton extends JButton implements TableCellRenderer {

    private int selectedRow;
    private int selectedColumn;

    public TableButton(String caption) {
        super(caption);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
            selectedRow = row;
            selectedColumn = col;
            return this;
    }
}

