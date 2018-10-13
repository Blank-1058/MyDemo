package jdbcDemo;

import util.dataBaseUtil.JDBCUtil;

import java.sql.Connection;
import java.util.logging.Logger;

public class JDBCDemo {
    private Logger log=Logger.getLogger(JDBCDemo.class.getName());
    public static void main(String[] arg0){
        Connection connection= JDBCUtil.getConnection();
        JDBCUtil.closeConnection(connection,null,null);
    }
}
