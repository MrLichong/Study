//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.base.parameter;

import java.util.Map;

public class ParameterUtil {
    public ParameterUtil() {
    }

    public static void verifyMap(Map<String, Object> params, String... args) {
        if (params == null) {
            throw new NullPointerException("参数列表为空");
        } else {
            String msg = "";
            String[] var3 = args;
            int var4 = args.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String arg = var3[var5];
                Object o = params.get(arg);
                if (o == null || "".equals(o.toString())) {
                    msg = msg + arg + "不能为空  ";
                }
            }

            if (!"".equals(msg)) {
                throw new RuntimeException(msg);
            }
        }
    }
}
