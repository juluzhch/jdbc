package jdbc;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class TestDb {

    @Test
    public void TestResourceFile(){
        Properties properties = new Properties();
        try {
            InputStream in = this.getClass().getResourceAsStream("/application.properties");
            InputStreamReader inputStreamReader =new InputStreamReader(in, "UTF-8");
            properties.load(inputStreamReader);
            String value = properties.getProperty("mysql.database.connection");
            System.out.println(value);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



}
