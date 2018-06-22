package com.aek.ebey.qc.service.impl;

import com.aek.ebey.qc.model.MtPlanTemplateItem;
import com.aek.ebey.qc.mapper.MtPlanTemplateItemMapper;
import com.aek.ebey.qc.service.MtPlanTemplateItemService;
import com.aek.common.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 保养计划模板项目表 服务实现类
 *
 * @author Honghui
 * @since 2018-03-21
 */
@Service
@Transactional
public class MtPlanTemplateItemServiceImpl extends BaseServiceImpl<MtPlanTemplateItemMapper, MtPlanTemplateItem> implements MtPlanTemplateItemService {
	
}
