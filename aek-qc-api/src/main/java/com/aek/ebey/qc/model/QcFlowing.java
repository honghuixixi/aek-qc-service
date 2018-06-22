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
@TableName("qc_flowing")
public class QcFlowing extends BaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * 机构ID
	 */
	@TableField(value="tenant_id")
	private Long tenantId;
	/**
	 * 
	 */
	@TableField(value="current_value")
	private Long currentValue;
	/**
	 * 
	 */
	private Integer increment;
	/**
	 * 那天
	 */
	private String days;


	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Long getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(Long currentValue) {
		this.currentValue = currentValue;
	}

	public Integer getIncrement() {
		return increment;
	}

	public void setIncrement(Integer increment) {
		this.increment = increment;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

}
