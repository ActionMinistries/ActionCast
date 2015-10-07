package action_cast.model;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by bmichaud on 9/1/2015.
 */
public class Director {

    @XmlElement
    private Person person;

    public Director() {

    }

    public Director(Person person) {
        this.person = person;
    }

    public String getName() {
        return person.getName();
    }
}
