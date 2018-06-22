package com.aek.ebey.qc.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "PmOptionAnswerRequest", description = "PmOptionAnswerRequest")
public class PmOptionAnswerRequest{
	
	/**
	 * 选项id
	 */
	@ApiModelProperty(value="选项id")
	private Long id;

	/**
	 * 选项名称
	 */
	@ApiModelProperty(value="选项名称")
	private String name;
	
	/**
	 * 答案1：合格 2：修改 3：可用 4：待修
	 */
	@ApiModelProperty(value="答案1：合格 2：修改 3：可用 4：待修")
	private Integer answer;
	/**
	 * 备注
	 */
	@ApiModelProperty(value="备注")
	private String remarks;
	
	/**
	 * 实测值
	 */
	@ApiModelProperty(value="备注实测值")
	private String measure;
	
	/**
	 * 设置值
	 */
	@ApiModelProperty(value="设置值")
	private String setnum;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAnswer() {
		return answer;
	}
	public void setAnswer(Integer answer) {
		this.answer = answer;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	public String getSetnum() {
		return setnum;
	}
	public void setSetnum(String setnum) {
		this.setnum = setnum;
	}
	
}
