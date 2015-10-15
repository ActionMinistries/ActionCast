package action_cast.controller;

import action_cast.widgets.CardPanel;

import java.util.Random;

/**
 * Created by brian on 9/28/2015.
 */
public abstract class BaseCardClass {

    protected String name;
    protected BreadCrumb breadCrumb = null;

    public BaseCardClass(BreadCrumb breadCrumb) {
        //Random random = new Random(System.currentTimeMillis());
        //name = Integer.toString(random.nextInt());
        this.breadCrumb = breadCrumb;
        breadCrumb.addCrumb(getName());
//        System.out.println("new name" + name);
    }

    public void addCard(BaseCardClass card) {
        getMainPanel().addCard(card);
    }

    public abstract CardPanel getMainPanel();

    public abstract String getName();

    public void onResume() {
        updateDisplay();
    }

    protected abstract void updateDisplay();

    public BreadCrumb getBreadCrumb() {
        return breadCrumb;
    }
}
