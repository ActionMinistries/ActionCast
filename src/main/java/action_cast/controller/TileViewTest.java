package action_cast.controller;

import action_cast.widgets.CardPanel;
import action_cast.widgets.custom.JTileView;

import javax.swing.*;

/**
 * Created by bmichaud on 10/29/2015.
 */
public class TileViewTest extends BaseCardClass {
    private JPanel mainPanel;
    private JTileView JTileView1;
    private JButton button1;

    public TileViewTest() {
        this(new BreadCrumb());
    }

    public TileViewTest(BreadCrumb breadCrumb) {
        super(breadCrumb);
    }

    @Override
    public CardPanel getMainPanel() {
        return (CardPanel)mainPanel;
    }

    @Override
    public String getName() {
        return "tileViewTest";
    }

    @Override
    protected void updateDisplay() {

    }

    private void createUIComponents() {
        if (breadCrumb == null) {
            mainPanel = new CardPanel(this);
        } else {
            mainPanel = new CardPanel(this, breadCrumb);
        }
        getMainPanel().setIsProtected(true);
        JTileView1 = new JTileView(0, 0);
        button1 = new JButton();
    }
}
