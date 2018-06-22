package com.aek.ebey.qc.service;

import com.aek.common.core.base.BaseService;
import com.aek.common.core.serurity.model.AuthUser;
import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.model.MtPlanImplement;
import com.aek.ebey.qc.query.MtPlanImplementQuery;
import com.aek.ebey.qc.request.MtPlanImplementResponse;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 保养计划实施服务类
 *
 * @author Honghui
 * @since 2018-03-21
 */
public interface MtPlanImplementService extends BaseService<MtPlanImplement> {
	
	/**
	 * 分页查询保养实施列表数据
	 * @param page
	 * @param query
	 * @return
	 */
	public LiuHuiPage<MtPlanImplementResponse> getMtPlanImplementPage(Page<MtPlanImplementResponse> page,AuthUser user,MtPlanImplementQuery query);
	
}
