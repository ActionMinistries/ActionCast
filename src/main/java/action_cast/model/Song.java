package action_cast.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bmichaud on 8/31/2015.
 */
public class Song {
    private final String name;
    private final String description;
    private final List<Performance> performances = new ArrayList<>();
    private List<Role> roles = new ArrayList<>();

    public Song(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void addPerformance(Performance performance) {
        this.performances.add(performance);
    }

    public List<Performance> getPerformances() {
        return performances;
    }
}
