package com.aek.ebey.qc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aek.common.core.base.BaseServiceImpl;
import com.aek.common.core.serurity.model.AuthUser;
import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.mapper.MtPlanImplementMapper;
import com.aek.ebey.qc.model.MtPlanImplement;
import com.aek.ebey.qc.query.MtPlanImplementQuery;
import com.aek.ebey.qc.request.MtPlanImplementResponse;
import com.aek.ebey.qc.service.MtPlanImplementService;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 保养计划实施表 服务实现类
 *
 * @author Honghui
 * @since 2018-03-21
 */
@Service
@Transactional
public class MtPlanImplementServiceImpl extends BaseServiceImpl<MtPlanImplementMapper, MtPlanImplement> implements MtPlanImplementService {

	@Autowired
	private MtPlanImplementMapper mtPlanImplementMapper;
	
	@Override
	public LiuHuiPage<MtPlanImplementResponse> getMtPlanImplementPage(Page<MtPlanImplementResponse> page,AuthUser user,
			MtPlanImplementQuery query) {
		List<MtPlanImplementResponse> list = mtPlanImplementMapper.getMtPlanImplementPage(page,user,query);
		LiuHuiPage<MtPlanImplementResponse> responsePage = new LiuHuiPage<>(page);
		responsePage.setRecords(list);
		return responsePage;
	}
	
	
}
