package action_cast.controller;

import action_cast.controller.ClientObjects.Performance;
import action_cast.controller.ClientObjects.Person;
import action_cast.controller.ClientObjects.RoleAssignment;
import action_cast.controller.ClientObjects.Song;
import action_cast.model.Role;
import action_cast.model.Session;
import action_cast.model.exceptions.InvalidIDException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by brian on 12/3/2015.
 */
public class SessionController {

    private final Session session;
    private final Controller controller;

    public SessionController(Session session, Controller controller) {
        this.session = session;
        this.controller = controller;
    }

    public List<Person> getPeople() {
        List<Person> results = session.getPeople().stream().map(person -> new Person(person.getIndex(), person.getName())).collect(Collectors.toList());
        return results;
    }

    public void setPeople(List<Person> people) throws InvalidIDException {
        session.clearPeople();
        for (Person person : people) {
           controller.assignPersonToCurrentSession(person);
        }
    }

    public Performance addPerformance(Song song, String name, String venue, Date date) throws InvalidIDException {
        action_cast.model.Performance performance = session.addPerformance(null, name, venue, date);
        controller.assignSongToPerformance(performance.getIndex(), song.getId());
        return new Performance(performance.getIndex(), performance.getName(), performance.getDirector(), new Song(performance.getSong().getIndex(), performance.getSong().getName(), performance.getSong().getDescription()), performance.getVenue(), performance.getDate());
    }

    public void updatePerformance(Performance performance) throws InvalidIDException {
        action_cast.model.Performance performanceData = session.getPerformance(performance.getId());
        performanceData.setName(performance.getName());
        performanceData.setDate(performance.getDate());
        performanceData.setDirector(performance.getDirector());
        controller.assignSongToPerformance(performance.getId(), performance.getSong().getId());
        performanceData.setVenue(performance.getVenue());
    }

    public List<Performance> getPerformances() {
        return session.getPerformances().stream().map(performance -> (new Performance(performance.getIndex(), performance.getName(), performance.getDirector(), new Song(performance.getSong().getIndex(), performance.getSong().getName(), performance.getSong().getDescription()), performance.getVenue(), performance.getDate()))).collect(Collectors.toList());
    }

    public List<RoleAssignment> getPerformanceAssignments(Performance p) {
        List<RoleAssignment> roleAssignments = new ArrayList<>();
        for (action_cast.model.RoleAssignment assignment : session.getPerformance(p.getId()).getAssignments()) {
            roleAssignments.add(new RoleAssignment(assignment.getIndex(), assignment.getPerson().getIndex(), assignment.getRole().getIndex()));
        }
//        Set<Map.Entry<Role, action_cast.model.Person>> entries = session.getPerformance(p.getId()).getAssignmentMap().entrySet();
//        for (Map.Entry<Role, action_cast.model.Person> entry : entries ) {
//            Role role = entry.getKey();
//            action_cast.model.Person person = entry.getValue();
//
//            roleAssignments.add(new RoleAssignment(new Person(person.getIndex(), person.getName()), new action_cast.controller.ClientObjects.Role(role.getIndex(), role.getName(), role.getDescription(), role.getType())));
//        }
        return roleAssignments;
    }
}
