//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.base.model;


import com.base.model.annotation.CopyProperties;
import com.base.reflect.ReflectUtil;
import com.base.sql.helper.FiledHelper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ModelHelper {
    public ModelHelper() {
    }

    public static <T> void set(T t, String fieldName, Object value) {
        if (t != null && fieldName != null && value != null) {
            try {
                String javaKey = FiledHelper.parseDBFieldToJavaField(fieldName.toLowerCase());
                Field[] fields = t.getClass().getDeclaredFields();
                Map<String, List<Field>> fieldGroupByName = (Map)Arrays.stream(fields).collect(Collectors.groupingBy((e) -> {
                    return e.getName();
                }));
                List<Field> filedList = null;
                filedList = (List)fieldGroupByName.get(javaKey);
                if (filedList == null) {
                    filedList = (List)fieldGroupByName.get(fieldName);
                }

                if (filedList != null) {
                    Field field = (Field)filedList.get(0);
                    field.setAccessible(true);
                    Class<?> type = field.getType();
                    value = ModelService.matchTypeValue(value, type);
                    field.set(t, value);
                }
            } catch (Exception var9) {
                var9.printStackTrace();
            }

        }
    }

    public static <T> T parseMap2Obj(Class<T> targetClass, Map<String, Object> map) {
        try {
            T t = targetClass.newInstance();
            if (map != null) {
                Field[] fields = targetClass.getDeclaredFields();
                Map<String, List<Field>> fieldGroupByName = (Map)Arrays.stream(fields).collect(Collectors.groupingBy((e) -> {
                    return e.getName();
                }));
                Set<Entry<String, Object>> entries = map.entrySet();
                Iterator var6 = entries.iterator();

                while(var6.hasNext()) {
                    Entry<String, Object> entry = (Entry)var6.next();
                    String key = (String)entry.getKey();
                    Object sourceValue = entry.getValue();
                    if (key != null && !"".equals(key) && sourceValue != null) {
                        String javaKey = FiledHelper.parseDBFieldToJavaField(key.toLowerCase());
                        List<Field> filedList = null;
                        filedList = (List)fieldGroupByName.get(javaKey);
                        if (filedList == null) {
                            filedList = (List)fieldGroupByName.get(key);
                        }

                        if (filedList != null) {
                            Field field = (Field)filedList.get(0);
                            field.setAccessible(true);
                            Class<?> type = field.getType();
                            Object value = null;
                            if (sourceValue != null) {
                                value = ModelService.matchTypeValue(sourceValue, type);
                            }

                            field.set(t, value);
                        }
                    }
                }
            }

            return t;
        } catch (Exception var15) {
            throw new RuntimeException(var15);
        }
    }

    public static <T> List<T> parseObj(List<Map<String, Object>> queryForList, Class<T> T) {
        List<T> list = new ArrayList();
        if (queryForList != null && queryForList.size() > 0) {
            Iterator var3 = queryForList.iterator();

            while(var3.hasNext()) {
                Map<String, Object> map = (Map)var3.next();
                T order = parseMap2Obj(T, map);
                list.add(order);
            }
        }

        return list;
    }

    public static <T> Map<String, Object> parseJavaBean2PhpMap(T t) {
        if (t == null) {
            return new HashMap();
        } else {
            Map<String, Object> map = new HashMap();
            Class<?> tClass = t.getClass();
            Field[] fields = tClass.getDeclaredFields();
            Field[] var4 = fields;
            int var5 = fields.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                Field field = var4[var6];
                String name = field.getName();
                String mapKey = FiledHelper.parseJavaFieldToDBField(name);
                String getMethodName = ReflectUtil.getGetMethodName(name);

                try {
                    Method getMethod = tClass.getMethod(getMethodName);
                    Object mapValue = getMethod.invoke(t);
                    map.put(mapKey, mapValue);
                } catch (Exception var13) {
                    ;
                }
            }

            return map;
        }
    }

    public static <T> Map<String, Object> parseJavaBean2Map(T t) {
        if (t == null) {
            return new HashMap();
        } else {
            Map<String, Object> map = new HashMap();
            Class<?> tClass = t.getClass();
            Field[] fields = tClass.getDeclaredFields();
            Field[] var4 = fields;
            int var5 = fields.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                Field field = var4[var6];
                String name = field.getName();
                String getMethodName = ReflectUtil.getGetMethodName(name);

                try {
                    Method getMethod = tClass.getMethod(getMethodName);
                    Object mapValue = getMethod.invoke(t);
                    map.put(name, mapValue);
                } catch (Exception var12) {
                    ;
                }
            }

            return map;
        }
    }

    public static void createModelFromDB(Connection connection, List<String> tables, String modelPath) throws Exception {
        MybatisGenerator.generateModelAndMapper(connection, tables, modelPath, (String)null, false);
    }

    public static void createHibernateModelFromDB(Connection connection, List<String> tables, String modelPath) throws Exception {
        MybatisGenerator.generateModelAndMapper(connection, tables, modelPath, (String)null, true);
    }

    public static void copyProperties(Object source, Object target) {
        if (source != null && target != null) {
            Class<?> sourceClass = source.getClass();
            Class<?> targetClass = target.getClass();
            CopyProperties sourceCopyPropertiesAnnotation = (CopyProperties)sourceClass.getAnnotation(CopyProperties.class);
            CopyProperties targetCopyPropertiesAnnotation = (CopyProperties)targetClass.getAnnotation(CopyProperties.class);

            try {
                if (sourceCopyPropertiesAnnotation != null) {
                    ModelService.copyPropertiesFromSourceToTargetLeadBySource(source, target);
                } else if (targetCopyPropertiesAnnotation != null) {
                    ModelService.copyPropertiesFromSourceToTargetLeadByTarget(source, target);
                } else if (source instanceof Map) {
                    ModelService.copyPropertiesFromMapToTargetLeadByTarget(source, target);
                }
            } catch (Exception var7) {
                var7.printStackTrace();
            }

        }
    }
}
