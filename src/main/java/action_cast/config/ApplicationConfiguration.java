package action_cast.config;

import action_cast.model.DataModel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bmichaud on 9/21/2016.
 */
@XmlRootElement
public class ApplicationConfiguration {

    public static ApplicationConfiguration instance;

    @XmlElementWrapper
    List<WindowConfiguration> windows = new ArrayList<>();

    @XmlTransient
    Map<String, WindowConfiguration> windowConfigurationMap;

    public static ApplicationConfiguration load(String filename) {
        System.out.println("loading file: " + filename);
        ApplicationConfiguration configuration = null;
        File file = new File(filename);
        if (file.exists()) {
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(ApplicationConfiguration.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                configuration = (ApplicationConfiguration) jaxbUnmarshaller.unmarshal(file);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        } else {
            configuration = new ApplicationConfiguration();
        }
        instance = configuration;
        return configuration;
    }

    public static ApplicationConfiguration getInstance() {
        return instance;
    }

    public void save(String filename) throws JAXBException {
        File file = new File(filename);

        JAXBContext jaxbContext = JAXBContext.newInstance(ApplicationConfiguration.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(this, file);
    }

    private Map<String, WindowConfiguration> getWindowConfigurationMap() {
        if (windowConfigurationMap == null) {
            windowConfigurationMap = new HashMap<>();
            for (WindowConfiguration winConfig: windows) {
                windowConfigurationMap.put(winConfig.getKey(), winConfig);
            }
        }
        return windowConfigurationMap;
    }
}
