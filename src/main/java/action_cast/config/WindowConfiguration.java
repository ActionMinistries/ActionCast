package action_cast.config;

import action_cast.view.INamedWindow;

import javax.xml.bind.annotation.XmlID;
import java.awt.*;

/**
 * Created by bmichaud on 9/21/2016.
 */
public class WindowConfiguration {

    @XmlID
    private String id;

    private int width, height;
    private int x, y;

    private WindowConfiguration() {

    }

    public WindowConfiguration(String windowName) {
        id = windowName;
    }

    public String getKey() {
        return id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static void restoreWindowConfiguration(Window window, String name) {
        WindowConfiguration mainConfig = ApplicationConfiguration.getInstance().getWindowConfiguration(name);
        if (mainConfig != null) {
            window.setSize(mainConfig.getWidth(), mainConfig.getHeight());
            window.setLocation(mainConfig.getX(), mainConfig.getY());
            if (!getVirtualBounds().contains(window.getBounds())) {
               window.setLocation(0, 0);
            }
        }
    }

    public static void saveWindowConfiguration(Window window, String name) {
        WindowConfiguration config = new WindowConfiguration(name);
        config.setHeight(window.getHeight());
        config.setWidth(window.getWidth());
        config.setX(window.getX());
        config.setY(window.getY());
        ApplicationConfiguration.getInstance().addWindowConfiguration(config);
    }

    private static Rectangle getVirtualBounds() {
        Rectangle bounds = new Rectangle(0, 0, 0, 0);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice lstGDs[] = ge.getScreenDevices();
        for (GraphicsDevice gd : lstGDs) {
            bounds.add(gd.getDefaultConfiguration().getBounds());
        }
        return bounds;
    }
}
