package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestPropertiesProvider {
    private static final String API_PROPERTIES = "api.properties";
    private static Properties autProperties = new Properties();
    
    public static String getPropertyByName(String name) {
        InputStream in = TestPropertiesProvider.class.getResourceAsStream(API_PROPERTIES);
        try {
            autProperties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return autProperties.getProperty(name);
    }
}
