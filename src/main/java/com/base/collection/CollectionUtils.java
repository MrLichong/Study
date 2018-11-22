package com.base.collection;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 集合框架工具类
 * @author:LiChong
 * @date:2018/11/15
 */
public class CollectionUtils {

	/**
	 * 将list转为string
	 * @param list
	 * @param separator 分隔符
	 * @param charizingOperator 是否加单引号
	 * @param <T>
	 * @return
	 */
	public static <T> String listToStr(List<T> list, String separator, boolean charizingOperator) {
		if (isEmpty(list)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (T t : list) {
			if (charizingOperator) {
				sb.append("'").append(t).append("'");
			}else{
				sb.append(t);
			}
			sb.append(separator);
		}
		return sb.substring(0, sb.length() - 1);
	}

	/**
	 * 讲字符串按分隔符分割并组合成list
	 * @param str
	 * @param separator
	 * @return
	 */
	public static List<String> strToList(String str, String separator) {
		List<String> list = new ArrayList<String>();
		if (StringUtils.isNotBlank(str)) {
			String[] split = str.split(separator);
			if (split != null && split.length > 0) {
				for (String s : split) {
					list.add(s);
				}
			}
		}
		return list;
	}

	/**
	 * 判断list是否非空
	 * @param list
	 * @return
	 */
	public static boolean isNotEmpty(List list) {
		if (list == null || list.size() == 0) {
			return false;
		}
		return true;
	}

	/**
	 * 判断list是否为空
	 * @param list
	 * @return
	 */
	public static boolean isEmpty(List list) {
		return !isNotEmpty(list);
	}

	/**
	 * 判断map是否不为空
	 * @param map
	 * @return
	 */
	public static boolean isNotEmpty(Map map) {
		if (map == null || map.size() == 0) {
			return false;
		}
		return true;
	}

	/**
	 * 判断map是否为空
	 * @param map
	 * @return
	 */
	public static boolean isEmpty(Map map) {
		return !isNotEmpty(map);
	}

	/**
	 * 根据key在map获取对于的值，避免空指针
	 * @param paramsMap
	 * @param key
	 * @return
	 */
	public String getParam(Map paramsMap, String key) {
		if (isEmpty(paramsMap)) {
			throw new RuntimeException("paramsMap is null or empty");
		}
		Object o = paramsMap.get(key);
		return o == null ? "": o.toString();
	}



	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("sss");
		list.add("aaa");
		list.add("bbb");
		String s = listToStr(list, ",", true);
		System.out.println(s);
	}
}
