//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.base.sql.hibernate.helper;


import com.base.sql.hibernate.annotation.Column;
import com.base.sql.hibernate.annotation.Id;
import com.base.sql.hibernate.annotation.Table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HibernateHelper {
    public HibernateHelper() {
    }

    public static String generateInsertSql(Object obj) {
        try {
            if (obj != null) {
                Class<?> objClass = obj.getClass();
                if (HibernateService.validate(objClass)) {
                    Table tableAnnotation = (Table)objClass.getAnnotation(Table.class);
                    String tableName = tableAnnotation.tableName();
                    Map<String, Object> param = HibernateService.getFieldParam(obj);
                    if (param.size() > 0) {
                        return "insert into " + tableName + HibernateService.getDbColumnAndValue(param);
                    }
                }
            }

            return "";
        } catch (Exception var5) {
            var5.printStackTrace();
            throw new RuntimeException(var5);
        }
    }

    public static <T> String generateBatchInsertSql(List<T> tList) {
        try {
            if (tList != null && tList.size() > 0) {
                T t = tList.get(0);
                Class<?> tClass = t.getClass();
                if (HibernateService.validate(tClass)) {
                    List<Map<String, Object>> params = new ArrayList();
                    Iterator var4 = tList.iterator();

                    while(var4.hasNext()) {
                        T tObj = (T) var4.next();
                        Map<String, Object> param = HibernateService.getFieldParam(tObj);
                        if (param.size() > 0) {
                            params.add(param);
                        }
                    }

                    if (params.size() > 0) {
                        Table tableAnnotation = (Table)tClass.getAnnotation(Table.class);
                        String tableName = tableAnnotation.tableName();
                        return "insert into " + tableName + HibernateService.getDbBatchColumnAndValue(params);
                    }
                }
            }

            return "";
        } catch (Exception var7) {
            var7.printStackTrace();
            throw new RuntimeException(var7);
        }
    }

    public static String generateUpdateSql(Object obj) {
        try {
            if (obj != null) {
                Class<?> objClass = obj.getClass();
                if (HibernateService.validate(objClass)) {
                    Table tableAnnotation = (Table)objClass.getAnnotation(Table.class);
                    String tableName = tableAnnotation.tableName();
                    Set<Field> idFields = new HashSet();
                    getIdField(objClass, idFields);
                    if (idFields.size() == 0) {
                        throw new RuntimeException("生成update语句并未在实体类中加入@Id注解，无法确定主键字段");
                    }

                    Map<String, Object> param = HibernateService.getFieldParam(obj);
                    if (param.size() > 0) {
                        String updateSql = "update " + tableName + " set " + HibernateService.getDbUpdateColumnAndValue(idFields, param) + " where ";
                        int i = 0;

                        for(Iterator var8 = idFields.iterator(); var8.hasNext(); ++i) {
                            Field idField = (Field)var8.next();
                            Column column = (Column)idField.getAnnotation(Column.class);
                            Object fieldValue = HibernateService.getFieldValue(obj, idField);
                            if (!"''".equals(fieldValue)) {
                                fieldValue = "'" + fieldValue + "'";
                            }

                            if (i == idFields.size() - 1) {
                                updateSql = updateSql + HibernateService.dbField(idField.getName(), column) + "= " + fieldValue;
                            } else {
                                updateSql = updateSql + HibernateService.dbField(idField.getName(), column) + "= " + fieldValue + " and ";
                            }
                        }

                        return updateSql;
                    }
                }
            }

            return "";
        } catch (Exception var12) {
            var12.printStackTrace();
            throw new RuntimeException(var12);
        }
    }

    private static Field getIdField(Class<?> objClass, Set<Field> idFields) {
        Field[] declaredFields = objClass.getDeclaredFields();
        Field[] var3 = declaredFields;
        int var4 = declaredFields.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Field field = var3[var5];
            Id idAnnotation = (Id)field.getAnnotation(Id.class);
            if (idAnnotation != null) {
                idFields.add(field);
            }
        }

        Class<?> superclass = objClass.getSuperclass();
        if (superclass != null) {
            return getIdField(superclass, idFields);
        } else {
            return null;
        }
    }

    public static <T> List<String> generateBatchUpdateSql(List<T> tList) {
        List<String> list = new ArrayList();
        if (tList != null && tList.size() > 0) {
            Iterator var2 = tList.iterator();

            while(var2.hasNext()) {
                T t = (T) var2.next();
                list.add(generateUpdateSql(t));
            }
        }

        return list;
    }

    public static String generateSeleteSql(Object obj) {
        try {
            if (obj != null) {
                Class<?> objClass = obj.getClass();
                if (HibernateService.validate(objClass)) {
                    Table tableAnnotation = (Table)objClass.getAnnotation(Table.class);
                    String selectSql = tableAnnotation.selectSql();
                    if (selectSql != null && !"".equals(selectSql)) {
                        return selectSql;
                    }

                    return HibernateService.getSelectSql(objClass, tableAnnotation);
                }
            }

            return "";
        } catch (Exception var4) {
            var4.printStackTrace();
            throw new RuntimeException(var4);
        }
    }
}
