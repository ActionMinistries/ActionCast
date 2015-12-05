package action_cast.model.modelinterface;

import action_cast.model.Director;
import action_cast.model.Performer;
import action_cast.model.Role;
import action_cast.model.Song;

import javax.xml.bind.annotation.XmlIDREF;
import java.util.Date;
import java.util.Map;

/**
 * Created by bmichaud on 12/4/2015.
 */
public interface PerformanceView {
    String getName();

    String getVenue();

    Date getDate();

    @XmlIDREF
    Song getSong();

    Map<Performer, Role> getAssignments();

    Director getDirector();
}
