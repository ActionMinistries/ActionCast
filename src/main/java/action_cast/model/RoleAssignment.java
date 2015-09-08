package action_cast.model;

/**
 * Created by bmichaud on 9/1/2015.
 */
public class RoleAssignment {
    private final Performer performer;
    private final Role role;
    private final Performance performance;

    public RoleAssignment(Performer performer, Role role, Performance performance) {
        this.performer = performer;
        this.role = role;
        this.performance = performance;
    }

    public Performer getPerformer() {
        return performer;
    }

    public Role getRole() {
        return role;
    }

    public Performance getPerformance() {
        return performance;
    }
}
