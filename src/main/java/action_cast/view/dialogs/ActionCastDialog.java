package action_cast.view.dialogs;

import action_cast.config.ApplicationConfiguration;
import action_cast.config.WindowConfiguration;
import action_cast.view.INamedWindow;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by bmichaud on 9/23/2016.
 */
public abstract class ActionCastDialog extends JDialog implements WindowListener, INamedWindow {

    public ActionCastDialog() {
        this.addWindowListener(this);
        setName(getClass().getName());
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        WindowConfiguration config = new WindowConfiguration(getName());
        config.setHeight(getHeight());
        config.setWidth(getWidth());
        ApplicationConfiguration.getInstance().addWindowConfiguration(config);
        System.out.println("saving: " + getName());
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    public void showDialog() {
        pack();
        WindowConfiguration winConfig = ApplicationConfiguration.getInstance().getWindowConfiguration(getName());
        if (winConfig != null) {
            setSize(winConfig.getWidth(), winConfig.getHeight());
        }
        setVisible(true);
    }
}
