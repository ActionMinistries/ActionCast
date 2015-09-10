package action_cast.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bmichaud on 8/31/2015.
 */
public class Performance {

    private final String name;
    private final String venue;
    private final Date date;
    private final Song song;
    private final Map<Performer, Role> assignments = new HashMap<>();
    private Director director;

    public Performance(Song song, String name, String venue, Date date) {
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
}