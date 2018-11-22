//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.base.sql.helper;

import com.base.sql.util.MapJavaAndDBColumeType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class TableHelper {
    public TableHelper() {
    }

    public static String generationJavaClassFromTable(Connection conn, String tableName, boolean isAllColumeUseString) throws SQLException {
        StringBuffer sb = new StringBuffer();
        ResultSetMetaData rsmd = null;
        String sql = "select * from " + tableName;
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rsmd = rs.getMetaData();
        int count = rsmd.getColumnCount();
        String className = FiledHelper.parseDBFieldToJavaField(tableName);
        sb.append("public class " + className.substring(0, 1).toUpperCase() + className.substring(1) + " {");
        sb.append("\n");
        sb.append("\n");

        for(int i = 1; i <= count; ++i) {
            String columnName = rsmd.getColumnName(i);
            String type = "";
            if (isAllColumeUseString) {
                type = "String";
            } else {
                String columnTypeName = rsmd.getColumnTypeName(i);
                type = MapJavaAndDBColumeType.getJavaFieldType(columnTypeName);
            }

            sb.append("\tprivate " + type + " " + FiledHelper.parseDBFieldToJavaField(columnName) + ";");
            sb.append("\n");
            sb.append("\n");
        }

        sb.append("}");
        return sb.toString();
    }
}
