package action_cast.widgets.custom;

import action_cast.widgets.dragdrop.PersonTransferHandler;
import action_cast.widgets.tiles.CreationTile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bmichaud on 10/20/2015.
 */
public abstract class JTileView<T extends Tile> extends JPanel {

    private static final String uiClassID = "TileView";

    public static final int TILE_WIDTH = 128;
    public static final int TILE_HEIGHT = 128;
    public static final int PADDING = 4;
    private CreationTile creationTile;

    public JTileView() {
        super();
        setTransferHandler(new PersonTransferHandler());
        setBorder(BorderFactory.createLineBorder(Color.black, 2));
        setLayout(new TileLayoutManager(TILE_WIDTH, TILE_HEIGHT, PADDING));
        setBackground(Color.darkGray);
    }

    public void setUI(JTileViewUI ui) {
        super.setUI(ui);
    }

    public void updateUI() {
        if (UIManager.get(getUIClassID()) != null) {
            setUI((JTileViewUI) UIManager.getUI(this));
        } else {
            setUI(new JTileViewUI());
        }
    }

    public JTileViewUI getUI() {
        return (JTileViewUI) ui;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension((TILE_WIDTH+PADDING)*2, (PADDING+TILE_HEIGHT)*(getComponents().length/2));
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension((TILE_WIDTH+PADDING)*2, (PADDING+TILE_HEIGHT)*(getComponents().length/2));
    }

    public String getUIClassID() {
        return uiClassID;
    }

    @Override
    public void remove(Component comp) {
        super.remove(comp);
        repaint();
    }

    public T getTileAt(Point p) {
        return (T)getComponentAt(p);
    }

    public List<T> getTiles() {
        List<T> tileList = new ArrayList<>();
        for (Component component : getComponents()) {
            tileList.add((T)component);
        }
        return tileList;
    }

    public void setCreationTile(CreationTile tile) {
        this.creationTile = tile;
    }

    public CreationTile getCreationTile() {
        return creationTile;
    }

    public abstract void createTile();
}
