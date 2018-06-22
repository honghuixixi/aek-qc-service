package com.aek.ebey.qc.service.impl;

import com.aek.ebey.qc.model.PmAnswersOption;
import com.aek.ebey.qc.mapper.PmAnswersOptionMapper;
import com.aek.ebey.qc.service.PmAnswersOptionService;
import com.aek.common.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * PMoption答案 服务实现类
 * </p>
 *
 * @author aek
 * @since 2017-12-01
 */
@Service
@Transactional
public class PmAnswersOptionServiceImpl extends BaseServiceImpl<PmAnswersOptionMapper, PmAnswersOption> implements PmAnswersOptionService {
	
}
