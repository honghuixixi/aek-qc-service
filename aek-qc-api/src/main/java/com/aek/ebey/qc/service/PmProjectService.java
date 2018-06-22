package com.aek.ebey.qc.service;

import com.aek.ebey.qc.model.PmProject;
import com.aek.ebey.qc.request.PmProjectRequest2;
import com.aek.common.core.base.BaseService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author aek
 * @since 2017-11-27
 */
public interface PmProjectService extends BaseService<PmProject> {

	void save(PmProjectRequest2 pmProjectRequest);

	void edit(PmProjectRequest2 request);

	void deleteProject(PmProject pmProject);
	
}
