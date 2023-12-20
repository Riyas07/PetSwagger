package Pet;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {
   private static PropertyManager propertyManager;
   private Properties properties;

    private PropertyManager()

    {
        try
        {
            InputStream inputStream=new FileInputStream("Config/config.properties");
            properties=new Properties();
            properties.load(inputStream);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
    public static PropertyManager getPropertyManagerInstance()
    {
        if (propertyManager ==null)
        {
            return new PropertyManager();
        }
        else
        {
            return propertyManager;
        }
    }
    public String getPetUrl()
    {
        return properties.getProperty("petUrl");
    }
}
