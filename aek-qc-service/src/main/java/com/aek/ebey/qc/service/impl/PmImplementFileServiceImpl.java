package com.aek.ebey.qc.service.impl;

import com.aek.ebey.qc.model.PmImplementFile;
import com.aek.ebey.qc.mapper.PmImplementFileMapper;
import com.aek.ebey.qc.service.PmImplementFileService;
import com.aek.common.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * PM实施文件表 服务实现类
 * </p>
 *
 * @author aek
 * @since 2017-11-30
 */
@Service
@Transactional
public class PmImplementFileServiceImpl extends BaseServiceImpl<PmImplementFileMapper, PmImplementFile> implements PmImplementFileService {
	
}
