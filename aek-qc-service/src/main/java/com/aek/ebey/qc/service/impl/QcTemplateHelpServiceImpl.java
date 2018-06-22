package com.aek.ebey.qc.service.impl;

import com.aek.ebey.qc.model.QcTemplateHelp;
import com.aek.ebey.qc.mapper.QcTemplateHelpMapper;
import com.aek.ebey.qc.service.QcTemplateHelpService;
import com.aek.common.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 巡检模版 服务实现类
 * </p>
 *
 * @author aek
 * @since 2017-11-03
 */
@Service
@Transactional
public class QcTemplateHelpServiceImpl extends BaseServiceImpl<QcTemplateHelpMapper, QcTemplateHelp> implements QcTemplateHelpService {
	
}
