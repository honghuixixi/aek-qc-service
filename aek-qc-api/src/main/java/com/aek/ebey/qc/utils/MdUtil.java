package com.aek.ebey.qc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MdUtil {

	public static String generateMdNum() {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

		Date date = new Date();

		String str = simpleDateFormat.format(date);

		Random random = new Random();

		int rannum = (int) (random.nextDouble() * (9999 - 1000 + 1)) + 1000;// 获取4位随机数

		return "ZKBG" + str + rannum;
		
	}
	
	public static String generateMdArchiveNum() {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

		Date date = new Date();

		String str = simpleDateFormat.format(date);

		Random random = new Random();

		int rannum = (int) (random.nextDouble() * (9999 - 1000 + 1)) + 1000;// 获取4位随机数

		return "ZKDA" + str + rannum;
		
	}

	public static void main(String[] args) {
		String generateMdNum = MdUtil.generateMdNum();
		System.out.println(generateMdNum);
	}
}
