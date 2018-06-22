package com.aek.ebey.qc.model;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * PMoption答案
 * </p>
 *
 * @author aek
 * @since 2017-12-01
 */
@TableName("pm_answers_option")
public class PmAnswersOption extends BaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	private Long id;
	/**
	 * 关联实施id
	 */
	@TableField(value="implement_id")
	private Long implementId;
	/**
	 * 答案id
	 */
	@TableField(value="answer_id")
	private Long answerId;
	/**
	 * 选项id
	 */
	@TableField(value="option_id")
	private Long optionId;
	/**
	 * 选项名称
	 */
	@TableField(value="option_name")
	private String optionName;
	/**
	 *  答案1：合格 2：修改 3：可用 4：待修
	 */
	private Integer answer;
	/**
	 * 备注
	 */
	private String remarks;
	
	/**
	 * 实测值
	 */
	private String measure;
	
	
	/**
	 * 设置值
	 */
	private String setnum;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getImplementId() {
		return implementId;
	}

	public void setImplementId(Long implementId) {
		this.implementId = implementId;
	}

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	public Long getOptionId() {
		return optionId;
	}

	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
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
