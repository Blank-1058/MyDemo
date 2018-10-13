package util.dataBaseUtil;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Logger;

public class JDBCUtil {
    private Connection connection=null;
    private Statement statement=null;
    private static String url="";
    private static String userName="";
    private static String passWord="";
    private static Logger log=null;
    static{
        log=Logger.getLogger(JDBCUtil.class.getName());
        Properties prop=new Properties();
        try{
            InputStream is= JDBCUtil.class.getClassLoader().getResourceAsStream("conf/dataBaseConf/jdbc.properties");
            prop.load(is);
            // 注册驱动
            String driverClass=prop.getProperty("jdbc.oracleDriver");
            Class.forName(driverClass);
            // 获取数据连接
            url=prop.getProperty("jdbc.oracleUrl");
            userName=prop.getProperty("jdbc.orcleUser");
            passWord=prop.getProperty("jdbc.oraclePassword");
        }catch (Exception e){
            e.printStackTrace();
            log.info(e.getMessage());
        }
    }

    /**
     * 连接数据库
     * @return
     */
    public static Connection getConnection(){
        Connection connection=null;
        try{
            log.info("连接数据库:"+url);
            connection= DriverManager.getConnection(url,userName,passWord);
            log.info("连接成功");
        }catch (Exception e){
            e.printStackTrace();
            log.info(e.getMessage());
            log.info("连接失败");
        }
        return connection;
    }

    /**
     * 关闭数据库连接
     * @param connection
     * @param statement
     * @param resultSet
     */
    public static void closeConnection(Connection connection,Statement statement,ResultSet resultSet){
        log.info("关闭数据库连接");
        if(resultSet!=null){
            try{
                resultSet.close();
                log.info("关闭resultSet成功");
            }catch (Exception e){
                e.printStackTrace();
                log.info("关闭resultSet失败");
            }
        }
        if(statement!=null){
            try{
                statement.close();
                log.info("关闭statement成功");
            }catch (Exception e){
                e.printStackTrace();
                log.info("关闭statement失败");
            }
        }
        if(connection!=null){
            try{
                connection.close();
                log.info("关闭connection成功");
            }catch (Exception e){
                e.printStackTrace();
                log.info("关闭connection失败");
            }
        }
    }
}
