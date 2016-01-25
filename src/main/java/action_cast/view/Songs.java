package action_cast.view;

import action_cast.controller.Controller;
import action_cast.widgets.SongListView;

import javax.swing.*;

/**
 * Created by bmichaud on 1/25/2016.
 */
public class Songs {
    private SongListView songListView1;
    private JPanel mainPanel;

    public void setController(Controller controller) {
        songListView1.setData(controller.getSongs());
    }
}
