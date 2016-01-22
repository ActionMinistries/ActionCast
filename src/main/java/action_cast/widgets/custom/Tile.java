package action_cast.widgets.custom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

/**
 * Created by bmichaud on 10/29/2015.
 */
public abstract class Tile extends JPanel implements MouseMotionListener {

    private static final String uiClassID = "Tile";

    private JTileView parent;

    public Tile(JTileView parent) {
        this.parent = parent;
        this.addMouseMotionListener(this);
        this.setTransferHandler(getDefaultTransferHandler());
        setBorder((BorderFactory.createRaisedBevelBorder()));
        setBackground(UIManager.getColor("Panel.background"));

    }

    protected abstract TransferHandler getDefaultTransferHandler();

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
        super.paintComponent(g);
        Graphics2D g2 = ((Graphics2D)g);

        Ellipse2D ellipse = new Ellipse2D.Float(getInsets().left, getInsets().top, JTileView.TILE_WIDTH -(10 + getInsets().right), JTileView.TILE_HEIGHT - (10 + getInsets().bottom));
        g2.draw(ellipse);
    }

    public String getUIClassID() {
        return uiClassID;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        JComponent c = (JComponent) e.getSource();
        TransferHandler handler = c.getTransferHandler();
        handler.exportAsDrag(c, e, TransferHandler.MOVE);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public void removeTile() {
        parent.remove(this);
    }

}
