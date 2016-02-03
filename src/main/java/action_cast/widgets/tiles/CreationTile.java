package action_cast.widgets.tiles;

import action_cast.widgets.custom.JTileView;
import action_cast.widgets.custom.Tile;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by bmichaud on 2/3/2016.
 */
public abstract class CreationTile extends Tile implements MouseListener {

    public CreationTile(JTileView parent) {
        super(parent);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        getTileView().createTile();
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
