package com.aek.ebey.qc.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.aek.common.core.base.BaseServiceImpl;
import com.aek.ebey.qc.mapper.PmAnswersMapper;
import com.aek.ebey.qc.model.PmAnswers;
import com.aek.ebey.qc.service.PmAnswersService;

/**
 * <p>
 * PM答案 服务实现类
 * </p>
 *
 * @author aek
 * @since 2017-11-30
 */
@Service
@Transactional
public class PmAnswersServiceImpl extends BaseServiceImpl<PmAnswersMapper, PmAnswers> implements PmAnswersService {


	@Autowired
	private PmAnswersMapper pmAnswersMapper;
	@Override
	@Transactional(readOnly=true)
	public Boolean selectAnswerByImplementId(Long id) {
		int i=pmAnswersMapper.selectAnswerByImplementId(id);
		return i>0?true:false;
	}
	
}
