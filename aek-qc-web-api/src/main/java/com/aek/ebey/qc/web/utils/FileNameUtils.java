package com.aek.ebey.qc.web.utils;

import java.util.UUID;

/**
 * 
 * 文件工具类
 *
 */
public class FileNameUtils {

	//根据文件名得到UUID的文件名
	public static String getFileName(String name) {
		int len = name.lastIndexOf(".");
		String fileName = name.substring(len);
		UUID uuid = UUID.randomUUID();
		return (uuid.toString() + fileName).replace("-", "");

	}
	

}
