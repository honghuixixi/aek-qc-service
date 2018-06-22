package com.aek.ebey.qc.service;

import java.util.List;

import com.aek.common.core.base.BaseService;
import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.model.QcTemplate;
import com.aek.ebey.qc.query.QcTemplateQuery;
import com.aek.ebey.qc.request.QcTemplateDetail;
import com.aek.ebey.qc.request.QcTemplateResponse;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author aek
 * @since 2017-11-02
 */
public interface QcTemplateService extends BaseService<QcTemplate> {

	void save(QcTemplate qcTemplate);

	void edit(QcTemplate qcTemplate);

	LiuHuiPage<QcTemplateResponse> search(QcTemplateQuery query);

	QcTemplateDetail getAllById(Long id);
	
	QcTemplateDetail getAllById2(Long id);

	List<QcTemplateResponse> changeSearch(String keyword);
	
}
