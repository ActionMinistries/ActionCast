package action_cast.model;

import javax.xml.bind.annotation.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by bmichaud on 8/31/2015.
 */
@XmlType
public class SongCast extends UniqueItem {

    @XmlIDREF
    @XmlElement
    private Song song;

    @XmlTransient
    private Map<Role, RoleAssignment> assignmentMap;

    @XmlElementWrapper
    private final HashSet<RoleAssignment> assignments = new HashSet<>();

    private SongCast() {

    }

    public SongCast(int id, Song song) {
        this.song = song;
        this.id = id;
    }

    public Song getSong() {
        return song;
    }

    public Map<Role, RoleAssignment> getAssignmentMap() {
        if (assignmentMap == null) {
            assignmentMap = new HashMap<>();
            for (RoleAssignment assignment : assignments) {
                assignmentMap.put(assignment.getRole(), assignment);
            }
        }
        return assignmentMap;
    }

    public HashSet<RoleAssignment> getAssignments() {
        return assignments;
    }

    public RoleAssignment assign(Person performer, Role role) {
        RoleAssignment roleAssignment;
        if (!getAssignmentMap().containsKey(role)) {
            roleAssignment = new RoleAssignment(assignments.size(), performer, role, this);
            assignments.add(roleAssignment);
            getAssignmentMap().put(role, roleAssignment);

        } else {
            if (performer == null) {
                assignments.remove(getAssignmentMap().get(role));
                getAssignmentMap().remove(role);
                return null;
            } else {
                getAssignmentMap().get(role).setPerson(performer);
            }
        }

        return getAssignmentMap().get(role);
    }

    public boolean isFullyCast() {
        for (Role role : song.getRoles()) {
            if (!getAssignmentMap().containsKey(role) || getAssignmentMap().get(role) == null) {
                return false;
            }
        }
        return true;
    }
}
