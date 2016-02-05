package action_cast.controller.ClientObjects;

import action_cast.model.Director;

import java.util.Date;

/**
 * Created by bmichaud on 12/10/2015.
 */
public class Performance {

    private Song song;
    private Director director;
    private int id;

    public Performance(int id, Director director, Song song) {
        this.id = id;
        this.director = director;
        this.song = song;
    }

    public Director getDirector() {
        return director;
    }

    public Song getSong() {
        return song;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public int getId() {
        return id;
    }
}
