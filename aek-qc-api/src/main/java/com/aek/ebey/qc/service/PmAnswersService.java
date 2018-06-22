package com.aek.ebey.qc.service;

import com.aek.ebey.qc.model.PmAnswers;
import com.aek.common.core.base.BaseService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author aek
 * @since 2017-11-30
 */
public interface PmAnswersService extends BaseService<PmAnswers> {

	Boolean selectAnswerByImplementId(Long id);
	
}
