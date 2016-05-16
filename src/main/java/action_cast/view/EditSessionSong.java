package action_cast.view;

import action_cast.controller.ClientObjects.Song;
import action_cast.controller.Controller;
import action_cast.widgets.CardPanel;
import action_cast.widgets.CastingWidget;

import javax.swing.*;

/**
 * Created by brian on 9/11/2015.
 */
public class EditSessionSong extends BaseCardClass {
    private JPanel mainPanel;
    private CastingWidget castingWidget1;

    private Controller controller;
    private Song song;

    public EditSessionSong() {
        this(new BreadCrumb());
    }

    public EditSessionSong(BreadCrumb breadCrumb) {
        super(breadCrumb);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setData(Controller controller, Song song) {
        this.controller = controller;
        this.song = song;
        updateDisplay();
    }

    public void updateDisplay() {
        castingWidget1.setData(controller, song);
    }


    @Override
    public CardPanel getMainPanel() {
        return (CardPanel) mainPanel;
    }

    @Override
    public String getName() {
        return "Add performance";
    }

    private void createUIComponents() {
        if (breadCrumb == null) {
            mainPanel = new CardPanel(this);
        } else {
            mainPanel = new CardPanel(this, breadCrumb);
        }
        getMainPanel().setIsProtected(true);
    }

}
