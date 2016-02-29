package action_cast.controller.ClientObjects;

/**
 * Created by bmichaud on 12/8/2015.
 */
public class Person {

    private final int id;
    private String phoneNumber;
    private String email;
    private String name;

    public Person(int id, String name, String phoneNumber, String email) {
        this.name = name;
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getId() {
        return id;
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
