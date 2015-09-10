package action_cast.views;

import action_cast.model.Session;

import javax.swing.*;
import java.util.List;

/**
 * Created by bmichaud on 9/10/2015.
 */
public class SessionSelector extends JComboBox {

    public SessionSelector() {
        super(new DefaultComboBoxModel());

    }

    public void setData(List<Session> data) {
        for (Session s : data) {
            ((DefaultComboBoxModel) getModel()).addElement(s.getStartDate().toString() + "-" +  s.getEndDate().toString());
        }
    }
}
