package action_cast.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by bmichaud on 9/1/2015.
 */
@XmlType
public class Person extends UniqueItem {

    private Person() {

    }

    @XmlElement
    private String name;

    @XmlElement
    private String phoneNumber;

    @XmlElement
    private String email;

    public Person(int id, String name, String phone, String email) {
        this.name = name;
        this.id = id;
        this.phoneNumber = phone;
        this.email = email;
    }

    @XmlTransient
    public String getName() {
        return name;
    }

    @XmlTransient
    public String getEmail() {
        return email;
    }

    @XmlTransient
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
