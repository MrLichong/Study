//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.base.sql.util;

public class MapJavaAndDBColumeType {
    public MapJavaAndDBColumeType() {
    }

    public static String getJavaFieldType(String dbColumeType) {
        byte var3 = -1;
        switch(dbColumeType.hashCode()) {
        case -1618932450:
            if (dbColumeType.equals("INTEGER")) {
                var3 = 4;
            }
            break;
        case -594415409:
            if (dbColumeType.equals("TINYINT")) {
                var3 = 6;
            }
            break;
        case 2041757:
            if (dbColumeType.equals("BLOB")) {
                var3 = 3;
            }
            break;
        case 2067286:
            if (dbColumeType.equals("CHAR")) {
                var3 = 1;
            }
            break;
        case 2571565:
            if (dbColumeType.equals("TEXT")) {
                var3 = 2;
            }
            break;
        case 66988604:
            if (dbColumeType.equals("FLOAT")) {
                var3 = 9;
            }
            break;
        case 176095624:
            if (dbColumeType.equals("SMALLINT")) {
                var3 = 7;
            }
            break;
        case 651290682:
            if (dbColumeType.equals("MEDIUMINT")) {
                var3 = 8;
            }
            break;
        case 954596061:
            if (dbColumeType.equals("VARCHAR")) {
                var3 = 0;
            }
            break;
        case 1959128815:
            if (dbColumeType.equals("BIGINT")) {
                var3 = 5;
            }
            break;
        case 2022338513:
            if (dbColumeType.equals("DOUBLE")) {
                var3 = 10;
            }
        }

        String javaType;
        switch(var3) {
        case 0:
        case 1:
        case 2:
            javaType = "String";
            break;
        case 3:
            javaType = "byte[]";
            break;
        case 4:
        case 5:
            javaType = "Long";
            break;
        case 6:
        case 7:
        case 8:
            javaType = "Integer";
            break;
        case 9:
            javaType = "Float";
            break;
        case 10:
            javaType = "Double";
            break;
        default:
            javaType = "String";
        }

        return javaType;
    }
}
