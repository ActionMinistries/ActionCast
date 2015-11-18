package action_cast.controller;

import action_cast.widgets.CardPanel;
import action_cast.widgets.custom.JTileView;
import action_cast.widgets.custom.TextTile;
import action_cast.widgets.custom.Tile;

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
        JTileView1 = new JTileView();
        button1 = new JButton();
        JTileView1.add(new TextTile(JTileView1, "one"));
        JTileView1.add(new TextTile(JTileView1, "two"));
        JTileView1.add(new TextTile(JTileView1, "three"));
        JTileView1.add(new TextTile(JTileView1, "four"));
        JTileView1.add(new TextTile(JTileView1, "five"));
    }
}
