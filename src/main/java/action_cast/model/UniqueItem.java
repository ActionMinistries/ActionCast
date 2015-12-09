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
    private String getId() {
        return getClass().getSimpleName() + "-" + Integer.toString(getIndex());
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id.substring(getClass().getSimpleName().length()+1));
    }

    public abstract int getIndex();


}
