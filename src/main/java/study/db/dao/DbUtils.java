package study.db.dao;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

//数据库的连接建立
public class DbUtils {

    public  enum ConnectonType {
        DirverManager,
        DuriDataSource;
    }
    public static ConnectonType connectonType =ConnectonType.DirverManager;

    public static  Connection getConnection() throws SQLException {
        if (connectonType==ConnectonType.DuriDataSource){
            return getConnectionByDruiDataSource();
        }else{
            return getConnectionByDirverManager();
        }
    }

    private static Connection getConnectionByDirverManager() throws SQLException {
        ConnectionInfo connectionInfo=getConnectionInfo();
        return java.sql.DriverManager.getConnection(connectionInfo.connectionUrl,connectionInfo.user,connectionInfo.password);
    }

    private static Connection getConnectionByDruiDataSource() throws SQLException {
        DruidDataSource ds = new DruidDataSource();
        ConnectionInfo connectionInfo=getConnectionInfo();
//        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUsername(connectionInfo.user);
        ds.setPassword(connectionInfo.password);
        ds.setUrl(connectionInfo.connectionUrl);
//        ds.setDriverClassName("com.mysql.jdbc.Driver");
        return ds.getConnection();
    }

    private static class  ConnectionInfo{
        String user;
        String password;
        String connectionUrl;
    }
    private static ConnectionInfo getConnectionInfo(){
        Properties properties=getProperties();
        String connectionUrl = properties.getProperty("mysql.database.connection");
        String user=properties.getProperty("mysql.database.connection.user");
        String password=properties.getProperty("mysql.database.connection.password");
        ConnectionInfo connectionInfo= new  ConnectionInfo();
        connectionInfo.connectionUrl=connectionUrl;
        connectionInfo.user=user;
        connectionInfo.password=password;
        return connectionInfo;
    }

    private static Properties getProperties(){
        Properties properties = new Properties();
        try {
            InputStream in = DbUtils.class.getResourceAsStream("/application.properties");
            InputStreamReader inputStreamReader =new InputStreamReader(in, "UTF-8");
            properties.load(inputStreamReader);

//            String value = properties.getProperty("mysql.database.connection");
//            System.out.println(value);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return properties;
    }
}
