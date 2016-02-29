package action_cast.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlTransient;
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

    @XmlElement
    @XmlIDREF
    private SongCast cast;

    private RoleAssignment() {

    }

    public RoleAssignment(int id, Person person, Role role, SongCast cast) {
        this.person = person;
        this.role = role;
        this.id = id;
        this.cast = cast;
    }

    @Override
    protected String getId() {
        return cast.getId() + "_" + super.getId();
    }

    @XmlTransient
    public Person getPerson() {
        return person;
    }

    public Role getRole() {
        return role;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
