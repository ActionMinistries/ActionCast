package action_cast.widgets.custom;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bmichaud on 10/20/2015.
 */
public class JTileView extends JComponent {

    private static final String uiClassID = "TileView";

    private static final int TILE_WIDTH = 64;
    private static final int TILE_HEIGHT = 64;
    private final int rows;
    private final int columns;

    private final List<Tile> tiles = new ArrayList<>();

    public JTileView(int rows, int cols) {
        this.rows = rows;
        this.columns = cols;
    }

    public void addTile(Tile tile) {
        tiles.add(tile);
    }

//    public void setUI(JTileViewUI ui) {
//        super.setUI(ui);
//    }
//
//    public void updateUI() {
//        if (UIManager.get(getUIClassID()) != null) {
//            setUI((JTileViewUI) UIManager.getUI(this));
//        } else {
//            setUI(new BasicJTileViewUI());
//        }
//    }
//
//    public JTileViewUI getUI() {
//        return (JTileViewUI) ui;
//    }

    public Dimension getPreferredSize() {
        return new Dimension(TILE_WIDTH, TILE_HEIGHT);
    }

    public Dimension getMinimumSize() {
        return new Dimension(TILE_WIDTH, TILE_HEIGHT);
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g1 = (Graphics2D) g.create();
        int posx = 0;
        int posy = 0;

        Rectangle clipRect = new Rectangle();
        g1.getClipBounds(clipRect);
        for (Tile tile : tiles) {
            if (posx + TILE_WIDTH > clipRect.getX() + clipRect.getWidth()) {
                posx = 0;
                posy += TILE_HEIGHT;
            }
            Graphics tileGraphics = g1.create(posx, posy, TILE_WIDTH, TILE_HEIGHT);
            tile.paintComponent(tileGraphics);
            posx += TILE_WIDTH;
        }
        g1.dispose();
    }

    public String getUIClassID() {
        return uiClassID;
    }

}
