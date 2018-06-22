package com.aek.ebey.qc.service.impl;

import com.aek.ebey.qc.model.PmOption;
import com.aek.ebey.qc.mapper.PmOptionMapper;
import com.aek.ebey.qc.service.PmOptionService;
import com.aek.common.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * PM项目选项 服务实现类
 * </p>
 *
 * @author aek
 * @since 2017-11-27
 */
@Service
@Transactional
public class PmOptionServiceImpl extends BaseServiceImpl<PmOptionMapper, PmOption> implements PmOptionService {
	
}
