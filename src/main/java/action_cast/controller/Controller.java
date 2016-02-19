package action_cast.controller;

import action_cast.controller.ClientObjects.*;
import action_cast.data_store.DataStore;
import action_cast.model.DataModel;
import action_cast.model.RoleType;
import action_cast.model.exceptions.InvalidIDException;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by bmichaud on 12/1/2015.
 */
public class Controller {

    DataModel model;
    DataStore store;

    SessionController sessionController;

    public Controller() {
        ClassLoader classLoader = getClass().getClassLoader();
        store = new DataStore(classLoader.getResource("main.xml").getFile());
        try {
            store.load();
            model = store.getModel();
            sessionController = new SessionController(model.getCurrentSession(), this);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    /*
    *   PEOPLE
    *
     */

    public void assignPersonToRole(Person person, Role role, Song song) throws InvalidIDException {
        action_cast.model.Song modelSong = model.getSong(song.getId());
        if (person != null) {
            model.getCurrentSession().getSongCast(modelSong).assign(model.getPerson(person.getId()), modelSong.getRole(role.getId()));
        } else {
            model.getCurrentSession().getSongCast(modelSong).assign(null, modelSong.getRole(role.getId()));
        }
    }

    public void addPerson(String name) {
        model.addPerson(name);
    }

    public void assignPersonToCurrentSession(Person person) throws InvalidIDException {
        model.getCurrentSession().addPerson(model.getPerson(person.getId()));
    }

    public List<Person> getPeopleNotInCurrentSession() {
        List<Person> results = model.getPeople().stream().filter(person -> !model.getCurrentSession().hasPerson(person)).map(person -> new Person(person.getIndex(), person.getName())).collect(Collectors.toList());
        //= model.getPeople().stream().map(person -> new Person(person.getIndex(), person.getName())).collect(Collectors.toList());
        return results;
    }

    public List<Person> getPeople() {
        List<Person> results = model.getPeople().stream().map(person -> new Person(person.getIndex(), person.getName())).collect(Collectors.toList());
        return results;
    }

    public Person getPerson(int id) throws InvalidIDException {
        action_cast.model.Person person = model.getPerson(id);
        return new Person(person.getIndex(), person.getName());
    }

    /*
    *   SONGS
    *
     */

    public void assignSongToSession(Song song) throws InvalidIDException {
        model.getCurrentSession().addSong(model.getSong(song.getId()));
        //return new SongCast(performance.getIndex(), performance.getDirector(), new Song(performance.getSong().getIndex(), performance.getSong().getName(), performance.getSong().getDescription()));
    }

    public void removeSongFromSession(Song song) throws InvalidIDException {
        model.getCurrentSession().removeSong(model.getSong(song.getId()));
    }

    public Song addSong(String name, String description) {
        action_cast.model.Song song = model.addSong(name, description);
        return new Song(song.getIndex(), song.getName(), song.getDescription());
    }

    public List<Song> getSongs() {
        List<Song> results = model.getSongs().stream().map(song -> new Song(song.getIndex(), song.getName(), song.getDescription())).collect(Collectors.toList());
        return results;
    }

    public List<Song> getSongsNotInCurrentSession() {
        List<Song> results = model.getSongs().stream().filter(song -> !model.getCurrentSession().hasSong(song)).map(song -> new Song(song.getIndex(), song.getName(), song.getDescription())).collect(Collectors.toList());
        //= model.getPeople().stream().map(person -> new Person(person.getIndex(), person.getName())).collect(Collectors.toList());
        return results;
    }

    public Song getSong(int id) throws InvalidIDException {
        action_cast.model.Song song = model.getSong(id);
        return new Song(song.getIndex(), song.getName(), song.getDescription());
    }

    public void updateSong(Song song) throws InvalidIDException {
        model.getSong(song.getId()).setName(song.getName());
        model.getSong(song.getId()).setDescription(song.getDescription());
    }

    /*
    *   ROLES
    *
     */

    public List<Role> getSongRoles(int id) throws InvalidIDException {
        action_cast.model.Song song = model.getSong(id);
        return song.getRoles().stream().map(role -> new Role(role.getIndex(), role.getName(), role.getDescription(), role.getType())).collect(Collectors.toList());
    }

    public List<action_cast.controller.ClientObjects.Role> getRoles(int songId) throws InvalidIDException {

        return model.getSong(songId).getRoles().stream().map(role -> new action_cast.controller.ClientObjects.Role(role.getIndex(), role.getName(), role.getDescription(), role.getType())).collect(Collectors.toList());
    }

    public Role createRole(int songId, String name, String description, RoleType type) throws InvalidIDException {
        action_cast.model.Role role = model.getSong(songId).addRole(name, description, type);
        return new Role(role.getIndex(), role.getName(), role.getDescription(), role.getType());
    }

    public Role getRole(int songId, int roleId) throws InvalidIDException {
        action_cast.model.Role role = model.getSong(songId).getRole(roleId);
        return new Role(role.getIndex(), role.getName(), role.getDescription(), role.getType());
    }

    public List<action_cast.controller.ClientObjects.RoleAssignment> getRoleAssignmentsForSong(action_cast.controller.ClientObjects.Song song) throws InvalidIDException {
        List<action_cast.controller.ClientObjects.RoleAssignment> roleAssignments = model.getCurrentSession().getSongCast(model.getSong(song.getId())).getAssignments().stream().map(assignment -> new RoleAssignment(assignment.getIndex(), assignment.getPerson().getIndex(), assignment.getRole().getIndex())).collect(Collectors.toList());
        return roleAssignments;
    }

    /*
    *   SESSION
    *
     */

    public Session getCurrentSession() {
        return new Session(model.getCurrentSession().getName(), model.getCurrentSession().getStartDate(), model.getCurrentSession().getEndDate());
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public void updateCurrentSession(Session session) {
        model.getCurrentSession().setName(session.getName());
        model.getCurrentSession().setEnd(session.getEndDate());
        model.getCurrentSession().setStart(session.getStartDate());
        save();
    }

    private void save() {
        try {
            store.save();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


}
