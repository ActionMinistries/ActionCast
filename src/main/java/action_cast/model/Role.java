package action_cast.model;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by bmichaud on 8/31/2015.
 */
public class Role {
    @XmlElement
    private String name;
    @XmlElement
    private String description;
    @XmlElement
    private RoleType type;

    public Role() {

    }

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
