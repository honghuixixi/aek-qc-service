package com.aek.ebey.qc.request;

/**
 * <p>
 * 
 * </p>
 *
 * @author aek
 * @since 2017-11-02
 */
public class QcOptionContent{


	/**
	 * 选项id
	 */

	private Long id;
	/**
	 * 选项名称
	 */
	private String result;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
}
