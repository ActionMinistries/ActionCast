package action_cast.model;

import action_cast.model.exceptions.InvalidIDException;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bmichaud on 9/14/2015.
 */
@XmlRootElement
public class DataModel {

    @XmlElementWrapper
    private final List<Person> people = new ArrayList<>();

    @XmlElementWrapper
    private final List<Song> songs = new ArrayList<>();

    private Session currentSession = null;

    public DataModel() {

    }

    public Person getPerson(int id) throws InvalidIDException {
        if (id < 0 || id >= people.size()) {
            throw new InvalidIDException();
        }
        return people.get(id);
    }

    public Person addPerson(String name) {
        Person person = new Person(people.size(), name);
        people.add(person);
        return person;
    }

    public Song getSong(int songID) throws InvalidIDException {
        if (songID < 0 || songID >= songs.size()) {
            throw new InvalidIDException();
        }
        return songs.get(songID);
    }

    public List<Song> getSongs() {
        return songs;
    }

    public Song addSong(String name, String description) {
        songs.add(new Song(songs.size(), name, description));
        return songs.get(songs.size() - 1);
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session s) {
        currentSession = s;
    }

    public List<Person> getPeople() {
        return people;
    }
}
