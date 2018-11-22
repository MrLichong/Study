//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.base.model;

import com.base.sql.helper.FiledHelper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.base.constant.BaseConstant.DB_MYSQL;
import static com.base.constant.BaseConstant.DB_ORACLE;

public class MybatisGenerator {


    public MybatisGenerator() {
    }

    private static List<String> getAllTables(Connection connection) throws SQLException {
        List<String> tables = new ArrayList();
        DatabaseMetaData metaData = connection.getMetaData();
        String[] types = new String[]{"TABLE"};
        ResultSet tableRet = metaData.getTables((String)null, (String)null, (String)null, types);

        while(tableRet.next()) {
            tables.add(tableRet.getObject("TABLE_NAME").toString());
        }

        return tables;
    }

    static void generateModelAndMapper(Connection connection, List<String> tables, String modelPath, String daoPath,
                                       boolean addSqlHelper,String dbType) throws Exception {
        if (modelPath != null && !"".equals(modelPath)) {
            createFileOrDirectoryIfNotExist(modelPath);
            generateModel(connection, tables, modelPath, addSqlHelper,dbType);
        }

        if (daoPath != null && !"".equals(daoPath)) {
            createFileOrDirectoryIfNotExist(daoPath);
            generateMapper(connection, tables, daoPath);
        }

    }

    private static void createFileOrDirectoryIfNotExist(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

    }

    private static void generateMapper(Connection connection, List<String> tables, String daoPath) throws Exception {
        generateClass(tables, daoPath);
        generateXml(connection, tables, daoPath);
    }

    private static void generateXml(Connection connection, List<String> tables, String daoPath) throws Exception {
        Iterator var3 = tables.iterator();

        while(var3.hasNext()) {
            String table = (String)var3.next();
            String sql = "select * from " + table + " limit 1";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int count = rsmd.getColumnCount();
            File file = createJavaFile(table + "Mapper", daoPath, ".xml");
            String idName = FiledHelper.parseDBFieldToJavaField(table);
            BufferedWriter buf = new BufferedWriter(new FileWriter(file));
            buf.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
            buf.newLine();
            buf.write("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >");
            buf.newLine();
            buf.write("<mapper namespace=\"\" >");
            buf.newLine();
            buf.newLine();
            buf.write("<resultMap id=\"" + idName + "Map\" type=\"" + idName + "\">");
            buf.newLine();

            for(int i = 1; i <= count; ++i) {
                String columnName = rsmd.getColumnName(i).toLowerCase();
                buf.write("    <result column=\"" + columnName + "\" property=\"" + FiledHelper.parseDBFieldToJavaField(columnName) + "\"></result>");
                buf.newLine();
            }

            buf.write("</resultMap>");
            buf.newLine();
            buf.write("<insert>");
            buf.newLine();
            buf.write("    INSERT INTO " + table + " SET");
            buf.newLine();
            List<String> columnList = new ArrayList();
            List<String> columnValueList = new ArrayList();

            for(int i = 1; i <= count; ++i) {
                String columnName = rsmd.getColumnName(i).toLowerCase();
                String columnValue = "#{item." + FiledHelper.parseDBFieldToJavaField(columnName) + "}";
                columnValueList.add(columnValue);
                columnList.add(columnName);
                if (i != count) {
                    buf.write("    `" + columnName + "` = #{" + FiledHelper.parseDBFieldToJavaField(columnName) + "},");
                    buf.newLine();
                } else {
                    buf.write("    `" + columnName + "` = #{" + FiledHelper.parseDBFieldToJavaField(columnName) + "}");
                    buf.newLine();
                }
            }

            buf.write("</insert>");
            buf.newLine();
            buf.write("select " + list2Str(columnList, ",", "`") + "from " + table);
            buf.newLine();
            buf.write("<insert id=\"save\" parameterType=\"" + idName + "\">");
            buf.newLine();
            buf.write("    INSERT INTO " + table + "(" + list2Str(columnList, ",", "`") + ") VALUES");
            buf.newLine();
            buf.write("    <foreach collection=\"list\" item=\"item\" separator=\",\">");
            buf.newLine();
            buf.write("        (" + list2Str(columnValueList, ",", "") + ")");
            buf.newLine();
            buf.write("    </foreach>");
            buf.newLine();
            buf.write("</insert>");
            buf.newLine();
            buf.write("</mapper>");
            buf.flush();
            buf.close();
        }

    }

    private static void generateClass(List<String> tables, String daoPath) throws Exception {
        Iterator var2 = tables.iterator();

        while(var2.hasNext()) {
            String table = (String)var2.next();
            File file = createJavaFile(table + "Mapper", daoPath, ".java");
            BufferedWriter buf = new BufferedWriter(new FileWriter(file));
            String className = tableName2ClassName(table + "Mapper");
            buf.write("public interface " + className + " {");
            buf.newLine();
            buf.newLine();
            buf.write("}");
            buf.flush();
            buf.close();
        }

    }

    private static void generateModel(Connection connection, List<String> tables, String modelPath, boolean
            addSqlHelper,String dbType) throws Exception {
        Iterator var4 = tables.iterator();

        while(var4.hasNext()) {
            String table = (String)var4.next();
            System.out.println(table);
            File file = createJavaFile(table, modelPath, ".java");
            String sql = "";
            if (DB_MYSQL.equals(dbType)) {
                sql = "select * from " + table + " limit 1";
            } else if (DB_ORACLE.equals(dbType)) {
                sql = "select * from " + table + " where rownum = 1";
            }
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int count = rsmd.getColumnCount();
            BufferedWriter buf = new BufferedWriter(new FileWriter(file));
            if (addSqlHelper) {
                buf.write("import com.iworker.utilbase.sqlutil.hibernate.annotation.Column;");
                buf.newLine();
                buf.write("import com.iworker.utilbase.sqlutil.hibernate.annotation.Id;");
                buf.newLine();
                buf.write("import com.iworker.utilbase.sqlutil.hibernate.annotation.Table;");
                buf.newLine();
                buf.newLine();
                buf.write("@Table(tableName = \"" + table + "\")");
                buf.newLine();
            }

            String className = tableName2ClassName(table);
            buf.write("public class " + className + " {");
            buf.newLine();
            buf.newLine();

            int i;
            String columnName;
            for(i = 1; i <= count; ++i) {
                columnName = rsmd.getColumnName(i).toLowerCase();
                if (addSqlHelper) {
                    if ("id".equalsIgnoreCase(columnName)) {
                        buf.write("    @Id");
                        buf.newLine();
                    }

                    buf.write("    @Column");
                    buf.newLine();
                }

                buf.write("    private String " + FiledHelper.parseDBFieldToJavaField(columnName) + ";");
                buf.newLine();
                buf.newLine();
            }

            for(i = 1; i <= count; ++i) {
                columnName = rsmd.getColumnName(i).toLowerCase();
                String subMethod = tableName2ClassName(columnName);
                String fieldName = FiledHelper.parseDBFieldToJavaField(columnName);
                buf.write("    public String get" + subMethod + "() {");
                buf.newLine();
                buf.write("        return " + fieldName + ";");
                buf.newLine();
                buf.write("    }");
                buf.newLine();
                buf.newLine();
                buf.write("    public void set" + subMethod + "(String " + fieldName + ") {");
                buf.newLine();
                buf.write("        this." + fieldName + " = " + fieldName + ";");
                buf.newLine();
                buf.write("    }");
                buf.newLine();
                buf.newLine();
            }

            buf.write("}");
            buf.flush();
            buf.close();
        }

    }

    private static File createJavaFile(String table, String modelPath, String suffix) throws Exception {
        String className = tableName2ClassName(table);
        File file = new File(modelPath + "\\" + className + suffix);
        if (!file.exists()) {
            file.createNewFile();
        }

        return file;
    }

    private static String tableName2ClassName(String table) {
        String fileName = FiledHelper.parseDBFieldToJavaField(table);
        return fileName.substring(0, 1).toUpperCase() + fileName.substring(1);
    }

    public static String list2Str(List<String> list, String separator, String around) {
        StringBuffer sb = new StringBuffer();
        if (list != null && list.size() > 0) {
            for(int i = 0; i < list.size(); ++i) {
                if (i == list.size() - 1) {
                    sb.append(around + (String)list.get(i) + around);
                } else {
                    sb.append(around + (String)list.get(i) + around).append(separator);
                }
            }
        }

        return sb.toString();
    }
}
