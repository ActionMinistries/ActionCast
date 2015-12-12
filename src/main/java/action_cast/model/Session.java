package action_cast.model;

import action_cast.model.exceptions.InvalidIDException;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by bmichaud on 9/10/2015.
 */
@XmlType(propOrder = {"name", "start", "end", "people", "performances", "performers"})
public class Session extends UniqueItem {

    private String name;

    @XmlElement
    private Date start;

    @XmlElement
    private Date end;

    @XmlElementWrapper
    private List<Performance> performances = new ArrayList<>();
    @XmlElement
    private List<Performer> performers = new ArrayList<>();
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

    public List<Performance> getPerformances() {
        return performances;
    }

    public Performance addPerformance(Song song, String name, String venue, Date date) throws InvalidIDException {
        performances.add(new Performance(performances.size(), song, name, venue, date));
        return performances.get(performances.size() - 1);
    }

    public void addPerformer(Performer performer) {
        performers.add(performer);
    }

    public Performance getPerformance(int id) {
        return performances.get(id);
    }

    public List<Performer> getPerformers() {
        return performers;
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
}
