package action_cast.controller.ClientObjects;

/**
 * Created by bmichaud on 12/10/2015.
 */
public class Song {
    private String name;
    private String description;

    public Song(String name, String description) {
        this.description = description;
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
