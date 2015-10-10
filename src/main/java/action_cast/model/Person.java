package action_cast.model;

import com.sun.istack.internal.NotNull;

import javax.xml.bind.annotation.*;

/**
 * Created by bmichaud on 9/1/2015.
 */
@XmlType
public class Person {
    @XmlAttribute
    @XmlID
    String id;
    @XmlAttribute
    @NotNull
    private String name;


    public Person() {

    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setID(String id) {
        this.id = id;
    }
}
