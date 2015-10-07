package action_cast.data_store;

import action_cast.model.DataModel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by bmichaud on 10/6/2015.
 */
public class DataStore {

    private DataModel model = null;

    public DataStore() {

    }

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

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    public void load() {
        try {

            File file = new File("file.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(DataModel.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            model = (DataModel)jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public DataModel getModel() {
        return model;
    }
}
