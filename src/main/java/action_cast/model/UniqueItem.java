package action_cast.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import java.util.UUID;

/**
 * Created by bmichaud on 10/12/2015.
 */
public abstract class UniqueItem {
    @XmlAttribute
    @XmlID
    protected String id;

    public UniqueItem() {
        id =  "I-" + UUID.randomUUID().toString();
    }
}
