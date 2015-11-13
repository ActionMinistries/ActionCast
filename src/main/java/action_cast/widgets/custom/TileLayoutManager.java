package action_cast.widgets.custom;

import java.awt.*;

/**
 * Created by bmichaud on 11/12/2015.
 */
public class TileLayoutManager implements LayoutManager {
    @Override
    public void addLayoutComponent(String name, Component comp) {
        System.out.println("addLayoutComponent(" +name + ", component");
    }

    @Override
    public void removeLayoutComponent(Component comp) {

    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return null;
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return null;
    }

    @Override
    public void layoutContainer(Container parent) {

    }
}
