package action_cast.widgets.custom;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
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
        g1.setColor(Color.black);
        //g1.draw3DRect(0, 0, 3000, 3000, true);
      //  g1.setColor(Color.CYAN);
        int posx = 0;
        int posy = 0;

//        Rectangle clipRect = new Rectangle();
//        g1.getClipBounds(clipRect);
//        while(posy + TILE_HEIGHT <= clipRect.getY() + clipRect.getHeight()) {
//            while(posx + TILE_WIDTH <= clipRect.getX() + clipRect.getWidth()) {
//                //Rectangle rect = new Rectangle(posx, posy, TILE_WIDTH, TILE_HEIGHT);
//                Ellipse2D ellipse = new Ellipse2D.Float(posx, posy, 40, 40);
//                g1.draw(ellipse);
//                //g1.drawRect(0, 0, TILE_WIDTH, TILE_HEIGHT);
//                posx += TILE_WIDTH;
//            }
//            posx = 0;
//            posy += TILE_HEIGHT;
//        }
        for (Tile tile : tiles) {
            Graphics tileGraphics = g1.
        }
        g1.dispose();
    }

    public String getUIClassID() {
        return uiClassID;
    }

}
