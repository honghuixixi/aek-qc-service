package com.aek.ebey.qc.request;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import com.aek.ebey.qc.validator.group.Add;
import com.aek.ebey.qc.validator.group.Edit;


public class QcProjectRequest{
	/**
	 * 模板id
	 */
	private Long id;
	
	/**
	 * 项目名称
	 */
	@Length(max = 40, groups = {Add.class,Edit.class  }, message = "Q_001")
	@NotEmpty(groups = {Add.class,Edit.class  }, message = "D_001")
	private String name;
	
	/**
	 * 选项列表
	 */
	@NotEmpty(groups = { Add.class,Edit.class }, message = "Q_002")
	@Valid
	private List<QcOptionRequest> list;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<QcOptionRequest> getList() {
		return list;
	}

	public void setList(List<QcOptionRequest> list) {
		this.list = list;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
}
