package com.aek.ebey.qc.request;

/**
 * <p>
 * PM模版
 * </p>
 *
 * @author aek
 * @since 2017-11-27
 */
public class PmTemplateEditResponse extends BaseRequest {

	/**
	 * 模板名称
	 */
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
