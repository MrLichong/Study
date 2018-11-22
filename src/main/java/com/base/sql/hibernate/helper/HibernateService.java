//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.base.sql.hibernate.helper;


import com.base.reflect.ReflectUtil;
import com.base.sql.helper.FiledHelper;
import com.base.sql.hibernate.annotation.Column;
import com.base.sql.hibernate.annotation.Id;
import com.base.sql.hibernate.annotation.Table;
import com.base.sql.hibernate.annotation.TinyInt;
import com.base.sql.util.SqlUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.activation.UnsupportedDataTypeException;

public class HibernateService {
    public HibernateService() {
    }

    public static Object getFieldValue(Object obj, Field idField) throws Exception {
        String fieldName = idField.getName();
        Class<?> objClass = obj.getClass();
        String getMethodName = ReflectUtil.getGetMethodName(fieldName);
        Method getMethod = objClass.getMethod(getMethodName);
        Object fieldValue = getMethod.invoke(obj);
        return fieldValue;
    }

    public static String getDbUpdateColumnAndValue(Set<Field> idFields, Map<String, Object> param) {
        StringBuffer sb = new StringBuffer();
        Set<String> idFieldNames = new HashSet();
        Iterator var4 = idFields.iterator();

        while(var4.hasNext()) {
            Field idField = (Field)var4.next();
            idFieldNames.add(dbField(idField.getName(), (Column)idField.getAnnotation(Column.class)));
        }

        var4 = param.entrySet().iterator();

        while(var4.hasNext()) {
            Entry<String, Object> entry = (Entry)var4.next();
            String key = (String)entry.getKey();
            if (!idFieldNames.contains(key)) {
                Object value = entry.getValue();
                sb.append(key).append("=").append(value).append(",");
            }
        }

        SqlUtil.verifySql(sb);
        return sb.toString();
    }

    public static String getDbBatchColumnAndValue(List<Map<String, Object>> params) {
        StringBuffer buffer = new StringBuffer();
        List<String> keyList = new ArrayList();
        Map<String, Object> keyMap = (Map)params.get(0);
        Iterator var4 = keyMap.entrySet().iterator();

        while(var4.hasNext()) {
            Entry<String, Object> entry = (Entry)var4.next();
            keyList.add(entry.getKey());
        }

        List<List<Object>> valueLists = new ArrayList();
        Iterator var11 = params.iterator();

        while(var11.hasNext()) {
            Map<String, Object> param = (Map)var11.next();
            List<Object> valueList = new ArrayList();
            Iterator var8 = param.entrySet().iterator();

            while(var8.hasNext()) {
                Entry<String, Object> entry = (Entry)var8.next();
                valueList.add(entry.getValue());
            }

            valueLists.add(valueList);
        }

        buffer.append("(").append((String)keyList.stream().collect(Collectors.joining(","))).append(")");
        buffer.append(" values ");
        var11 = valueLists.iterator();

        while(var11.hasNext()) {
            List<Object> valueList = (List)var11.next();
            buffer.append("(").append((String)valueList.stream().map((e) -> {
                return e + "";
            }).collect(Collectors.joining(","))).append(")").append(",");
        }

        SqlUtil.verifySql(buffer);
        return buffer.toString();
    }

    public static String getDbColumnAndValue(Map<String, Object> param) {
        StringBuffer buffer = new StringBuffer();
        List<String> keyList = new ArrayList();
        List<Object> valueList = new ArrayList();
        Iterator var4 = param.entrySet().iterator();

        while(var4.hasNext()) {
            Entry<String, Object> entry = (Entry)var4.next();
            keyList.add(entry.getKey());
            valueList.add(entry.getValue());
        }

        buffer.append("(").append((String)keyList.stream().collect(Collectors.joining(","))).append(")");
        buffer.append(" values ");
        buffer.append("(").append((String)valueList.stream().map((e) -> {
            return e + "";
        }).collect(Collectors.joining(","))).append(")");
        return buffer.toString();
    }

    public static Map<String, Object> getFieldParam(Object obj) throws Exception {
        Map<String, Object> param = new LinkedHashMap();
        Class<?> objClass = obj.getClass();
        getSelfAndParentParam(param, objClass, obj);
        return param;
    }

    private static void getSelfAndParentParam(Map<String, Object> param, Class<?> objClass, Object obj) throws Exception {
        Field[] declaredFields = objClass.getDeclaredFields();
        Field[] var4 = declaredFields;
        int var5 = declaredFields.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Field field = var4[var6];
            Class<?> type = field.getType();
            Column columnAnnotation = (Column)field.getAnnotation(Column.class);
            Id idAnnotation = (Id)field.getAnnotation(Id.class);
            if (columnAnnotation != null) {
                if (type != Integer.TYPE && type != Integer.class && type != Long.TYPE && type != Long.class && type != Double.TYPE && type != Double.class && type != String.class && type != BigDecimal.class) {
                    throw new UnsupportedDataTypeException("不支持生成sql的数据类型：" + type);
                }

                Table table = (Table)obj.getClass().getAnnotation(Table.class);
                String fieldName = field.getName();
                if (idAnnotation == null || !table.idAutoIncrement()) {
                    String getMethodName = ReflectUtil.getGetMethodName(fieldName);
                    Method getMethod = objClass.getMethod(getMethodName);
                    Object fieldValue = getMethod.invoke(obj);
                    String setMethodName = ReflectUtil.getSetMethodName(fieldName);
                    if (fieldValue == null) {
                        if (table.generateDefaultValue()) {
                            String defaultValue = columnAnnotation.defaultValue();
                            Method method;
                            if (type == Integer.TYPE) {
                                method = objClass.getMethod(setMethodName, Integer.TYPE);
                                fillIntDefaultValue(obj, columnAnnotation, defaultValue, method, param, fieldName);
                            } else if (type == Integer.class) {
                                method = objClass.getMethod(setMethodName, Integer.class);
                                fillIntDefaultValue(obj, columnAnnotation, defaultValue, method, param, fieldName);
                            } else if (type == Long.TYPE) {
                                method = objClass.getMethod(setMethodName, Long.TYPE);
                                fillLongDefaultValue(obj, columnAnnotation, defaultValue, method, param, fieldName);
                            } else if (type == Long.class) {
                                method = objClass.getMethod(setMethodName, Long.class);
                                fillLongDefaultValue(obj, columnAnnotation, defaultValue, method, param, fieldName);
                            } else if (type == Double.TYPE) {
                                method = objClass.getMethod(setMethodName, Double.TYPE);
                                fillDoubleDefaultValue(obj, columnAnnotation, defaultValue, method, param, fieldName);
                            } else if (type == Double.class) {
                                method = objClass.getMethod(setMethodName, Double.class);
                                fillDoubleDefaultValue(obj, columnAnnotation, defaultValue, method, param, fieldName);
                            } else if (type == String.class) {
                                method = objClass.getMethod(setMethodName, String.class);
                                fillStringDefaultValue(obj, columnAnnotation, defaultValue, method, param, fieldName);
                            }
                        } else {
                            param.put(dbField(fieldName, columnAnnotation), fieldValue);
                        }
                    }

                    if (type == BigDecimal.class) {
                        param.put(dbField(fieldName, columnAnnotation), stringValue(((BigDecimal)fieldValue).toPlainString(), columnAnnotation));
                    } else {
                        param.put(dbField(fieldName, columnAnnotation), stringValue(fieldValue, columnAnnotation));
                    }
                }
            }
        }

        Class<?> superclass = objClass.getSuperclass();
        if (superclass != null) {
            getSelfAndParentParam(param, superclass, obj);
        }

    }

    private static void getParentField(List<Field> fieldList, Class<?> objClass) {
        Class<?> superclass = objClass.getSuperclass();
        if (superclass != null) {
            Field[] declaredFields = superclass.getDeclaredFields();
            fieldList.addAll(Arrays.asList(declaredFields));
            getParentField(fieldList, superclass);
        }

    }

    public static Object stringValue(Object fieldValue, Column columnAnnotation) {
        if (fieldValue == null) {
            return null;
        } else {
            if (columnAnnotation.isVarchar()) {
                String value = fieldValue.toString();
                if (value.contains("'")) {
                    value = value.replaceAll("'", "''");
                    fieldValue = value;
                }
            }

            return "'" + fieldValue + "'";
        }
    }

    public static void fillStringDefaultValue(Object obj, Column columnAnnotation, String defaultValue, Method method, Map<String, Object> param, String fieldName) throws InvocationTargetException, IllegalAccessException {
        if (defaultValue != null && !"".equals(defaultValue)) {
            method.invoke(obj, defaultValue);
            param.put(dbField(fieldName, columnAnnotation), stringValue(defaultValue, columnAnnotation));
        } else {
            method.invoke(obj, "''");
            param.put(dbField(fieldName, columnAnnotation), (Object)null);
        }

    }

    public static void fillDoubleDefaultValue(Object obj, Column columnAnnotation, String defaultValue, Method method, Map<String, Object> param, String fieldName) throws InvocationTargetException, IllegalAccessException {
        if (defaultValue != null && !"".equals(defaultValue)) {
            method.invoke(obj, Double.parseDouble(defaultValue));
            param.put(dbField(fieldName, columnAnnotation), Double.parseDouble(defaultValue));
        } else {
            method.invoke(obj, 0.0D);
            param.put(dbField(fieldName, columnAnnotation), 0.0D);
        }

    }

    public static void fillLongDefaultValue(Object obj, Column columnAnnotation, String defaultValue, Method method, Map<String, Object> param, String fieldName) throws InvocationTargetException, IllegalAccessException {
        if (defaultValue != null && !"".equals(defaultValue)) {
            method.invoke(obj, Long.parseLong(defaultValue));
            param.put(dbField(fieldName, columnAnnotation), Long.parseLong(defaultValue));
        } else {
            method.invoke(obj, 0L);
            param.put(dbField(fieldName, columnAnnotation), 0L);
        }

    }

    public static void fillIntDefaultValue(Object obj, Column columnAnnotation, String defaultValue, Method method, Map<String, Object> param, String fieldName) throws Exception {
        if (defaultValue != null && !"".equals(defaultValue)) {
            method.invoke(obj, Integer.parseInt(defaultValue));
            param.put(dbField(fieldName, columnAnnotation), Integer.parseInt(defaultValue));
        } else {
            method.invoke(obj, 0);
            param.put(dbField(fieldName, columnAnnotation), 0);
        }

    }

    public static String dbField(String fieldName, Column columnAnnotation) {
        return "`" + ("".equals(columnAnnotation.name()) ? (columnAnnotation.fieldEqualsColumn() ? fieldName : FiledHelper.parseJavaFieldToDBField(fieldName)) : columnAnnotation.name()) + "`";
    }

    public static boolean validate(Class<?> objClass) {
        Table tableAnnotation = (Table)objClass.getAnnotation(Table.class);
        if (tableAnnotation == null) {
            throw new RuntimeException("实体类需要加上@Table注解");
        } else {
            return true;
        }
    }

    public static String getSelectSql(Class<?> objClass, Table tableAnnotation) throws UnsupportedDataTypeException {
        Field[] fields = objClass.getDeclaredFields();
        if (fields.length > 0) {
            StringBuffer sql = new StringBuffer();
            List<String> fieldList = new ArrayList();
            Field[] var5 = fields;
            int var6 = fields.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                Field field = var5[var7];
                Column columnAnnotation = (Column)field.getAnnotation(Column.class);
                if (columnAnnotation != null) {
                    Class<?> type = field.getType();
                    if (type != Integer.TYPE && type != Integer.class && type != Long.TYPE && type != Long.class && type != Double.TYPE && type != Double.class && type != String.class) {
                        throw new UnsupportedDataTypeException("不支持生成sql的数据类型：" + type);
                    }

                    String fieldName = field.getName();
                    String dbField = dbField(fieldName, columnAnnotation);
                    TinyInt tinyInt = (TinyInt)field.getAnnotation(TinyInt.class);
                    if (tinyInt == null) {
                        fieldList.add(dbField);
                    } else {
                        fieldList.add(dbField + "*1 as " + dbField);
                    }
                }
            }

            if (fieldList.size() == 0) {
                return "";
            } else {
                sql.append("select ");
                sql.append((String)fieldList.stream().collect(Collectors.joining(",")));
                sql.append(" from ").append(tableAnnotation.tableName()).append(" ");
                return sql.toString();
            }
        } else {
            return "";
        }
    }
}
