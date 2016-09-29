package action_cast.controller.ClientObjects;

import action_cast.model.RoleType;

/**
 * Created by brian on 1/2/2016.
 */
public class Role {
    private final int id;
    private String name;
    private String description;
    private RoleType type;

    private int minAssignments;
    private int maxAssignments;
    private boolean optional;

    public Role(int id, String name, String description, RoleType type, int min, int max, boolean optional) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.minAssignments = min;
        this.maxAssignments = max;
        this.optional = optional;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public RoleType getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public void setMaxAssignments(int maxAssignments) {
        this.maxAssignments = maxAssignments;
    }

    public void setMinAssignments(int minAssignments) {
        this.minAssignments = minAssignments;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    public int getMaxAssignments() {
        return maxAssignments;
    }

    public int getMinAssignments() {
        return minAssignments;
    }

    public boolean isOptional() {
        return optional;
    }
}
