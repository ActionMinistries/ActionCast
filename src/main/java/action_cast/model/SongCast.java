package action_cast.model;

import action_cast.model.exceptions.InvalidIDException;

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

    public RoleAssignment assign(Person performer, Role role, int roleAssignmentId) throws InvalidIDException {
        RoleAssignment roleAssignment = null;
        for (RoleAssignment assignment : getAssignmentMap().get(role)) {
            if (assignment.getIndex() == roleAssignmentId) {
                assignment.setPerson(performer);
                roleAssignment = assignment;
                break;
            }
        }
        if (roleAssignment == null) {
            throw new InvalidIDException();
        }
        return roleAssignment;
    }

    public RoleAssignment assign(Person performer, Role role) throws InvalidIDException {
        RoleAssignment roleAssignment = null;

        if (!getAssignmentMap().containsKey(role)) {
            List<RoleAssignment> assignmentList = new ArrayList<>();
            roleAssignment = new RoleAssignment(assignments.size(), performer, role, this);
            assignmentList.add(roleAssignment);

            assignments.add(roleAssignment);
            getAssignmentMap().put(role, assignmentList);

        } else {
            roleAssignment = new RoleAssignment(assignments.size(), performer, role, this);

            assignments.add(roleAssignment);
            getAssignmentMap().get(role).add(roleAssignment);
        }
        return roleAssignment;
    }

        public void unassign(int roleAssignmentId) {
        RoleAssignment selected = null;
        for (RoleAssignment assignment : assignments) {
            if (assignment.getIndex() == roleAssignmentId) {
                selected = assignment;
                assignments.remove(assignment);
                break;
            }
        }

        getAssignmentMap().remove(selected.getRole()).remove(selected);
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
