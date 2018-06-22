package com.aek.ebey.qc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aek.common.core.base.BaseServiceImpl;
import com.aek.common.core.serurity.WebSecurityUtils;
import com.aek.common.core.serurity.model.AuthUser;
import com.aek.ebey.qc.mapper.QcFlowingMapper;
import com.aek.ebey.qc.model.QcFlowing;
import com.aek.ebey.qc.service.QcFlowingService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

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
public class QcFlowingServiceImpl extends BaseServiceImpl<QcFlowingMapper, QcFlowing> implements QcFlowingService {

	@Autowired
	private QcFlowingMapper qcFlowingMapper;
	
	@Override
	public Long savePlanNo(String Date) {
		//QcFlowing qcFlowing=qcFlowingMapper.selectByTime(Date);
		AuthUser authUser = WebSecurityUtils.getCurrentUser();
		if(authUser!=null){
			Wrapper<QcFlowing> wrapper=new EntityWrapper<>();
			wrapper.eq("days", Date).eq("tenant_id", authUser.getTenantId());
			List<QcFlowing>  list=qcFlowingMapper.selectList(wrapper);
			if(list!=null&&list.size()>0){
				QcFlowing qcFlowing=list.get(0);
				qcFlowing.setCurrentValue(qcFlowing.getCurrentValue()+qcFlowing.getIncrement());
				qcFlowingMapper.updateById(qcFlowing);
				return qcFlowing.getCurrentValue();
			}else{
				QcFlowing qcFlowing=new QcFlowing();
				qcFlowing.setDays(Date);
				qcFlowing.setTenantId(authUser.getTenantId());
				qcFlowing.setIncrement(1);
				qcFlowing.setCurrentValue(1L);
				qcFlowingMapper.insert(qcFlowing);
				return 1l;
			}
		}
		return null;
	}
	
}
