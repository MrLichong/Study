//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.base.fouraroperationutil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FourArOperations {
    public FourArOperations() {
    }

    public static void main(String[] args) {
        double result = (new FourArOperations()).getResult("0-3-8-1");
        System.out.println(result);
    }

    public static List<String> splitStr(String string) throws Exception {
        List<String> listSplit = new ArrayList();
        Matcher matcher = Pattern.compile("\\-?\\d+(\\.\\d+)?|[*/()]|\\-").matcher(string);

        while(matcher.find()) {
            listSplit.add(matcher.group(0));
        }

        return listSplit;
    }

    public double getResult(String str) {
        try {
            double returnDouble = 0.0D;
            List<String> listSplit = splitStr(str);
            List<Integer> zKuohaoIdxList = new ArrayList();
            if (Pattern.compile(".*\\(|\\).*").matcher(str).find()) {
                String value = "";
               // int zIdx = 0;
                List<String> tempList = new ArrayList();
               // int removeL = 0;
                //int tempListSize = 0;

                for(int i = 0; i < listSplit.size(); ++i) {
                    value = (String)listSplit.get(i);
                    ((List)tempList).add(value);
                    int tempListSize = ((List)tempList).size();
                    if ("(".equals(value)) {
                        zKuohaoIdxList.add(tempListSize - 1);
                    } else if (")".equals(value)) {
                        int zIdx = zKuohaoIdxList.size() - 1;
                        int start = (Integer)zKuohaoIdxList.get(zIdx);
                        returnDouble = this.jisuan((List)tempList, start + 1, tempListSize - 1);
                        int removeL = tempListSize - start;
                        tempList = this.removeUseList((List)tempList, removeL);
                        ((List)tempList).add(returnDouble + "");
                        zKuohaoIdxList.remove(zIdx);
                    }
                }

                returnDouble = this.jisuan((List)tempList, 0, ((List)tempList).size());
            } else {
                returnDouble = this.jisuan(listSplit, 0, listSplit.size());
            }

            return returnDouble;
        } catch (Exception var13) {
            var13.printStackTrace();
            throw new RuntimeException(var13);
        }
    }

    public List<String> removeUseList(List<String> list, int removeLength) {
        int le = list.size() - removeLength;

        for(int i = list.size() - 1; i >= le; --i) {
            list.remove(i);
        }

        return list;
    }

    public double jisuan(List<String> listSplit, int start, int end) throws Exception {
        double returnValue = 0.0D;
        String strValue = null;
        List<String> jjValueList = new ArrayList();

        int j;
        for(j = start; j < end; ++j) {
            strValue = (String)listSplit.get(j);
            if ("*".equals(strValue) || "/".equals(strValue)) {
                strValue = this.jisuanValue("*".equals(strValue) ? "*" : "/", Double.parseDouble((String)jjValueList.get(jjValueList.size() - 1)), Double.parseDouble((String)listSplit.get(j + 1))) + "";
                jjValueList.remove(jjValueList.size() - 1);
                ++j;
            }

            jjValueList.add(strValue);
        }

        for(j = 0; j < jjValueList.size(); ++j) {
            strValue = (String)jjValueList.get(j);
            if (!"-".equals(strValue) && !"+".equals(strValue)) {
                returnValue += Double.parseDouble((String)jjValueList.get(j));
            } else {
                returnValue = this.jisuanValue("-".equals(strValue) ? "-" : "+", returnValue, Double.parseDouble((String)jjValueList.get(j + 1)));
                ++j;
            }
        }

        return returnValue;
    }

    public double jisuanValue(String type, double start, double end) throws Exception {
        double d = 0.0D;
        if ("-".equals(type)) {
            d = start - end;
        } else if ("+".equals(type)) {
            d = start + end;
        } else if ("*".equals(type)) {
            d = start * end;
        } else if ("/".equals(type)) {
            if (0.0D != start && 0.0D != end) {
                d = start / end;
            } else {
                d = 0.0D;
            }
        }

        return d;
    }
}
