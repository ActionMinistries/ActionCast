package action_cast.data_store;

import action_cast.model.DataModel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Created by bmichaud on 10/6/2015.
 */
public class DataStore {

    private final DataModel model;

    public DataStore(DataModel model) {
        this.model = model;
    }

    public void save() {
        try {

            File file = new File("file.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(DataModel.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(model, file);
            jaxbMarshaller.marshal(model, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
