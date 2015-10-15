package action_cast.data_store;

import action_cast.model.DataModel;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

/**
 * Created by bmichaud on 10/6/2015.
 */
public class DataStore {

    private DataModel model = null;

    private String filename;

    public DataStore(String filename) {
        this.filename = filename;
    }

    public DataStore(DataModel model) {
        this.model = model;
        filename = "file.xml";
    }

    public void save() throws JAXBException {
        File file = new File(filename);

        JAXBContext jaxbContext = JAXBContext.newInstance(DataModel.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(model, file);
    }

    public void load() throws JAXBException, SAXException {
        File file = new File(filename);
        if (file.exists()) {
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new File("schema1.xsd"));

            JAXBContext jaxbContext = JAXBContext.newInstance(DataModel.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            jaxbUnmarshaller.setSchema(schema);
            jaxbUnmarshaller.setEventHandler(new ValidationEventHandler());

            model = (DataModel) jaxbUnmarshaller.unmarshal(file);
        }
        else {
            model = new DataModel();
        }
    }

    public DataModel getModel() {
        return model;
    }
}
