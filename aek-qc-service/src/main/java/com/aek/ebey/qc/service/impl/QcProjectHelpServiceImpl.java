package com.aek.ebey.qc.service.impl;

import com.aek.ebey.qc.model.QcProjectHelp;
import com.aek.ebey.qc.mapper.QcProjectHelpMapper;
import com.aek.ebey.qc.service.QcProjectHelpService;
import com.aek.common.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 项目 服务实现类
 * </p>
 *
 * @author aek
 * @since 2017-11-03
 */
@Service
@Transactional
public class QcProjectHelpServiceImpl extends BaseServiceImpl<QcProjectHelpMapper, QcProjectHelp> implements QcProjectHelpService {
	
}
