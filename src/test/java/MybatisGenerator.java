import com.base.model.ModelHelper;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * 模拟Mybatis逆向工程
 * Created by 张震文 on 2017/4/1.
 */
public class MybatisGenerator {

    public static void main(String[] args) throws Exception {
        Connection connection = DB.getConnection();
        List<String> tables = new ArrayList<>();
        tables.add("project");
        //List<String> tables = new ArrayList<>();
        //tables.add("sns_posts_pr_group_users");
        ModelHelper.createModelFromOracleDB(connection, tables, "E:/Java/testModel");

    }
}
