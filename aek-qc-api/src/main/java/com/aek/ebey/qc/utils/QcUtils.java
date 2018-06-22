package com.aek.ebey.qc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.aek.ebey.qc.inter.StatusConstants;
import com.aek.ebey.qc.model.QcPlan;
import com.aek.ebey.qc.request.QcPlanRequest;

public class QcUtils {

	public static String getStatus(Integer status) {
		if(status!=null){
			if(status.intValue()==StatusConstants.ONE.intValue()){
				return "启用";
			}
			if(status.intValue()==StatusConstants.TWO.intValue()){
				return "停用";
			}
		}
		return null;
	}
	
	public static String getStringDate() {
		   Date currentTime = new Date();
		   SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		   String dateString = formatter.format(currentTime);
		   return dateString;
		}

	public static String getPlanNo(Long planNo) {
		if(planNo!=null){
			return "XJJH"+QcUtils.getStringDate()+String.format("%04d", planNo);
		}
		return null;
	}
	
	
	public static String getMsgDate(Date date) {
		   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		   String dateString = formatter.format(date);
		   return dateString;
		}
	/**
	 * 
	 * 得到巡检报告编码
	 */
	public static String reportNo() {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

		Date date = new Date();

		String str = simpleDateFormat.format(date);

		Random random = new Random();

		int rannum = (int) (random.nextDouble() * (9999 - 1000 + 1)) + 1000;// 获取4位随机数

		return "XJBG" + str + rannum;
		
	}
	
	/**
	 * 
	 * 得到PM报告编码
	 */
	public static String pMreportNo() {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

		Date date = new Date();

		String str = simpleDateFormat.format(date);

		Random random = new Random();

		int rannum = (int) (random.nextDouble() * (9999 - 1000 + 1)) + 1000;// 获取4位随机数

		return "PMBG" + str + rannum;
		
	}
	
	/**
	 * 
	 * 得到MS报告编码
	 */
	public static String mSreportNo() {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

		Date date = new Date();

		String str = simpleDateFormat.format(date);

		Random random = new Random();

		int rannum = (int) (random.nextDouble() * (9999 - 1000 + 1)) + 1000;// 获取4位随机数

		return "JLBG" + str + rannum;
		
	}
	/**
	 * 
	 * 得到计量编码
	 */
	public static String mSno() {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

		Date date = new Date();

		String str = simpleDateFormat.format(date);

		Random random = new Random();

		int rannum = (int) (random.nextDouble() * (9999 - 1000 + 1)) + 1000;// 获取4位随机数

		return "JL" + str + rannum;
		
	}
	
	/**
	 * 
	 * 得到保养报告编码
	 */
	public static String mtReportNo() {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

		Date date = new Date();

		String str = simpleDateFormat.format(date);

		Random random = new Random();

		int rannum = (int) (random.nextDouble() * (9999 - 1000 + 1)) + 1000;// 获取4位随机数

		return "BYBG" + str + rannum;
		
	}
	
	public static void main(String[] args) {
		System.out.println(getPlanNo(1l));
	}

	public static Date getNextTime(QcPlanRequest request) {
		if(request!=null){
			//1月 2天
			if(request.getCycleType()!=null&&1==request.getCycleType().intValue()){
				if(request.getCycle()!=null&&request.getDate()!=null){
					Calendar calendar=Calendar.getInstance();   
					   calendar.setTime(request.getDate()); 
					   calendar.add(Calendar.MONTH,request.getCycle());//让月加1  
					  return calendar.getTime();
				}
			}
			if(request.getCycleType()!=null&&2==request.getCycleType().intValue()){
				if(request.getCycle()!=null&&request.getDate()!=null){
					Calendar calendar=Calendar.getInstance();   
					   calendar.setTime(request.getDate()); 
					   calendar.add(Calendar.DAY_OF_MONTH,request.getCycle());//让日期加1  
					  return calendar.getTime();
				}
			}
		}
		return null;
	}

	public static String getAttention(Date smdate,Date bdate) {
		String s="";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        try {
			smdate=sdf.parse(sdf.format(smdate));
			bdate=sdf.parse(sdf.format(bdate));  
		} catch (ParseException e) {
			e.printStackTrace();
		}  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
        if(between_days<=5&&between_days>=0){
        	s="剩余"+between_days+"天";
        }else if(between_days<0){
        	s="已过期";
        }    
		return s;
	}
	
	public static String getAttentionMs(Date smdate,Date bdate) {
		String s="";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        try {
			smdate=sdf.parse(sdf.format(smdate));
			bdate=sdf.parse(sdf.format(bdate));  
		} catch (ParseException e) {
			e.printStackTrace();
		}  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
        if(between_days<=30&&between_days>=0){
        	s="剩余"+between_days+"天";
        }else if(between_days<0){
        	s="已过期";
        }    
		return s;
	}
	
	
	public static int getFlag(Date smdate,Date bdate) {
		int flag=0;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        try {
			smdate=sdf.parse(sdf.format(smdate));
			bdate=sdf.parse(sdf.format(bdate));  
		} catch (ParseException e) {
			e.printStackTrace();
		}  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
        if(between_days<=5&&between_days>=0){
        	flag=1;
        }else if(between_days<0){
        	flag=2;
        }    
		return flag;
	}
	
	
	public static long getNumDay(Date smdate,Date bdate) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        try {
			smdate=sdf.parse(sdf.format(smdate));
			bdate=sdf.parse(sdf.format(bdate));  
		} catch (ParseException e) {
			e.printStackTrace();
		}  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);    
		return between_days;
	}
	
	//QcUtils.getNextTime(request)
	public static Date getNextTime2(QcPlan plan) {
		if(plan!=null){
			//1月 2天
			if(plan.getCycleType()!=null&&1==plan.getCycleType().intValue()){
				if(plan.getNextTime()!=null){
					Calendar calendar=Calendar.getInstance();   
					   calendar.setTime(plan.getNextTime()); 
					   calendar.add(Calendar.MONTH,plan.getPlanCycle());//让月加1  
					  return calendar.getTime();
				}
			}
			if(plan.getCycleType()!=null&&2==plan.getCycleType().intValue()){
				if(plan.getNextTime()!=null){
					Calendar calendar=Calendar.getInstance();   
					   calendar.setTime(plan.getNextTime()); 
					   calendar.add(Calendar.DAY_OF_MONTH,plan.getPlanCycle());//让日期加1  
					  return calendar.getTime();
				}
			}
		}
		return null;
	}
	
	public static Date getDay(String year) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(year);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
