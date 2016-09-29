package action_cast.controller;

import action_cast.controller.ClientObjects.*;
import action_cast.controller.events.RoleAssignmentEvent;
import action_cast.controller.events.SongsUpdateEvent;
import action_cast.data_store.DataStore;
import action_cast.model.DataModel;
import action_cast.model.RoleType;
import action_cast.model.exceptions.InvalidIDException;
import com.google.common.eventbus.EventBus;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by bmichaud on 12/1/2015.
 */
public class Controller {

    DataModel model;
    DataStore store;

    SessionController sessionController;

    private EventBus eventBus = new EventBus();

    public Controller() {
        String path = null;
        try {
            path = Controller.class.getProtectionDomain().getCodeSource().getLocation().toURI().resolve("..").getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        ClassLoader classLoader = getClass().getClassLoader();
        File dataFile = new File(path + "/main.xml");//classLoader.getResource("./main.xml").getFile());
        if (!dataFile.exists()) {
            InputStream initialStream = classLoader.getResourceAsStream("main.xml");
            try {
                if (dataFile.createNewFile()) {
                    byte[] buffer = new byte[initialStream.available()];
                    initialStream.read(buffer);
                    OutputStream outStream = new FileOutputStream(dataFile);
                    outStream.write(buffer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        //System.out.println("Using file: " + dataFile.getAbsolutePath());
        store = new DataStore(dataFile.getAbsolutePath());
        try {
            store.load();
            model = store.getModel();
            sessionController = new SessionController(model.getCurrentSession(), this);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public EventBus getEventBus() {
        return eventBus;
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
        eventBus.post(new RoleAssignmentEvent());
    }

    public void addPerson(String name, String phoneNumber, String email) {
        model.addPerson(name, phoneNumber, email);
    }

    public void updatePerson(Person person) throws InvalidIDException {
        action_cast.model.Person modelPerson = model.getPerson(person.getId());
        modelPerson.setName(person.getName());
        modelPerson.setEmail(person.getEmail());
        modelPerson.setPhoneNumber(person.getPhoneNumber());
    }

    public void assignPersonToCurrentSession(Person person) throws InvalidIDException {
        model.getCurrentSession().addPerson(model.getPerson(person.getId()));
    }

    public List<Person> getPeopleNotInCurrentSession() {
        return model.getPeople().stream().filter(person -> !model.getCurrentSession().hasPerson(person)).map(person -> new Person(person.getIndex(), person.getName(), person.getPhoneNumber(), person.getEmail())).collect(Collectors.toList());
    }

    public List<Person> getPeople() {
        return model.getPeople().stream().map(person -> new Person(person.getIndex(), person.getName(), person.getPhoneNumber(), person.getEmail())).collect(Collectors.toList());
    }

    public Person getPerson(int id) throws InvalidIDException {
        action_cast.model.Person person = model.getPerson(id);
        return new Person(person.getIndex(), person.getName(), person.getPhoneNumber(), person.getEmail());
    }

    public int getRoleTypeCountFor(Person person, RoleType type) throws InvalidIDException {
        int result = 0;
        action_cast.model.Person modelPerson = model.getPerson(person.getId());
        for (action_cast.model.Song song : model.getCurrentSession().getSongs()) {
            for (action_cast.model.RoleAssignment assignment : model.getCurrentSession().getSongCast(song).getAssignments()) {
                if (assignment.getPerson() == modelPerson && assignment.getRole().getType() == type) {
                    ++result;
                }
            }
        }
        return result;
    }

    /*
    *   SONGS
    *
     */

    public void assignSongToSession(Song song) throws InvalidIDException {
        model.getCurrentSession().addSong(model.getSong(song.getId()));
    }

    public void removeSongFromSession(Song song) throws InvalidIDException {
        model.getCurrentSession().removeSong(model.getSong(song.getId()));
    }

    public Song addSong(String name, String description) {
        action_cast.model.Song song = model.addSong(name, description);
        getEventBus().post(new SongsUpdateEvent());
        return new Song(song.getIndex(), song.getName(), song.getDescription());
    }

    public List<Song> getSongs() {
        return model.getSongs().stream().map(song -> new Song(song.getIndex(), song.getName(), song.getDescription())).collect(Collectors.toList());
    }

    public List<Song> getSongsNotInCurrentSession() {
        return model.getSongs().stream().filter(song -> !model.getCurrentSession().hasSong(song)).map(song -> new Song(song.getIndex(), song.getName(), song.getDescription())).collect(Collectors.toList());
    }

    public Song getSong(int id) throws InvalidIDException {
        action_cast.model.Song song = model.getSong(id);
        return new Song(song.getIndex(), song.getName(), song.getDescription());
    }

    public void updateSong(Song song) throws InvalidIDException {
        model.getSong(song.getId()).setName(song.getName());
        model.getSong(song.getId()).setDescription(song.getDescription());
        getEventBus().post(new SongsUpdateEvent());
    }

    public boolean isSongCast(Song song) throws InvalidIDException {
        return model.getCurrentSession().getSongCast(model.getSong(song.getId())).isFullyCast();
    }

    /*
    *   ROLES
    *
     */

    public List<Role> getSongRoles(int id) throws InvalidIDException {
        action_cast.model.Song song = model.getSong(id);
        return song.getRoles().stream().map(role -> new Role(role.getIndex(), role.getName(), role.getDescription(), role.getType(), role.getMinAssignments(), role.getMaxAssignments(), role.isOptional())).collect(Collectors.toList());
    }

    public List<action_cast.controller.ClientObjects.Role> getRoles(int songId) throws InvalidIDException {

        return model.getSong(songId).getRoles().stream().map(role -> new action_cast.controller.ClientObjects.Role(role.getIndex(), role.getName(), role.getDescription(), role.getType(), role.getMinAssignments(), role.getMaxAssignments(), role.isOptional())).collect(Collectors.toList());
    }

    public Role createMainRole(int songId, String name, String description) throws InvalidIDException {
        action_cast.model.Role role = model.getSong(songId).addRole(name, description, RoleType.MAIN, 1, 1, false);
        getEventBus().post(new SongsUpdateEvent());
        return new Role(role.getIndex(), role.getName(), role.getDescription(), role.getType(), role.getMinAssignments(), role.getMaxAssignments(), role.isOptional());
    }

    public Role createSupportRole(int songId, String name, String description, int minAssignments, int maxAssignments) throws InvalidIDException {
        action_cast.model.Role role = model.getSong(songId).addRole(name, description, RoleType.SUPPORT, minAssignments, maxAssignments, false);
        getEventBus().post(new SongsUpdateEvent());
        return new Role(role.getIndex(), role.getName(), role.getDescription(), role.getType(), role.getMinAssignments(), role.getMaxAssignments(), role.isOptional());
    }

    public Role createBackgroundRole(int songId, String name, String description, int minAssignments, boolean optional) throws InvalidIDException {
        action_cast.model.Role role = model.getSong(songId).addRole(name, description, RoleType.BACKGROUND, minAssignments, 0, optional);
        getEventBus().post(new SongsUpdateEvent());
        return new Role(role.getIndex(), role.getName(), role.getDescription(), role.getType(), role.getMinAssignments(), role.getMaxAssignments(), role.isOptional());
    }

    public Role getRole(int songId, int roleId) throws InvalidIDException {
        action_cast.model.Role role = model.getSong(songId).getRole(roleId);
        return new Role(role.getIndex(), role.getName(), role.getDescription(), role.getType(), role.getMinAssignments(), role.getMaxAssignments(), role.isOptional());
    }

    public List<action_cast.controller.ClientObjects.RoleAssignment> getRoleAssignmentsForSong(action_cast.controller.ClientObjects.Song song) throws InvalidIDException {
        return model.getCurrentSession().getSongCast(model.getSong(song.getId())).getAssignments().stream().map(assignment -> new RoleAssignment(assignment.getIndex(), assignment.getPerson().getIndex(), assignment.getRole().getIndex())).collect(Collectors.toList());
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
    }

    public void save() {
        try {
            store.save();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}