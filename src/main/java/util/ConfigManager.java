package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    /* Get actual class name to be printed on */
    static Logger log = LogManager.getLogger(ConfigManager.class.getName());

    Properties prop = new Properties();
    String fileNameDefault = "config.properties";

    public void load(String fileName) {

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(fileName)){
            prop.load(is);
        } catch (FileNotFoundException ex) {
            log.error("impossible de trouver le fichier de congiguration", ex);

        } catch (IOException exIO) {
            log.error("impossible de charger le fichier de congiguration", exIO);
        }
    }

    public String get(String cle){

        return  prop.getProperty(cle);
    }
}
