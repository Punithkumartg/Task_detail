package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class propertyReader {
    private Properties properties;

    public Properties readConfigProperty() {
        properties = new Properties();
        FileInputStream fileinputStream = null;
        try {
            fileinputStream = new FileInputStream("src/main/resources/configfiles/config.properties");
            properties.load(fileinputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return properties;
    }

    public Properties readProductProperty() {
        properties = new Properties();
        FileInputStream fileinputStream = null;
        try {
            fileinputStream = new FileInputStream("src/main/resources/testData/products.properties");
            properties.load(fileinputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }
}
