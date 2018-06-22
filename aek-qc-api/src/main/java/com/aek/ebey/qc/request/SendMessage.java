package com.aek.ebey.qc.request;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "SendMessage", description = "SendMessage")
public class SendMessage{
	/**
	 * 接收人ID
	 */
	@ApiModelProperty(value="接收人ID")
	private Long userId;
	
	
	/**
	 * id
	 */
	@ApiModelProperty(value="ID")
	private Long moduleId;
	/**
	 * 消息内容
	 */
	@ApiModelProperty(value="消息内容")
	private String messageContent;
	/**
	 * 备注
	 */
	@ApiModelProperty(value="备注")
	private String remarks;
	
	@ApiModelProperty(value="status")
	private int status;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getModuleId() {
		return moduleId;
	}
	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
