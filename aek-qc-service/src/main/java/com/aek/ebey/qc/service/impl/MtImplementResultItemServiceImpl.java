package com.aek.ebey.qc.service.impl;

import com.aek.ebey.qc.model.MtImplementResultItem;
import com.aek.ebey.qc.mapper.MtImplementResultItemMapper;
import com.aek.ebey.qc.service.MtImplementResultItemService;
import com.aek.common.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 保养实施项目结果表 服务实现类
 *
 * @author Honghui
 * @since 2018-03-21
 */
@Service
@Transactional
public class MtImplementResultItemServiceImpl extends BaseServiceImpl<MtImplementResultItemMapper, MtImplementResultItem> implements MtImplementResultItemService {
	
}
