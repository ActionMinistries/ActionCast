package action_cast.widgets.custom;

import java.awt.*;

/**
 * Created by bmichaud on 11/12/2015.
 */
public class TileLayoutManager implements LayoutManager2 {

    int tileWidth;
    int tileHeight;
    int tilePadding;

    public TileLayoutManager(int tileWidth, int tileHeight, int tilePadding) {
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.tilePadding = tilePadding;
    }

    @Override
    public void addLayoutComponent(String name, Component comp) {
       // System.out.println("addLayoutComponent(" +name + ", component)");
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        //System.out.println("removeLayoutComponent(" +comp + ")");

    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
       // System.out.println("preferredLayoutSize(" +parent + ")");
        return null;
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        //System.out.println("minimumLayoutSize(" +parent + ")");

        return null;
    }

    @Override
    public void layoutContainer(Container parent) {
        Insets insets = parent.getInsets();
//      Graphics2D g1 = (Graphics2D) g.create();
        int posx = insets.left;//parent.getX();
        int posy = insets.top;//parent.getY();

  //      Rectangle clipRect = new Rectangle();
    //    g1.getClipBounds(clipRect);

        for (Component tile :  parent.getComponents()) {
            if (posx + tileWidth + tilePadding > parent.getBounds().width - insets.right) {
                posx = insets.left;
                posy += tileHeight+tilePadding;
            }
            tile.setBounds(posx, posy, tileWidth, tileHeight);
            posx += tileHeight+tilePadding;
        }
    }

    @Override
    public void addLayoutComponent(Component comp, Object constraints) {
        //System.out.println("addLayoutComponent(" +comp +", " + constraints + ")");
    }

    @Override
    public Dimension maximumLayoutSize(Container target) {
       // System.out.println("maximumLayoutSize(" +target + ")");
        return null;
    }

    @Override
    public float getLayoutAlignmentX(Container target) {
       // System.out.println("getLayoutAlignmentX(" +target + ")");
        return 0;
    }

    @Override
    public float getLayoutAlignmentY(Container target) {
       //System.out.println("getLayoutAlignmentY(" +target + ")");
        return 0;
    }

    @Override
    public void invalidateLayout(Container target) {
        //System.out.println("invalidateLayout(" +target + ")");
    }
}
