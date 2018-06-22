package com.aek.ebey.qc.constant;

import java.util.HashMap;
import java.util.Map;

public class MdConstant {

	//质检单状态(1=暂存、2=待审核、3=审核通过、4=审核未通过、5=已撤销)
	public final static Map<Integer, String> STATUS_MAP = new HashMap<Integer, String>();
	static {
		STATUS_MAP.put(1, "暂存");
		STATUS_MAP.put(2, "待审核");
		STATUS_MAP.put(3, "审核通过");
		STATUS_MAP.put(4, "审核未通过");
		STATUS_MAP.put(5, "已撤销");
	}
	
	//质控审核列表查询选择状态(1=全部、2=待审核、3=审核通过、4=审核未通过)
//	public final static Map<Integer, String> VERIFY_TYPE_MAP = new HashMap<Integer, String>();
//	static {
//		VERIFY_TYPE_MAP.put(1, "全部");
//		VERIFY_TYPE_MAP.put(2, "待审核");
//		VERIFY_TYPE_MAP.put(3, "审核通过");
//		VERIFY_TYPE_MAP.put(4, "审核未通过");
//	}
	
	//质控检测类型(1=验收检测、2=状态性检测、3=稳定性检测)
	public final static Map<Integer, String> CHECK_TYPE_MAP = new HashMap<Integer, String>();
	static {
		CHECK_TYPE_MAP.put(1, "验收检测");
		CHECK_TYPE_MAP.put(2, "状态性检测");
		CHECK_TYPE_MAP.put(3, "稳定性检测");
	}
	
	//检测结论(0=不合格、1=合格)
	public final static Map<Integer, String> CHECK_RESULT_MAP = new HashMap<Integer, String>();
	static {
		CHECK_RESULT_MAP.put(0, "不合格");
		CHECK_RESULT_MAP.put(1, "合格");
	}
	
	//审核结果(0=审核未通过、1=审核通过)
	public final static Map<Integer, String> VERIFY_RESULT_MAP = new HashMap<Integer, String>();
	static {
		VERIFY_RESULT_MAP.put(0, "审核未通过");
		VERIFY_RESULT_MAP.put(1, "审核通过");
	}
}
