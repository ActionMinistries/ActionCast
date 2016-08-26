package action_cast.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by bmichaud on 8/31/2015.
 */
@XmlType
public class Role extends UniqueItem {
    @XmlElement
    private String name;
    @XmlElement
    private String description;
    @XmlElement
    private RoleType type;

    @XmlElement
    @XmlIDREF
    Song song;

    private Role() {

    }

    @Override
    protected String getId() {
        return song.getId() + "_" + super.getId();
    }

    public Role(int id, String name, String description, RoleType type, Song song) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.id = id;
        this.song = song;
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
