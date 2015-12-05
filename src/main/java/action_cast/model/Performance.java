package action_cast.model;

import action_cast.model.exceptions.InvalidIDException;
import action_cast.model.id.SongID;
import action_cast.model.modelinterface.PerformanceModel;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlIDREF;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bmichaud on 8/31/2015.
 */
public class Performance implements PerformanceModel {

    private String name;
    private String venue;
    private Date date;
    private Song song;
    @XmlElementWrapper
    private Map<Performer, Role> assignments = new HashMap<>();
    private Director director;

    private Performance() {

    }

    public Performance(SongID song, String name, String venue, Date date) throws InvalidIDException {
        //TODO
        //this.song = DataModel.instance.getSong(song);
        this.name = name;
        this.venue = venue;
        this.date = date;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getVenue() {
        return venue;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    @XmlIDREF
    public Song getSong() {
        return song;
    }

    @Override
    public Map<Performer, Role> getAssignments() {
        return assignments;
    }

    public void assign(Performer performer, Role role) {
        assignments.put(performer, role);
    }

    @Override
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
