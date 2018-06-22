package com.aek.ebey.qc.mapper;

import com.aek.ebey.qc.model.PmAnswers;
import com.aek.common.core.base.BaseMapper;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author aek
 * @since 2017-11-30
 */
public interface PmAnswersMapper extends BaseMapper<PmAnswers> {

	int selectAnswerByImplementId(Long id);

}