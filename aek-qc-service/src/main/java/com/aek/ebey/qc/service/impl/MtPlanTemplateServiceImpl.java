package com.aek.ebey.qc.service.impl;

import com.aek.ebey.qc.model.MtPlanTemplate;
import com.aek.ebey.qc.mapper.MtPlanTemplateMapper;
import com.aek.ebey.qc.service.MtPlanTemplateService;
import com.aek.common.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 保养计划模板表 服务实现类
 *
 * @author Honghui
 * @since 2018-03-21
 */
@Service
@Transactional
public class MtPlanTemplateServiceImpl extends BaseServiceImpl<MtPlanTemplateMapper, MtPlanTemplate> implements MtPlanTemplateService {
	
}
