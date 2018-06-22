package com.aek.ebey.qc.service.impl;

import com.aek.ebey.qc.model.QcOption;
import com.aek.ebey.qc.mapper.QcOptionMapper;
import com.aek.ebey.qc.service.QcOptionService;
import com.aek.common.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author aek
 * @since 2017-11-02
 */
@Service
@Transactional
public class QcOptionServiceImpl extends BaseServiceImpl<QcOptionMapper, QcOption> implements QcOptionService {
	
}
