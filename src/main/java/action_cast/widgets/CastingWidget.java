package action_cast.widgets;

import action_cast.controller.ClientObjects.Performance;
import action_cast.controller.ClientObjects.RoleAssignment;
import action_cast.controller.Controller;
import action_cast.model.exceptions.InvalidIDException;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by bmichaud on 1/7/2016
 */
public class CastingWidget extends JPanel{

    private final PersonListView personListView1;
    private final RoleAssignmentGrid roleAssignmentGrid1;
    private Controller controller;
    private Performance performance;

    public CastingWidget() {
        setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        personListView1 = new PersonListView();
        add(personListView1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(150, -1), null, null, 0, false));
        roleAssignmentGrid1 = new RoleAssignmentGrid();
        add(roleAssignmentGrid1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    }

    public void setData(Controller controller, Performance performance) {
        this.controller = controller;
        this.performance = performance;
        updateDisplay();
    }

//    public List<RoleAssignment> getAssignments() {
//        return roleAssignmentGrid1.getTiles().stream().map(tile -> new RoleAssignment(tile.getAssignedPerson().getId(), tile.getRole().getId())).collect(Collectors.toList());
//    }

    public void updateDisplay() {
        personListView1.setData(controller.getSessionController().getPeople());
        try {
            roleAssignmentGrid1.setData(controller, performance, controller.getSongRoles(performance.getSong().getId()), controller.getSessionController().getPerformanceAssignments(performance));//controller.getSessionController().getPerformanceAssignments(performance));
        } catch (InvalidIDException e) {
            e.printStackTrace();
        }
    }
}
