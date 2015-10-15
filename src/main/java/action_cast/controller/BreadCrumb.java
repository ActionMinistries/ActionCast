package action_cast.controller;

import action_cast.widgets.CardPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brian on 9/26/2015.
 */
public class BreadCrumb {

    private List<String> crumbs = new ArrayList<>();
    private List<CardPanel> components = new ArrayList<>();

    public void addCrumb(String crumb) {
        crumbs.add(crumb);
    }

    public void addComponent(CardPanel comp) {
        components.add(comp);
    }

    public List<String> getCrumbs() {
        return crumbs;
    }

    public List<CardPanel> getComponents() {
        return components;
    }

    public void setCrumbs(List<String> crumbs) {
        this.crumbs = crumbs;
    }

    public void setComponents(List<CardPanel> components) {
        this.components = components;
    }
}
