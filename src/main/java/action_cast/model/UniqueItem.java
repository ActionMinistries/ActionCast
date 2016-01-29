package action_cast.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;

/**
 * Created by bmichaud on 10/12/2015.
 */
public abstract class UniqueItem {
    protected int id;

    @XmlAttribute
    @XmlID
    protected String getId() {
        return getClass().getSimpleName() + "-" + Integer.toString(getIndex());
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id.substring(id.lastIndexOf("-")+1));
    }

    public int getIndex() {
        return id;
    }


}
