package action_cast.view;

import action_cast.controller.ClientObjects.Song;
import action_cast.controller.Controller;
import action_cast.widgets.CardPanel;
import action_cast.widgets.CastingWidget;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;

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
        setupUI();
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

    private void setupUI() {
        createUIComponents();
        mainPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        castingWidget1 = new CastingWidget();
        panel1.add(castingWidget1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent getRootComponent() {
        return mainPanel;
    }

}
