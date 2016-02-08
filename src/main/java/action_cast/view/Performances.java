package action_cast.view;

import action_cast.model.Session;
import action_cast.widgets.CardPanel;
import action_cast.widgets.SongTableView;
import action_cast.widgets.SessionSelector;

import javax.swing.*;

/**
 * Created by bmichaud on 9/10/2015.
 */
public class Performances extends BaseCardClass {
    private SongTableView songTableView1;
    private JButton button1;
    private SessionSelector sessionSelector1;
    private JButton editSessionButton;
    private JPanel panel1;

    public Performances() {
        super(new BreadCrumb());
    }

    public Performances(BreadCrumb breadCrumb) {
        super(breadCrumb);
    }

    public void setData(Session session) {
        //TODO
        //songTableView1.setData(session.getPerformances());
    }

    private void createUIComponents() {
        songTableView1 = new SongTableView(this);
    }

    @Override
    public CardPanel getMainPanel() {
        return (CardPanel) panel1;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    protected void updateDisplay() {

    }

}
