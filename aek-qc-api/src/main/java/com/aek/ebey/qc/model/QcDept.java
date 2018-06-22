package com.aek.ebey.qc.model;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author aek
 * @since 2017-11-03
 */
@TableName("qc_dept")
public class QcDept extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
	 * 机构ID
	 */
	@TableField(value="tenant_id")
	private Long tenantId;
	/**
	 * 关联巡检计划ID
	 */
	@TableField(value="plan_id")
	private Long planId;
	/**
	 * 巡检科室ID
	 */
	@TableField(value="dept_id")
	private Long deptId;
	/**
	 * 科室名称
	 */
	@TableField(value="dept_name")
	private String deptName;
	
	/**
	 * 状态 1:启用、2:停用
	 */
	private Integer status;



	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
