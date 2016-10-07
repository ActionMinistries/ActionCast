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
    private Map<Role, List<RoleAssignment>> assignmentMap = null;
    @XmlTransient
    private Map<Role, Integer> roleCountMap = null;

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
                recordAssignment(assignment);
            }
        }
        return assignmentMap;
    }

    private Map<Role, Integer> getRoleCountMap() {
        if (roleCountMap == null) {
            roleCountMap = new HashMap<>();
            for (Role role : song.getRoles()) {
                roleCountMap.put(role, 0);
            }
        }
        return roleCountMap;
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
        RoleAssignment roleAssignment = new RoleAssignment(assignments.size(), performer, role, this);
        addAssignment(roleAssignment);
        return roleAssignment;
    }

    public void unassign(int roleAssignmentId) {
        for (RoleAssignment assignment : assignments) {
            if (assignment.getIndex() == roleAssignmentId) {
                removeAssignment(assignment);
                break;
            }
        }

    }

    public boolean isFullyCast() {
        for (Role role : song.getRoles()) {
            if (!getAssignmentMap().containsKey(role) || getAssignmentMap().get(role) == null) {
                return false;
            }
        }
        return true;
    }

    private void addAssignment(RoleAssignment assignment) {
        recordAssignment(assignment);
        assignments.add(assignment);
    }

    private void removeAssignment(RoleAssignment assignment) {
        assignments.remove(assignment);
        getAssignmentMap().remove(assignment.getRole()).remove(assignment);
        getRoleCountMap().put(assignment.getRole(), getRoleCountMap().get(assignment.getRole()) - 1);
    }

    private void recordAssignment(RoleAssignment assignment) {
        if (getAssignmentMap().containsKey(assignment.getRole())) {
            getAssignmentMap().get(assignment.getRole()).add(assignment);
        } else {
            List<RoleAssignment> assignmentList = new ArrayList<>();
            assignmentList.add(assignment);
            getAssignmentMap().put(assignment.getRole(), assignmentList);
        }
        if (getRoleCountMap().containsKey(assignment.getRole())) {
            getRoleCountMap().put(assignment.getRole(), getRoleCountMap().get(assignment.getRole()) + 1);
        } else {
            getRoleCountMap().put(assignment.getRole(), 1);
        }
    }
}
