package libraries;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author rkasha
 */
public class ReadJavaProperties {
    public static void main(String[] args){
        InputStream inpStream;
        try {
            Properties prop = new Properties();
            inpStream = ReadJavaProperties.class.getClassLoader()
                  .getResourceAsStream("java/src/main/config.properties");
            if(inpStream != null){
            prop.load(inpStream);
            String value = prop.getProperty("abc.test");
            System.out.println(value);
                inpStream.close();
            }
        } catch (IOException ie){
            ie.printStackTrace();
        }
        
    }
}
