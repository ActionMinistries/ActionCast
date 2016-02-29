package action_cast.model;

import javax.xml.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by bmichaud on 9/10/2015.
 */
@XmlType(propOrder = {"name", "start", "end", "people", "casts"})
public class Session extends UniqueItem {

    private String name;

    @XmlElement
    private Date start;

    @XmlElement
    private Date end;

    @XmlElementWrapper
    private List<SongCast> casts = new ArrayList<>();

    @XmlTransient
    private Map<Song, SongCast> castMap;

    @XmlElementWrapper
    @XmlIDREF
    private HashSet<Person> people = new HashSet<>();

    private Session () {

    }

    public Session(String name, Date start, Date end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    public Date getStartDate() {
        return start;
    }

    public Date getEndDate() {
        return end;
    }

    public List<Song> getSongs() {
        return new ArrayList<>(getCastMap().keySet());
    }

    public void addSong(Song song) {
        casts.add(new SongCast(casts.size(), song));
        getCastMap().put(song, casts.get(casts.size()-1));
    }

    public List<Person> getPeople() {
        return people.stream().collect(Collectors.toList());
    }

    public void addPerson(Person person) {
        people.add(person);
    }

    public boolean hasPerson(Person person) {
        return people.contains(person);
    }

    public boolean hasSong(Song song) {
        return getCastMap().containsKey(song);
    }

    public void clearPeople() {
        people.clear();
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SongCast getSongCast(Song song) {
        if (getCastMap().containsKey(song)) {
            return getCastMap().get(song);
        } else {
            return null;
        }
    }

    private Map<Song, SongCast> getCastMap() {
        if (castMap == null) {
            castMap = new HashMap<>();
            for (SongCast cast : casts) {
                castMap.put(cast.getSong(), cast);
            }
        }
        return castMap;
    }

    public void removeSong(Song song) {
        casts.remove(castMap.get(song));
        castMap.remove(song);
    }
}
