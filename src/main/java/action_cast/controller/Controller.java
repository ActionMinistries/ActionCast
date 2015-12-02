package action_cast.controller;

import action_cast.model.DataModel;
import action_cast.model.exceptions.InvalidIDException;
import action_cast.model.id.PersonID;

/**
 * Created by bmichaud on 12/1/2015.
 */
public class Controller {

    DataModel model;

    public void assignPersonToCurrentSession(PersonID id) throws InvalidIDException {
        model.getCurrentSession().addPerson(id);
    }


}
