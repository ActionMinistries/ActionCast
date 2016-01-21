package action_cast.widgets.custom;

import javax.swing.*;
import java.awt.*;

/**
 * Created by bmichaud on 11/4/2015.
 */
public class TextTile extends Tile {

    private final String text;

    public TextTile(JTileView parent, String text) {
        super(parent);
        this.text = text;
    }

    @Override
    protected TransferHandler getDefaultTransferHandler() {
        return null;
    }

    protected void paintComponent(Graphics g) {
        ((Graphics2D)g).drawString(text, 5, 20);
        super.paintComponent(g.create());
        g.dispose();
    }
}
