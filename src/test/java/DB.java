import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by 张震文 on 2017/4/1.
 */
public class DB {

    /**
     * 获取连接
     *
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/study?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
        //String url = "jdbc:mysql://192.168.1.210:3306/iworker_report?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
        //String url = "jdbc:mysql://192.168.1.210:3306/iworker_global?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
        //String url = "jdbc:mysql://192.168.1.210:3306/iworker_report_etl?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
       /* String username = "iworker";
        String password = "123456";*/
        String username = "root";
        String password = "";
        Connection conn = DriverManager.getConnection(url, username,password);
        return conn;
    }
}
