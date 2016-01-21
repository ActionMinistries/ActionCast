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

    public Role(int id, String name, String description, RoleType type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
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
}
