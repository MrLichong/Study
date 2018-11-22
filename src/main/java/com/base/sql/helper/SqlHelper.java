//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.base.sql.helper;


import com.base.sql.util.PrepareSqlAndArgs;
import com.base.sql.util.SqlUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SqlHelper {
    public SqlHelper() {
    }

    public static void main(String[] args) throws Exception {

    }

    public static <T> String generateInsertSql(String tableName, T t, boolean idIsAutoIncrement, boolean insertValueWhenNull) throws Exception {
        Class<?> clazz = t.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        int length = declaredFields.length;
        if (length > 0) {
            StringBuffer s = new StringBuffer();
            s.append(SqlUtil.getInsertSqlStart(declaredFields, tableName, idIsAutoIncrement, insertValueWhenNull, clazz, t));
            s.append(" values ");
            SqlUtil.getInsertSqlValues(s, declaredFields, clazz, t, false, idIsAutoIncrement, insertValueWhenNull);
            return s.toString();
        } else {
            return "这个类连字段都没有,Are You Kidding Me?";
        }
    }

    public static <T> String generateBatchInsertSql(String tableName, List<T> tList, boolean idIsAutoIncrement) throws Exception {
        if (tList != null && tList.size() > 0) {
            T t = tList.get(0);
            Class<?> clazz = t.getClass();
            Field[] declaredFields = clazz.getDeclaredFields();
            int length = declaredFields.length;
            if (length > 0) {
                StringBuffer s = new StringBuffer();
                s.append(SqlUtil.getInsertSqlStart(declaredFields, tableName, idIsAutoIncrement, true, clazz, t));
                s.append(" values ");

                for(int i = 0; i < tList.size(); ++i) {
                    T tObj = tList.get(i);
                    if (i == tList.size() - 1) {
                        SqlUtil.getInsertSqlValues(s, declaredFields, clazz, tObj, false, idIsAutoIncrement, true);
                    } else {
                        SqlUtil.getInsertSqlValues(s, declaredFields, clazz, tObj, false, idIsAutoIncrement, true);
                        s.append(",");
                    }
                }

                return s.toString();
            } else {
                return "这个类连字段都没有,Are You Kidding Me?";
            }
        } else {
            return "";
        }
    }

    public static <T> PrepareSqlAndArgs generatePrepareInsertSql(String tableName, T t, boolean idIsAutoIncrement, boolean insertValueWhenNull) throws Exception {
        Class<?> clazz = t.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        int length = declaredFields.length;
        if (length > 0) {
            PrepareSqlAndArgs sa = new PrepareSqlAndArgs();
            StringBuffer s = new StringBuffer();
            s.append(SqlUtil.getInsertSqlStart(declaredFields, tableName, idIsAutoIncrement, insertValueWhenNull, clazz, t));
            s.append(" values ");
            Object[] args = SqlUtil.getInsertSqlValues(s, declaredFields, clazz, t, true, idIsAutoIncrement, insertValueWhenNull);
            sa.setPrepareSql(s.toString());
            sa.setArgs(args);
            return sa;
        } else {
            return null;
        }
    }

    public static <T> PrepareSqlAndArgs generatePrepareInsertSql(String tableName, List<T> tList, boolean idIsAutoIncrement) throws Exception {
        if (tList != null && tList.size() > 0) {
            T t = tList.get(0);
            Class<?> clazz = t.getClass();
            Field[] declaredFields = clazz.getDeclaredFields();
            int length = declaredFields.length;
            if (length > 0) {
                PrepareSqlAndArgs sa = new PrepareSqlAndArgs();
                StringBuffer s = new StringBuffer();
                s.append(SqlUtil.getInsertSqlStart(declaredFields, tableName, idIsAutoIncrement, true, clazz, t));
                s.append(" values ");
                List<Object> list = new ArrayList();

                for(int i = 0; i < tList.size(); ++i) {
                    T tObj = tList.get(i);
                    if (i == tList.size() - 1) {
                        list.addAll(Arrays.asList(SqlUtil.getInsertSqlValues(s, declaredFields, clazz, tObj, true, idIsAutoIncrement, true)));
                    } else {
                        list.addAll(Arrays.asList(SqlUtil.getInsertSqlValues(s, declaredFields, clazz, tObj, true, idIsAutoIncrement, true)));
                        s.append(",");
                    }
                }

                sa.setPrepareSql(s.toString());
                sa.setArgs(list.toArray());
                return sa;
            }
        }

        return null;
    }

    public static <T> PrepareSqlAndArgs generatePrepareUpdateSql(String tableName, T t, boolean updateValueWhenNull, String... whereField) throws Exception {
        if (whereField.length == 0) {
            throw new RuntimeException("更新语句请传入where条件字段");
        } else {
            Class<?> clazz = t.getClass();
            Field[] declaredFields = clazz.getDeclaredFields();
            int length = declaredFields.length;
            if (length > 0) {
                PrepareSqlAndArgs sa = new PrepareSqlAndArgs();
                StringBuffer s = new StringBuffer();
                Object[] args = SqlUtil.getUpdateSql(s, declaredFields, tableName, updateValueWhenNull, clazz, t, Arrays.asList(whereField));
                sa.setPrepareSql(s.toString());
                sa.setArgs(args);
                return sa;
            } else {
                return null;
            }
        }
    }

    public static <T> String generateUpdateSql(String tableName, T t, boolean updateValueWhenNull, String... whereField) throws Exception {
        if (whereField.length == 0) {
            throw new RuntimeException("更新语句请传入where条件字段");
        } else {
            Class<?> clazz = t.getClass();
            Field[] declaredFields = clazz.getDeclaredFields();
            int length = declaredFields.length;
            if (length > 0) {
                String sql = SqlUtil.getUpdateSql(declaredFields, tableName, updateValueWhenNull, clazz, t, Arrays.asList(whereField));
                return sql;
            } else {
                return null;
            }
        }
    }
}
