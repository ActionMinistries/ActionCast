package action_cast.controller.ClientObjects;

import action_cast.model.Director;

import java.util.Date;

/**
 * Created by bmichaud on 12/10/2015.
 */
public class Performance {

    private String name;
    private String venue;
    private Date date;
    private Song song;
    private Director director;
    private int id;

    public Performance(int id, Director director, Song song) {
        this.id = id;
        this.date = date;
        this.director = director;
        this.name = name;
        this.song = song;
        this.venue = venue;
    }

    public Date getDate() {
        return date;
    }

    public Director getDirector() {
        return director;
    }

    public String getName() {
        return name;
    }

    public Song getSong() {
        return song;
    }

    public String getVenue() {
        return venue;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getId() {
        return id;
    }
}
