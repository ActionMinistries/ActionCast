package action_cast.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by bmichaud on 1/13/2016.
 */
@XmlType
public class RoleAssignment extends UniqueItem{

    @XmlElement
    @XmlIDREF
    private Person person;

    @XmlElement
    @XmlIDREF
    private Role role;

    private RoleAssignment() {

    }

    public RoleAssignment(Person person, Role role) {
        this.person = person;
        this.role = role;
    }

    public Person getPerson() {
        return person;
    }

    public Role getRole() {
        return role;
    }
}
