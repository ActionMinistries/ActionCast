package action_cast.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bmichaud on 8/31/2015.
 */
@XmlType
public class Song extends UniqueItem {
    @XmlElement
    private String name;
    @XmlElement
    private String description;
    @XmlElementWrapper
    private final List<Role> roles = new ArrayList<>();
    private Role role;

    private Song() {

    }

    public Song(int id, String name, String description) {
        this.name = name;
        this.description = description;
        this.id = id;
    }

    @XmlTransient
    public String getName() {
        return name;
    }

    @XmlTransient
    public String getDescription() {
        return description;
    }

//    public void setRoles(List<Role> roles) {
//        this.roles = roles;
//    }

    public Role addRole(String name, String description, RoleType type) {
        roles.add(new Role(roles.size(), name, description, type));
        return roles.get(roles.size()-1);
    }

    public List<Role> getRoles() {
        return roles;
    }

    public Role getRole(int roleID) {
        return roles.get(roleID);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }
}
