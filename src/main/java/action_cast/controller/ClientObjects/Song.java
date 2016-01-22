package action_cast.controller.ClientObjects;

/**
 * Created by bmichaud on 12/10/2015.
 */
public class Song {
    private final int id;
    private String name;
    private String description;

    public Song(int id, String name, String description) {
        this.description = description;
        this.name = name;
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
