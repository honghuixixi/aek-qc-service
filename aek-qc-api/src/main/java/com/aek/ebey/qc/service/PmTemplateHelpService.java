package com.aek.ebey.qc.service;

import com.aek.ebey.qc.model.PmTemplateHelp;
import com.aek.ebey.qc.request.PmProjectDetail;

import java.util.List;

import com.aek.common.core.base.BaseService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author aek
 * @since 2017-11-28
 */
public interface PmTemplateHelpService extends BaseService<PmTemplateHelp> {

	List<PmProjectDetail> getAllById(Long templateId);
	
}
