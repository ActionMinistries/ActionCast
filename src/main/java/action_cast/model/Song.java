package action_cast.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
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

    private Song() {

    }

    public Song(int id, String name, String description) {
        this.name = name;
        this.description = description;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

//    public void setRoles(List<Role> roles) {
//        this.roles = roles;
//    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public List<Role> getRoles() {
        return roles;
    }
}
