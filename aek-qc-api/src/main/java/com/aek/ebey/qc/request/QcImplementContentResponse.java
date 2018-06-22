package com.aek.ebey.qc.request;

import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author aek
 * @since 2017-11-03
 */
public class QcImplementContentResponse{
	
	/**
	 * 模板项目名称列表
	 */
	private List<QcProjectRequest4> template;

	public List<QcProjectRequest4> getTemplate() {
		return template;
	}

	public void setTemplate(List<QcProjectRequest4> template) {
		this.template = template;
	}
	

	
}
