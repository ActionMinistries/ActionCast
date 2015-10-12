package action_cast.model;

import javax.xml.bind.annotation.XmlElement;
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
    private List<Role> roles = new ArrayList<>();

    public Song() {

    }

    public Song(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Role> getRoles() {
        return roles;
    }
}
