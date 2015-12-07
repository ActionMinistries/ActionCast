package action_cast.controller.ClientObjects;

import java.util.Date;

/**
 * Created by bmichaud on 12/7/2015.
 */
public class Session {

    private String name;
    private Date start;
    private Date end;

    public Session(String name, Date start, Date end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    public Date getEndDate() {
        return end;
    }

    public String getName() {
        return name;
    }

    public Date getStartDate() {
        return start;
    }

    public void setEndDate(Date end) {
        this.end = end;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(Date start) {
        this.start = start;
    }
}
