//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.base.sql.util;


import com.base.reflect.ReflectUtil;
import com.base.sql.annotation.DBStringValue;
import com.base.sql.annotation.Ignore;
import com.base.sql.helper.FiledHelper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SqlUtil {
    public SqlUtil() {
    }

    public static <T> Object[] getInsertSqlValues(StringBuffer s, Field[] declaredFields, Class<?> clazz, T t, boolean isPrepare, boolean idIsAutoIncrement, boolean insertValueWhenNull) throws Exception {
        int length = declaredFields.length;
        List<Object> args = new ArrayList();
        s.append(" ( ");

        for(int i = 0; i < length; ++i) {
            Field field = declaredFields[i];
            Ignore ignore = (Ignore)field.getAnnotation(Ignore.class);
            if (ignore == null) {
                Class<?> type = field.getType();
                String name = field.getName();
                if (!"id".equals(name) || !idIsAutoIncrement) {
                    Object value = getFieldValue(name, clazz, t);
                    if (insertValueWhenNull || !Objects.isNull(value) && (type != String.class || !"".equals(value.toString()))) {
                        args.add(value);
                        if (i == length - 1) {
                            if (isPrepare) {
                                s.append("?");
                            } else {
                                s.append(value);
                            }
                        } else if (isPrepare) {
                            s.append("?,");
                        } else {
                            s.append(value + ",");
                        }
                    }
                }
            }
        }

        verifySql(s);
        s.append(" ) ");
        return args.toArray();
    }

    private static <T> Object getFieldValue(String fieldName, Class<?> clazz, T t) throws Exception {
        String getMethodName = ReflectUtil.getGetMethodName(fieldName);
        Method getMethod = clazz.getMethod(getMethodName);
        Object invoke = getMethod.invoke(t);
        Field field = clazz.getDeclaredField(fieldName);
        DBStringValue dbStringValue = (DBStringValue)field.getAnnotation(DBStringValue.class);
        if (dbStringValue != null && invoke != null) {
            invoke = "'" + invoke + "'";
        }

        return invoke;
    }

    public static <T> String getInsertSqlStart(Field[] declaredFields, String tableName, boolean idIsAutoIncrement, boolean insertValueWhenNull, Class<?> clazz, T t) throws Exception {
        int length = declaredFields.length;
        StringBuffer s = new StringBuffer();
        s.append("insert into " + tableName + "(");

        for(int i = 0; i < length; ++i) {
            Field field = declaredFields[i];
            Ignore ignore = (Ignore)field.getAnnotation(Ignore.class);
            if (ignore == null) {
                String fieldName = field.getName();
                if (!"id".equals(fieldName) || !idIsAutoIncrement) {
                    if (!insertValueWhenNull) {
                        Object value = getFieldValue(fieldName, clazz, t);
                        if (Objects.isNull(value)) {
                            continue;
                        }

                        Class<?> type = field.getType();
                        if (type == String.class && "".equals(value.toString())) {
                            continue;
                        }
                    }

                    if (i == length - 1) {
                        s.append("`" + FiledHelper.parseJavaFieldToDBField(fieldName) + "`");
                    } else {
                        s.append("`" + FiledHelper.parseJavaFieldToDBField(fieldName) + "`").append(",");
                    }
                }
            }
        }

        verifySql(s);
        s.append(")");
        return s.toString();
    }

    public static void verifySql(StringBuffer s) {
        int i = s.lastIndexOf(",");
        if (i == s.length() - 1) {
            s.deleteCharAt(i);
        }

    }

    public static <T> Object[] getUpdateSql(StringBuffer s, Field[] declaredFields, String tableName, boolean updateValueWhenNull, Class<?> clazz, T t, List<String> whereField) throws Exception {
        int length = declaredFields.length;
        s.append("update " + tableName + " set ");
        List<Object> args = new ArrayList();

        int i;
        for(i = 0; i < length; ++i) {
            Field field = declaredFields[i];
            String fieldName = field.getName();
            if (!whereField.contains(fieldName)) {
                Ignore ignore = (Ignore)field.getAnnotation(Ignore.class);
                if (ignore == null) {
                    Object value = getFieldValue(fieldName, clazz, t);
                    if (!updateValueWhenNull) {
                        if (Objects.isNull(value)) {
                            continue;
                        }

                        Class<?> type = field.getType();
                        if (type == String.class && "".equals(value.toString())) {
                            continue;
                        }
                    }

                    args.add(value);
                    if (i == length - 1) {
                        s.append(FiledHelper.parseJavaFieldToDBField(fieldName) + "=?");
                    } else {
                        s.append(FiledHelper.parseJavaFieldToDBField(fieldName) + "=?").append(",");
                    }
                }
            }
        }

        verifySql(s);
        s.append(" where ");

        for(i = 0; i < whereField.size(); ++i) {
            String where = (String)whereField.get(i);
            if (i == whereField.size() - 1) {
                s.append(FiledHelper.parseJavaFieldToDBField(where) + "=?");
            } else {
                s.append(FiledHelper.parseJavaFieldToDBField(where) + "=?").append(" and ");
            }

            args.add(getFieldValue(where, clazz, t));
        }

        return args.toArray();
    }

    public static <T> String getUpdateSql(Field[] declaredFields, String tableName, boolean updateValueWhenNull, Class<?> clazz, T t, List<String> whereField) throws Exception {
        StringBuffer sql = new StringBuffer();
        int length = declaredFields.length;
        sql.append("update " + tableName + " set ");

        int i;
        for(i = 0; i < length; ++i) {
            Field field = declaredFields[i];
            String fieldName = field.getName();
            Ignore ignore = (Ignore)field.getAnnotation(Ignore.class);
            if (ignore == null && !whereField.contains(fieldName)) {
                Object value = getFieldValue(fieldName, clazz, t);
                if (!updateValueWhenNull) {
                    if (Objects.isNull(value)) {
                        continue;
                    }

                    Class<?> type = field.getType();
                    if (type == String.class && "".equals(value.toString())) {
                        continue;
                    }
                }

                if (i == length - 1) {
                    sql.append("`" + FiledHelper.parseJavaFieldToDBField(fieldName) + "`" + "=" + value);
                } else {
                    sql.append("`" + FiledHelper.parseJavaFieldToDBField(fieldName) + "`" + "=" + value).append(",");
                }
            }
        }

        verifySql(sql);
        sql.append(" where ");

        for(i = 0; i < whereField.size(); ++i) {
            String where = (String)whereField.get(i);
            Object value = getFieldValue(where, clazz, t);
            if (i == whereField.size() - 1) {
                sql.append("`" + FiledHelper.parseJavaFieldToDBField(where) + "`" + "=" + value);
            } else {
                sql.append("`" + FiledHelper.parseJavaFieldToDBField(where) + "`" + "=" + value).append(" and ");
            }
        }

        return sql.toString();
    }
}
