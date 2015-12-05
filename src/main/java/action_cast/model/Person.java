package action_cast.model;

import action_cast.model.modelinterface.PersonModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by bmichaud on 9/1/2015.
 */
@XmlType
public class Person extends UniqueItem implements PersonModel{

    private Person() {

    }

    @XmlElement
    private String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
