package com.aek.ebey.qc.service;

import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.model.PmTemplate;
import com.aek.ebey.qc.query.PmTemplateQuery;
import com.aek.ebey.qc.request.PmTemplateDetail;
import com.aek.ebey.qc.request.PmTemplateResponse;

import java.util.List;

import com.aek.common.core.base.BaseService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author aek
 * @since 2017-11-27
 */
public interface PmTemplateService extends BaseService<PmTemplate> {

	void save(PmTemplate pmTemplate);

	void edit(PmTemplate pmTemplate);

	PmTemplateDetail getAllById(Long id);

	LiuHuiPage<PmTemplateResponse> search(PmTemplateQuery query);

	List<PmTemplateResponse> changeSearch(String keyword);

	PmTemplateDetail getAllById2(Long id);
	
}
