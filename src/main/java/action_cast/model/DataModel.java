package action_cast.model;

import action_cast.model.exceptions.InvalidIDException;
import action_cast.model.id.PersonID;
import action_cast.model.id.SongID;

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

    public Person getPerson(PersonID id) throws InvalidIDException {
        if (id.getIndex() < 0 || id.getIndex() >= people.size()) {
            throw new InvalidIDException();
        }
        return people.get(id.getIndex());
    }

    public PersonID addPerson(String name) {
        Person person = new Person(name);
        people.add(person);
        return new PersonID(people.size() - 1);
    }

    public Song getSong(SongID songID) throws InvalidIDException {
        if (songID.getIndex() < 0 || songID.getIndex() >= songs.size()) {
            throw new InvalidIDException();
        }
        return songs.get(songID.getIndex());
    }

    public SongID addSong(String name, String description) {
        songs.add(new Song(name, description));
        return new SongID(songs.size() - 1);
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
