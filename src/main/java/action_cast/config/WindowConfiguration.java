package action_cast.config;

import javax.xml.bind.annotation.XmlID;

/**
 * Created by bmichaud on 9/21/2016.
 */
public class WindowConfiguration {

    @XmlID
    private String id;
    private int width;
    private int height;

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
}