package action_cast.model;

import com.sun.istack.internal.NotNull;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by bmichaud on 9/1/2015.
 */
public class Person {
    @XmlElement
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
}
