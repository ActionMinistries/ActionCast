package action_cast.widgets.events;

import java.util.EventObject;

/**
 * Created by bmichaud on 9/15/2015.
 */
public class RowSelectedEvent extends EventObject {

    private final int rowNumber;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public RowSelectedEvent(Object source, int rowNumber) {
        super(source);
        this.rowNumber = rowNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }

}
