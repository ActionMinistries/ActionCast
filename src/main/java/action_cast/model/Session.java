package action_cast.model;

import action_cast.model.exceptions.InvalidIDException;
import action_cast.model.id.PersonID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bmichaud on 9/10/2015.
 */
@XmlType(propOrder = {"name", "start", "end", "people", "performanceList", "performers"})
public class Session {

    private String name;

    @XmlElement
    private Date start;

    @XmlElement
    private Date end;

    @XmlElement
    private List<Performance> performanceList = new ArrayList<>();
    @XmlElement
    private List<Performer> performers = new ArrayList<>();
    @XmlElementWrapper
    @XmlIDREF
    private List<Person> people = new ArrayList<>();

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
        return performanceList;
    }

    public void addPerformance(Performance performance) {
        performanceList.add(performance);
    }

    public void addPerformer(Performer performer) {
        performers.add(performer);
    }

    public List<Performer> getPerformers() {
        return performers;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void addPerson(PersonID person) throws InvalidIDException {
        //TODO
        //people.add(DataModel.instance.getPerson(person));
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
