package com.aek.ebey.qc.service.impl;

import com.aek.ebey.qc.model.PmOptionHelp;
import com.aek.ebey.qc.mapper.PmOptionHelpMapper;
import com.aek.ebey.qc.service.PmOptionHelpService;
import com.aek.common.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * PM项目选项 服务实现类
 * </p>
 *
 * @author aek
 * @since 2017-11-28
 */
@Service
@Transactional
public class PmOptionHelpServiceImpl extends BaseServiceImpl<PmOptionHelpMapper, PmOptionHelp> implements PmOptionHelpService {
	
}
