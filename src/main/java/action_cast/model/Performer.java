package action_cast.model;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by bmichaud on 9/1/2015.
 */
public class Performer {
    @XmlElement
    private Person person;

    public Performer() {

    }

    public Performer(Person person) {
        this.person = person;
    }

    public String getName() {
        return person.getName();
    }
}
