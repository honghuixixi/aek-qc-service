package com.aek.ebey.qc.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aek.common.core.BeanMapper;
import com.aek.common.core.base.BaseServiceImpl;
import com.aek.ebey.qc.inter.StatusConstants;
import com.aek.ebey.qc.mapper.MsCheckMapper;
import com.aek.ebey.qc.mapper.MsCheckStorageMapper;
import com.aek.ebey.qc.model.MsCheck;
import com.aek.ebey.qc.model.MsCheckStorage;
import com.aek.ebey.qc.request.MsCheckStorageRequest;
import com.aek.ebey.qc.service.MsCheckStorageService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

/**
 * <p>
 * 计量设备检测信息暂存表(正在检测) 服务实现类
 * </p>
 *
 * @author Honghui
 * @since 2018-04-25
 */
@Service
@Transactional
public class MsCheckStorageServiceImpl extends BaseServiceImpl<MsCheckStorageMapper, MsCheckStorage> implements MsCheckStorageService {

	
	@Autowired
	private MsCheckStorageMapper msCheckStorageMapper;
	@Autowired
	private MsCheckMapper msCheckMapper;
	
	@Override
	public void saving(MsCheckStorageRequest request) {
		Wrapper<MsCheckStorage> wrapper=new EntityWrapper<>();
		wrapper.eq("ms_check_id", request.getMsCheckId());
		msCheckStorageMapper.delete(wrapper);
		MsCheckStorage msCheckStorage = BeanMapper.map(request, MsCheckStorage.class);
		msCheckStorageMapper.insert(msCheckStorage);
		//将检测信息检测状态更新为检测中
		MsCheck msCheck = msCheckMapper.selectById(request.getMsCheckId());
		//检测中
		msCheck.setCheckStatus(StatusConstants.CHECKING_STATUS);
		msCheckMapper.updateById(msCheck);
	}
	
}
