package com.aek.ebey.qc.query;

import com.aek.common.core.base.page.PageHelp;
import com.aek.ebey.qc.model.vo.MtPlanResponseVO;

public class MtPlanQuery extends PageHelp<MtPlanResponseVO>{

	private String keyword;
	private Long deptId;
	private Integer enable;
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public Integer getEnable() {
		return enable;
	}
	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	
	
}
