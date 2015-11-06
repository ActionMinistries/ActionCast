package action_cast.widgets.custom;

import java.awt.*;

/**
 * Created by bmichaud on 11/4/2015.
 */
public class TextTile extends Tile {

    private final String text;

    public TextTile(String text) {
        this.text = text;
    }

    protected void paintComponent(Graphics g) {
        ((Graphics2D)g).drawString(text, 5, 20);
        super.paintComponent(g.create());
        g.dispose();
    }
}
