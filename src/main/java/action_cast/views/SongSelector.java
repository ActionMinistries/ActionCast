package action_cast.views;

import action_cast.model.Song;

import javax.swing.*;
import java.util.List;

/**
 * Created by brian on 9/11/2015.
 */
public class SongSelector extends JComboBox {

    public SongSelector() {
        super(new DefaultComboBoxModel());

    }

    public void setData(List<Song> data) {
        for (Song s : data) {
            ((DefaultComboBoxModel) getModel()).addElement(s.getName());
        }
    }
}
