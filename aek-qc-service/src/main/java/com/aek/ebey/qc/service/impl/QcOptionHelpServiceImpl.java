package com.aek.ebey.qc.service.impl;

import com.aek.ebey.qc.model.QcOptionHelp;
import com.aek.ebey.qc.mapper.QcOptionHelpMapper;
import com.aek.ebey.qc.service.QcOptionHelpService;
import com.aek.common.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 项目选项 服务实现类
 * </p>
 *
 * @author aek
 * @since 2017-11-03
 */
@Service
@Transactional
public class QcOptionHelpServiceImpl extends BaseServiceImpl<QcOptionHelpMapper, QcOptionHelp> implements QcOptionHelpService {
	
}
