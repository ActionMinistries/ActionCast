package action_cast.model;

/**
 * Created by bmichaud on 8/31/2015.
 */
public class Role {
    private final String name;
    private final String description;
    private final RoleType type;

    public Role(String name, String description, RoleType type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public RoleType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
