package com.aek.ebey.qc.query;

import java.util.List;

import com.aek.ebey.qc.request.Dept;
import com.aek.ebey.qc.request.QcDeptRequest;

public class QcUserDept {
	private List<QcDeptRequest> qcDepts;
	
	private Long userId;
	
	private String userName;

	

	public List<QcDeptRequest> getQcDepts() {
		return qcDepts;
	}

	public void setQcDepts(List<QcDeptRequest> qcDepts) {
		this.qcDepts = qcDepts;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "QcUserDept [qcDepts=" + qcDepts + ", userId=" + userId + ", userName=" + userName + "]";
	}
	

}
