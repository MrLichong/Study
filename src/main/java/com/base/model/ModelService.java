//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.base.model;


import com.base.model.annotation.SourceProperties;
import com.base.model.annotation.TargetProperties;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.activation.UnsupportedDataTypeException;

public class ModelService {
    public ModelService() {
    }

    static void copyPropertiesFromSourceToTargetLeadBySource(Object source, Object target) throws IllegalAccessException {
        Class<?> sourceClass = source.getClass();
        Class<?> targetClass = target.getClass();
        Field[] sourceClassDeclaredFields = sourceClass.getDeclaredFields();
        Field[] targetClassDeclaredFields = targetClass.getDeclaredFields();
        Map<String, List<Field>> targetClassDeclaredFieldsGroupByName = (Map)Arrays.stream(targetClassDeclaredFields).collect(Collectors.groupingBy((e) -> {
            return e.getName();
        }));
        Field[] var7 = sourceClassDeclaredFields;
        int var8 = sourceClassDeclaredFields.length;

        for(int var9 = 0; var9 < var8; ++var9) {
            Field sourceClassDeclaredField = var7[var9];
            TargetProperties sourceClassTargetPropertiesAnnotation = (TargetProperties)sourceClassDeclaredField.getAnnotation(TargetProperties.class);
            if (sourceClassTargetPropertiesAnnotation != null) {
                String targetPropertiesName = "".equals(sourceClassTargetPropertiesAnnotation.name()) ? sourceClassDeclaredField.getName() : sourceClassTargetPropertiesAnnotation.name();
                sourceClassDeclaredField.setAccessible(true);
                Object sourceValue = sourceClassDeclaredField.get(source);

                try {
                    Field targetClassDeclaredField = (Field)((List)targetClassDeclaredFieldsGroupByName.get(targetPropertiesName)).get(0);
                    targetClassDeclaredField.setAccessible(true);
                    Class<?> type = targetClassDeclaredField.getType();
                    Object value = null;
                    if (sourceValue != null) {
                        value = matchTypeValue(sourceValue, type);
                    }

                    targetClassDeclaredField.set(target, value);
                } catch (Exception var17) {
                    var17.printStackTrace();
                    System.out.println("设置：" + targetPropertiesName + "出现错误" + var17.getMessage());
                }
            }
        }

    }

    static void copyPropertiesFromSourceToTargetLeadByTarget(Object source, Object target) {
        Class<?> sourceClass = source.getClass();
        Class<?> targetClass = target.getClass();
        Field[] sourceClassDeclaredFields = sourceClass.getDeclaredFields();
        Field[] targetClassDeclaredFields = targetClass.getDeclaredFields();
        Map<String, List<Field>> sourceClassDeclaredFieldsGroupByName = (Map)Arrays.stream(sourceClassDeclaredFields).collect(Collectors.groupingBy((e) -> {
            return e.getName();
        }));
        Field[] var7 = targetClassDeclaredFields;
        int var8 = targetClassDeclaredFields.length;

        for(int var9 = 0; var9 < var8; ++var9) {
            Field targetClassDeclaredField = var7[var9];
            SourceProperties targetClassSourcePropertiesAnnotation = (SourceProperties)targetClassDeclaredField.getAnnotation(SourceProperties.class);
            if (targetClassSourcePropertiesAnnotation != null) {
                String sourcePropertiesName = "".equals(targetClassSourcePropertiesAnnotation.name()) ? targetClassDeclaredField.getName() : targetClassSourcePropertiesAnnotation.name();

                try {
                    Field sourceClassDeclaredField = (Field)((List)sourceClassDeclaredFieldsGroupByName.get(sourcePropertiesName)).get(0);
                    sourceClassDeclaredField.setAccessible(true);
                    Object sourceValue = sourceClassDeclaredField.get(source);
                    Class<?> type = targetClassDeclaredField.getType();
                    targetClassDeclaredField.setAccessible(true);
                    Object value = null;
                    if (sourceValue != null) {
                        value = matchTypeValue(sourceValue, type);
                    }

                    targetClassDeclaredField.set(target, value);
                } catch (Exception var17) {
                    System.out.println("设置" + sourcePropertiesName + "出现错误" + var17.getMessage());
                    var17.printStackTrace();
                }
            }
        }

    }

    public static void copyPropertiesFromMapToTargetLeadByTarget(Object source, Object target) {
        Map<String, Object> sourceMap = (Map)source;
        Class<?> targetClass = target.getClass();
        Field[] targetClassDeclaredFields = targetClass.getDeclaredFields();
        Field[] var5 = targetClassDeclaredFields;
        int var6 = targetClassDeclaredFields.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            Field targetClassDeclaredField = var5[var7];
            SourceProperties targetClassSourcePropertiesAnnotation = (SourceProperties)targetClassDeclaredField.getAnnotation(SourceProperties.class);
            if (targetClassSourcePropertiesAnnotation != null) {
                String sourcePropertiesName = "".equals(targetClassSourcePropertiesAnnotation.name()) ? targetClassDeclaredField.getName() : targetClassSourcePropertiesAnnotation.name();

                try {
                    Object sourceValue = sourceMap.get(sourcePropertiesName);
                    Class<?> type = targetClassDeclaredField.getType();
                    targetClassDeclaredField.setAccessible(true);
                    Object value = null;
                    if (sourceValue != null) {
                        value = matchTypeValue(sourceValue, type);
                    }

                    targetClassDeclaredField.set(target, value);
                } catch (Exception var14) {
                    System.out.println("设置" + sourcePropertiesName + "出现错误" + var14.getMessage());
                    var14.printStackTrace();
                }
            }
        }

    }

    public static <T> Object matchTypeValue(Object sourceValue, Class<?> type) throws UnsupportedDataTypeException {
        Object value = null;
        if (type != Integer.TYPE && type != Integer.class) {
            if (type != Double.TYPE && type != Double.class) {
                if (type != Float.TYPE && type != Float.class) {
                    if (type != Long.TYPE && type != Long.class) {
                        if (type == String.class) {
                            value = sourceValue.toString();
                        } else if (type == BigDecimal.class) {
                            value = new BigDecimal(sourceValue.toString());
                        } else {
                            value = sourceValue;
                        }
                    } else {
                        value = Long.parseLong(sourceValue.toString());
                    }
                } else {
                    value = Float.parseFloat(sourceValue.toString());
                }
            } else {
                value = Double.parseDouble(sourceValue.toString());
            }
        } else {
            value = Integer.parseInt(sourceValue.toString());
        }

        return value;
    }
}
