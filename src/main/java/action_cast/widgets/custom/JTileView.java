package action_cast.widgets.custom;

import action_cast.widgets.PersonTransferHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bmichaud on 10/20/2015.
 */
public class JTileView extends JPanel {

    private static final String uiClassID = "TileView";

    public static final int TILE_WIDTH = 64;
    public static final int TILE_HEIGHT = 64;
    public static final int PADDING = 2;
   // private final int rows;
   // private final int columns;

    private final List<Tile> tiles = new ArrayList<>();

    public JTileView() {
        super();
        setTransferHandler(new PersonTransferHandler());
        setBorder(BorderFactory.createLineBorder(Color.black, 2));
        setLayout(new TileLayoutManager(TILE_WIDTH, TILE_HEIGHT, PADDING));
    }

  //  public JTileView(int rows, int cols) {
   //     this.rows = rows;
   //     this.columns = cols;
  //  }

    public void addTile(Tile tile) {
        if (tiles.isEmpty()) {
            System.out.println("setting border");
            tile.setBorder(BorderFactory.createLineBorder(Color.black,2));
        }

        this.add(tile);
        tiles.add(tile);
        repaint();
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

    public Dimension getPreferredSize() {
        return new Dimension((TILE_WIDTH+PADDING)*2, (PADDING+TILE_HEIGHT)*(tiles.size()/2));
    }

    public Dimension getMinimumSize() {
        return new Dimension((TILE_WIDTH+PADDING)*2, (PADDING+TILE_HEIGHT)*(tiles.size()/2));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
/*        Graphics2D g1 = (Graphics2D) g.create();
        int posx = 0;
        int posy = 0;

        Rectangle clipRect = new Rectangle();
        g1.getClipBounds(clipRect);
        for (Tile tile : tiles) {
            if (posx + TILE_WIDTH > clipRect.getX() + clipRect.getWidth()) {
                posx = 0;
                posy += TILE_HEIGHT+PADDING;
            }
            Graphics tileGraphics = g1.create(posx+PADDING, posy+PADDING, TILE_WIDTH+PADDING, TILE_HEIGHT+PADDING);
            tile.paintComponent(tileGraphics);
            posx += TILE_WIDTH+PADDING;
        }*/
    }

    public String getUIClassID() {
        return uiClassID;
    }

    public Tile getSelectedTile() {
        return tiles.get(0);
    }

}
