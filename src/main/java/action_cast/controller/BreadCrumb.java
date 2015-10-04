package action_cast.controller;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by brian on 9/26/2015.
 */
public class BreadCrumb {

    private List<String> crumbs = new ArrayList<>();
    private List<Component> components = new ArrayList<>();

    public void addCrumb(String crumb) {
        crumbs.add(crumb);
    }

    public void addComponent(Component comp) {
        components.add(comp);
    }

    public List<String> getCrumbs() {
        return crumbs;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setCrumbs(List<String> crumbs) {
        this.crumbs = crumbs;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }
}
