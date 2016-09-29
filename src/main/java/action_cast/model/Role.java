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

    private int minAssignments;
    private int maxAssignments;
    private boolean optional;

    private Role() {

    }

    @Override
    protected String getId() {
        return song.getId() + "_" + super.getId();
    }

    public Role(int id, String name, String description, RoleType type, Song song, int min, int max, boolean optional) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.id = id;
        this.song = song;
        this.minAssignments = min;
        this.maxAssignments = max;
        this.optional = optional;
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
