package action_cast.model;

import javax.xml.bind.annotation.*;
import java.util.*;

/**
 * Created by bmichaud on 8/31/2015.
 */
@XmlType
public class SongCast extends UniqueItem {

    @XmlIDREF
    @XmlElement
    private Song song;

    @XmlTransient
    private Map<Role, List<RoleAssignment>> assignmentMap;

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

    public Map<Role, List<RoleAssignment>> getAssignmentMap() {
        if (assignmentMap == null) {
            assignmentMap = new HashMap<>();
            for (RoleAssignment assignment : assignments) {
                if (assignmentMap.containsKey(assignment.getRole())) {
                    assignmentMap.get(assignment.getRole()).add(assignment);
                } else {
                    List<RoleAssignment> assignmentList = new ArrayList<>();
                    assignmentList.add(assignment);
                    assignmentMap.put(assignment.getRole(), assignmentList);
                }
            }
        }
        return assignmentMap;
    }

    public HashSet<RoleAssignment> getAssignments() {
        return assignments;
    }

    public RoleAssignment assign(Person performer, Role role, int roleAssignmentId) {
        RoleAssignment roleAssignment;
        if (!getAssignmentMap().containsKey(role)) {
            List<RoleAssignment> assignmentList = new ArrayList<>();
            roleAssignment = new RoleAssignment(assignments.size(), performer, role, this);
            assignmentList.add(roleAssignment);

            assignments.add(roleAssignment);
            getAssignmentMap().put(role, assignmentList);

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
