package com.aek.ebey.qc.service;

import com.aek.ebey.qc.model.QcProject;
import com.aek.ebey.qc.request.QcProjectRequest;
import com.aek.ebey.qc.request.QcProjectRequest2;
import com.aek.common.core.base.BaseService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author aek
 * @since 2017-11-02
 */
public interface QcProjectService extends BaseService<QcProject> {

	void save(QcProjectRequest qcProjectRequest);

	void edit(QcProjectRequest2 request);

	void deleteProject(QcProject qcProject);
	
}
