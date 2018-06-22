package com.aek.ebey.qc.service.impl;

import com.aek.ebey.qc.model.MtPlanAssets;
import com.aek.ebey.qc.mapper.MtPlanAssetsMapper;
import com.aek.ebey.qc.service.MtPlanAssetsService;
import com.aek.common.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 保养计划资产信息表 服务实现类
 *
 * @author Honghui
 * @since 2018-03-21
 */
@Service
@Transactional
public class MtPlanAssetsServiceImpl extends BaseServiceImpl<MtPlanAssetsMapper, MtPlanAssets> implements MtPlanAssetsService {
	
}
