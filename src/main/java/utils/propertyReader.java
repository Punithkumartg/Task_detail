package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class propertyReader {
    private Properties properties;
    private Properties scanprop;

    public Properties readConfigProperty()
    {
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
    public Properties readscansproperties()
    {
        scanprop =new Properties();
        FileInputStream fileinputStreams = null;
        try
        {
            fileinputStreams= new FileInputStream("./src/main/resources/testdata/scans.properties");
            scanprop.load(fileinputStreams);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);} catch (IOException e) {
            throw new RuntimeException(e);
        }
        return scanprop;
    }

}
