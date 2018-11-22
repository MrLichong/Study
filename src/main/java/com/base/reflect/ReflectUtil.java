//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.base.reflect;

public class ReflectUtil {
    public ReflectUtil() {
    }

    public static String getGetMethodName(String fieldName) {
        return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    public static String getSetMethodName(String fieldName) {
        return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }
}
