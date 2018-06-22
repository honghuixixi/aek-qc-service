package com.aek.ebey.qc.service.impl;

import com.aek.ebey.qc.model.MtTemplateItem;
import com.aek.ebey.qc.mapper.MtTemplateItemMapper;
import com.aek.ebey.qc.service.MtTemplateItemService;
import com.aek.common.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 保养模板项目表 服务实现类
 *
 * @author Honghui
 * @since 2018-03-21
 */
@Service
@Transactional
public class MtTemplateItemServiceImpl extends BaseServiceImpl<MtTemplateItemMapper, MtTemplateItem> implements MtTemplateItemService {
	
}
