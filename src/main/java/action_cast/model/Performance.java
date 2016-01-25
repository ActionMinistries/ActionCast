package action_cast.model;

import action_cast.widgets.events.RoleAssignedEvent;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.*;

/**
 * Created by bmichaud on 8/31/2015.
 */
@XmlType
public class Performance extends UniqueItem {

    private String name;
    private String venue;
    private Date date;
    private Song song;
    private Director director;

    @XmlTransient
    private Map<Role, RoleAssignment> assignmentMap;

    @XmlElementWrapper
    private final HashSet<RoleAssignment> assignments = new HashSet<>();

    private Performance() {

    }

    public Performance(int id, Song song, String name, String venue, Date date) {
        this.song = song;
        this.name = name;
        this.venue = venue;
        this.date = date;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getVenue() {
        return venue;
    }

    public Date getDate() {
        return date;
    }

    @XmlIDREF
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
            roleAssignment = new RoleAssignment(performer, role);
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

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSong(Song song) {
        this.song = song;
    }
}
