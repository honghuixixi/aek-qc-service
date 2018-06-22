package com.aek.ebey.qc.inter;

public interface Constants {
	/** 系统模版类型*/
	public static final Integer SYSTEM_TYPE = 1;
	
	/** 自定义模版类型*/
	public static final Integer CUSTOM_TYPE = 2;
	
	/** 巡检周期类型 月*/
	public static final Integer CYCLE_MOUNTH = 1;
	
	/** 巡检周期类型 天*/
	public static final Integer CYCLE_DAY = 2;
	
	//非强制计量设备
	public static final Integer UNFORCE_MEASURE_ASSETS = 1;
	
	//强制计量设备
    public static final Integer FORCE_MEASURE_ASSETS = 2;
}
