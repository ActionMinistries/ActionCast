package action_cast.controller;

import action_cast.controller.ClientObjects.Person;
import action_cast.model.Session;
import action_cast.model.modelinterface.PerformanceView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by brian on 12/3/2015.
 */
public class SessionController {

    private final Session session;

    public SessionController(Session session) {
        this.session = session;
    }

    public List<Person> getPeople() {
        List<Person> results = session.getPeople().stream().map(person -> new Person(person.getIndex(), person.getName())).collect(Collectors.toList());
        return results;
    }

    public List<PerformanceView> getPerformances() {
        return session.getPerformances().stream().map(performance -> ((PerformanceView)performance)).collect(Collectors.toList());
    }
}
