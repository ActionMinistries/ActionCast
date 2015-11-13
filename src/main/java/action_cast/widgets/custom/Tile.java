package action_cast.widgets.custom;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by bmichaud on 10/29/2015.
 */
public abstract class Tile extends JPanel {

    private static final String uiClassID = "Tile";


    //public boolean isSelected = false;

    public Dimension getPreferredSize() {
        return new Dimension(JTileView.TILE_WIDTH, JTileView.TILE_HEIGHT);
    }
    public Dimension getMinimumSize() {
        return new Dimension(JTileView.TILE_WIDTH, JTileView.TILE_HEIGHT);

    }
    public Dimension getMaximumSize() {
        return new Dimension(JTileView.TILE_WIDTH, JTileView.TILE_HEIGHT);

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


    protected void paintComponent(Graphics g) {
//        if (isSelected) {
//            System.out.println("setting border...");
//           // setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.black, Color.gray));
//            setBorder(BorderFactory.createLineBorder(Color.black));
//        }
      //  paintBorder(g);
        super.paintComponent(g);
        Graphics2D g2 = ((Graphics2D)g);


        Ellipse2D ellipse = new Ellipse2D.Float(0, 0, 40, 40);
        g2.draw(ellipse);
        //g.drawRect(0, 0, TILE_WIDTH, TILE_HEIGHT);
    }

    public String getUIClassID() {
        return uiClassID;
    }

}
