package action_cast.config;

import action_cast.controller.Controller;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.*;
import java.net.URISyntaxException;
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

    public static ApplicationConfiguration load() {
        //System.out.println("loading file: " + filename);
        ApplicationConfiguration configuration = null;

        File file = new File(getConfigurationFilePath());
        if (!file.exists()) {
            ClassLoader classLoader = ApplicationConfiguration.class.getClassLoader();
            InputStream initialStream = classLoader.getResourceAsStream("config.xml");
            try {
                if (file.createNewFile()) {
                    byte[] buffer = new byte[initialStream.available()];
                    initialStream.read(buffer);
                    OutputStream outStream = new FileOutputStream(file);
                    outStream.write(buffer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ApplicationConfiguration.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            configuration = (ApplicationConfiguration) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        instance = configuration;
        return configuration;
    }

    public static ApplicationConfiguration getInstance() {
        return instance;
    }

    public static String getConfigurationFilePath() {
        String path = "";
        try {
            path = Controller.class.getProtectionDomain().getCodeSource().getLocation().toURI().resolve("..").getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return path + "/config.xml";
    }

    public void save(String filename) throws JAXBException {
        File file = new File(filename);

        JAXBContext jaxbContext = JAXBContext.newInstance(ApplicationConfiguration.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(this, file);
    }

    public void addWindowConfiguration(WindowConfiguration winConfig) {
        if (!getWindowConfigurationMap().containsKey(winConfig.getKey())) {
            windows.add(winConfig);
        } else {
            windows.remove(getWindowConfiguration(winConfig.getKey()));
            windows.add(winConfig);
            getWindowConfigurationMap().put(winConfig.getKey(), winConfig);
        }
        getWindowConfigurationMap().put(winConfig.getKey(), winConfig);
    }

    public WindowConfiguration getWindowConfiguration(String key) {
        WindowConfiguration result = null;
        if (getWindowConfigurationMap().containsKey(key)) {
            result = getWindowConfigurationMap().get(key);
        }
        return result;
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
