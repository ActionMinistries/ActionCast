package action_cast.widgets;

import action_cast.widgets.events.RowSelectedEvent;

import java.util.EventListener;

/**
 * Created by bmichaud on 9/15/2015.
 */
public interface RowSelectedListener extends EventListener {

    public void rowSelected(RowSelectedEvent e);

}
