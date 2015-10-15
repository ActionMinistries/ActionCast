package action_cast.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;

/**
 * Created by bmichaud on 9/1/2015.
 */
public class Director {

    @XmlElement
    @XmlIDREF
    private Person person;

    private Director() {

    }

    public Director(Person person) {
        this.person = person;
    }

    public String getName() {
        return person.getName();
    }
}
