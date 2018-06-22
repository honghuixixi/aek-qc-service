package com.aek.ebey.qc.service.impl;

import com.aek.ebey.qc.model.PmProjectHelp;
import com.aek.ebey.qc.mapper.PmProjectHelpMapper;
import com.aek.ebey.qc.service.PmProjectHelpService;
import com.aek.common.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * PM项目 服务实现类
 * </p>
 *
 * @author aek
 * @since 2017-11-28
 */
@Service
@Transactional
public class PmProjectHelpServiceImpl extends BaseServiceImpl<PmProjectHelpMapper, PmProjectHelp> implements PmProjectHelpService {
	
}
