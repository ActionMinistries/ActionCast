package action_cast.controller;

import action_cast.data_store.DataStore;
import action_cast.model.DataModel;
import action_cast.model.exceptions.InvalidIDException;
import action_cast.model.id.PersonID;
import action_cast.model.modelinterface.PersonView;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by bmichaud on 12/1/2015.
 */
public class Controller {

    DataModel model;

    SessionController sessionController;

    public Controller() {
        ClassLoader classLoader = getClass().getClassLoader();
        DataStore store = new DataStore(classLoader.getResource("main.xml").getFile());
        try {
            store.load();
            model = store.getModel();
            sessionController = new SessionController(model.getCurrentSession());
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public void assignPersonToCurrentSession(PersonID id) throws InvalidIDException {
        model.getCurrentSession().addPerson(id);
    }

    public void addPerson(String name) {
        model.addPerson(name);
    }

    public List<PersonView> getPeople() {
        List<PersonView> results = model.getPeople().stream().map(person -> (PersonView)(person)).collect(Collectors.toList());
        return results;
    }

    public SessionController getSessionController() {
        return sessionController;
    }
}
