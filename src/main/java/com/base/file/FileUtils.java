package com.base.file;

import java.io.File;

/**
 * 文件工具类
 * @author:LiChong
 * @date:2018/11/19
 */
public class FileUtils {

	public final static String SEPARATOR = "/";

	/**
	 * 创建目录 如果目录不存在
	 * @param directoryPath
	 */
	public static void createDirectoryIfNotExist(String directoryPath) {
		createDirectoryIfNotExist(new File(directoryPath));
	}

	public static void createDirectoryIfNotExist(File directoryFile) {

		if (directoryFile == null) {
			throw new RuntimeException(" directoryFile is null");
		}
		if (!directoryFile.exists()) {
			directoryFile.mkdirs();
		}
	}
}
