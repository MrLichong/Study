//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.base.sql.helper;

public class FiledHelper {
    public FiledHelper() {
    }

    public static String parseDBFieldToJavaField(String field) {
        if (field.contains("_")) {
            int i = field.indexOf("_");
            String startLine = field.substring(0, i);
            String line2 = field.substring(i + 1, field.length());
            String line3 = line2.substring(0, 1).toUpperCase() + line2.substring(1, line2.length());
            String prepareLine = startLine + line3;
            return prepareLine.contains("_") ? parseDBFieldToJavaField(prepareLine) : prepareLine;
        } else {
            return field;
        }
    }

    public static String parseJavaFieldToDBField(String field) {
        char[] charArray = field.toCharArray();
        boolean flag = false;

        for(int i = 0; i < charArray.length; ++i) {
            if (Character.isUpperCase(charArray[i])) {
                flag = true;
                String substring = field.substring(0, i);
                char c = Character.toLowerCase(charArray[i]);
                String substring1 = field.substring(i + 1, charArray.length);
                field = substring + "_" + c + substring1;
                break;
            }
        }

        if (flag) {
            field = parseJavaFieldToDBField(field);
        }

        field = removeAroundUnderline(field);
        return field;
    }

    private static String removeAroundUnderline(String field) {
        if (field.startsWith("_")) {
            field = field.substring(1);
        }

        if (field.endsWith("_")) {
            field = field.substring(0, field.length() - 1);
        }

        return field;
    }
}
