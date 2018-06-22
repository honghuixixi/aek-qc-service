package com.aek.ebey.qc.service.impl;

import com.aek.ebey.qc.model.QcDept;
import com.aek.ebey.qc.mapper.QcDeptMapper;
import com.aek.ebey.qc.service.QcDeptService;
import com.aek.common.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author aek
 * @since 2017-11-03
 */
@Service
@Transactional
public class QcDeptServiceImpl extends BaseServiceImpl<QcDeptMapper, QcDept> implements QcDeptService {
	
}
