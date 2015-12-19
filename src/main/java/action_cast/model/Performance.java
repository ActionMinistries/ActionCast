package action_cast.model;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    @XmlElementWrapper
    private Map<Person, Role> assignments = new HashMap<>();

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

    public Map<Person, Role> getAssignments() {
        return assignments;
    }

    public void assign(Person performer, Role role) {
        assignments.put(performer, role);
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
