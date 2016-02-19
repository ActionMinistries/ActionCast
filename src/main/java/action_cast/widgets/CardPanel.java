package action_cast.widgets;

import action_cast.view.BaseCardClass;
import action_cast.view.BreadCrumb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bmichaud on 9/30/2015.
 */
public class CardPanel extends JPanel implements ActionListener {

    private final BaseCardClass parent;
    JToolBar breadCrumbToolbar;

    BreadCrumb breadCrumb;

    boolean isProtected = false;

    List<Component> componentList = new ArrayList<>();
    List<Object> constraintsList = new ArrayList<>();

    public CardPanel(BaseCardClass parent) {
        this(parent, new BreadCrumb());
        breadCrumb.addComponent(this);
    }

    public CardPanel(BaseCardClass parent, BreadCrumb breadCrumb) {
        this.breadCrumb = breadCrumb;
        breadCrumb.addComponent(this);
        breadCrumbToolbar = new JToolBar();
        populateToolbar();

        this.add(breadCrumbToolbar, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
        this.parent = parent;
    }

    public void addCard(BaseCardClass card) {
        getParent().add(card.getMainPanel());
        ((CardLayout) getParent().getLayout()).addLayoutComponent(card.getName(), card.getMainPanel());
        ((CardLayout) getParent().getLayout()).next(getParent());
    }

    public boolean isProtected() {
        return isProtected;
    }

    public void setIsProtected(boolean isProtected) {
        this.isProtected = isProtected;
    }

    public BreadCrumb getBreadCrumb() {
        return breadCrumb;
    }

    public BaseCardClass getCardParent() {
        return parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //mainPanel.getParent().add(new CardTest(breadCrumbToolbar).mainPanel);
       /* if (e.getSource() == button1) {
            System.out.println("create card");
            Random random = new Random();
            String crumb = Integer.toString(random.nextInt());
            breadCrumb.addCrumb(crumb);

        } else if (e.getSource() == button2) {
            //do nothing
        } else {*/
        int selectedIndex;
        for (selectedIndex = breadCrumb.getCrumbs().size() - 1; selectedIndex >= 0 && !((JButton) e.getSource()).getText().equals(breadCrumb.getCrumbs().get(selectedIndex));  --selectedIndex) {

        }

        if (selectedIndex == 0) {
            ((CardLayout) getParent().getLayout()).first(getParent());
        } else {
            ((CardLayout) getParent().getLayout()).show(getParent(), ((JButton) e.getSource()).getText());
        }
        breadCrumb.getComponents().get(selectedIndex).getCardParent().onResume();

        for (int i = selectedIndex+1; i < breadCrumb.getComponents().size(); ++i) {
//            System.out.println("remove: " + breadCrumb.getCrumbs().get(i));
            (getParent()).remove(breadCrumb.getComponents().get(i));
        }
//        System.out.println("Components: " + breadCrumb.getComponents().size());
//        System.out.println("Before: " + breadCrumb.getCrumbs().size());

        breadCrumb.setCrumbs(breadCrumb.getCrumbs().subList(0, selectedIndex+1));
        breadCrumb.setComponents(breadCrumb.getComponents().subList(0, selectedIndex+1));

//        System.out.println("After: " + breadCrumb.getCrumbs().size());

        // }
    }

    @Override
    public void setLayout(LayoutManager mgr) {
        if (mgr instanceof com.intellij.uiDesigner.core.GridLayoutManager) {
            com.intellij.uiDesigner.core.GridLayoutManager layoutManager = (com.intellij.uiDesigner.core.GridLayoutManager) mgr;
            com.intellij.uiDesigner.core.GridLayoutManager newLayoutManager = new com.intellij.uiDesigner.core.GridLayoutManager(layoutManager.getRowCount() + 1, layoutManager.getColumnCount(), new Insets(0, 0, 0, 0), -1, -1);
            super.setLayout(newLayoutManager);
            return;

        }
        super.setLayout(mgr);

    /*    if (isProtected) {
            super.setLayout(mgr);
        }
        else {
            if (mgr instanceof com.intellij.uiDesigner.core.GridLayoutManager) {
                //com.intellij.uiDesigner.core.GridLayoutManager layoutManager = (com.intellij.uiDesigner.core.GridLayoutManager) mgr;
                //com.intellij.uiDesigner.core.GridLayoutManager newLayoutManager = new com.intellij.uiDesigner.core.GridLayoutManager(layoutManager.getRowCount() + 1, layoutManager.getColumnCount(), new Insets(0, 0, 0, 0), -1, -1);
                super.setLayout(newLayoutManager);

            }
            else {
                super.setLayout(mgr);
            }

        }*/
    }

    @Override
    public void add(Component comp, Object constraints) {
        /*if (this.getLayout() == null) {
            System.out.println("null layout manager");
        }
        else {
            System.out.println("not null layout");
        }*/
        if (!isProtected) {
            //super.add(comp, constraints);
            componentList.add(comp);
            constraintsList.add(constraints);
            return;
        }
        else if (constraints instanceof com.intellij.uiDesigner.core.GridConstraints) {
            for (int i = 0; i < componentList.size(); ++i) {
                super.add(componentList.get(i), constraintsList.get(i));

            }
            componentList.clear();
            constraintsList.clear();
            com.intellij.uiDesigner.core.GridConstraints gridConstraints = (com.intellij.uiDesigner.core.GridConstraints)constraints;
            //if (gridConstraints.getRow() == 0 && gridConstraints.getColumn() == 0) {
                //System.out.println("adding special item");
            com.intellij.uiDesigner.core.GridConstraints newGridConstraints = new com.intellij.uiDesigner.core.GridConstraints(gridConstraints.getRow()+1, gridConstraints.getColumn(),
                    gridConstraints.getRowSpan(), gridConstraints.getColSpan(),
                    gridConstraints.getAnchor(),
                    gridConstraints.getFill(),
                    gridConstraints.getHSizePolicy(),
                    gridConstraints.getVSizePolicy(),
                    gridConstraints.myMinimumSize, gridConstraints.myMaximumSize, gridConstraints.myPreferredSize, gridConstraints.getIndent(), gridConstraints.isUseParentLayout());
            super.add(comp, newGridConstraints);

            //}
            //else {
               // super.add(comp, constraints);

          //  }


        }
    }



    private void populateToolbar() {
        java.util.List<String> crumbs = breadCrumb.getCrumbs();
//        System.out.println("__________________________________");
        for (int i = 0; i < crumbs.size() - 1; ++i) { //String crumb : crumbs) {
//            System.out.println(crumbs.get(i));
            String crumb = crumbs.get(i);
            JButton newButton = new JButton(Integer.toString(this.breadCrumbToolbar.getComponentCount()));
            newButton.addActionListener(this);
            newButton.setText(crumb);
            breadCrumbToolbar.add(newButton);
        }
    }
}
