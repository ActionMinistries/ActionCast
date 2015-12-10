package action_cast.model;

import action_cast.model.exceptions.InvalidIDException;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlIDREF;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bmichaud on 8/31/2015.
 */
public class Performance {

    private String name;
    private String venue;
    private Date date;
    private Song song;
    private Director director;
    @XmlElementWrapper
    private Map<Performer, Role> assignments = new HashMap<>();

    private Performance() {

    }

    public Performance(Song song, String name, String venue, Date date) throws InvalidIDException {
        this.song = song;
        this.name = name;
        this.venue = venue;
        this.date = date;
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

    public Map<Performer, Role> getAssignments() {
        return assignments;
    }

    public void assign(Performer performer, Role role) {
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
