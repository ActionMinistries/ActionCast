package action_cast.widgets.tiles;

import action_cast.controller.ClientObjects.Song;
import action_cast.controller.Controller;
import action_cast.widgets.custom.JTileView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by bmichaud on 2/3/2016.
 */
public class RoleCreationTile extends CreationTile  {

    private final Controller controller;
    private final Song song;

    public RoleCreationTile(JTileView parent, Controller controller, Song song) {
        super(parent);
        this.controller = controller;
        this.song = song;
        addMouseListener(this);
    }

    @Override
    protected TransferHandler getDefaultTransferHandler() {
        return null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).drawString("New...", (JTileView.TILE_WIDTH/2) - (int)(g.getFontMetrics().getStringBounds("New...", null).getWidth()/2), JTileView.TILE_HEIGHT/2);
    }
}
