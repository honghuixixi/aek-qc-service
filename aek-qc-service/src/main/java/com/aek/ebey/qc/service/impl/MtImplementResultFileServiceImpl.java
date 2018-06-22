package com.aek.ebey.qc.service.impl;

import com.aek.ebey.qc.model.MtImplementResultFile;
import com.aek.ebey.qc.mapper.MtImplementResultFileMapper;
import com.aek.ebey.qc.service.MtImplementResultFileService;
import com.aek.common.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 保养实施结果附件表 服务实现类
 *
 * @author Honghui
 * @since 2018-03-21
 */
@Service
@Transactional
public class MtImplementResultFileServiceImpl extends BaseServiceImpl<MtImplementResultFileMapper, MtImplementResultFile> implements MtImplementResultFileService {
	
}
