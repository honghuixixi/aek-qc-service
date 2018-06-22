package com.aek.ebey.qc.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aek.common.core.BeanMapper;
import com.aek.common.core.base.BaseServiceImpl;
import com.aek.common.core.serurity.WebSecurityUtils;
import com.aek.common.core.serurity.model.AuthUser;
import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.inter.StatusConstants;
import com.aek.ebey.qc.mapper.MsAssetsMapper;
import com.aek.ebey.qc.mapper.MsCheckHistoryMapper;
import com.aek.ebey.qc.mapper.MsCheckMapper;
import com.aek.ebey.qc.mapper.MsCheckResultInfoMapper;
import com.aek.ebey.qc.mapper.MsCheckStorageMapper;
import com.aek.ebey.qc.model.MsAssets;
import com.aek.ebey.qc.model.MsCheck;
import com.aek.ebey.qc.model.MsCheckHistory;
import com.aek.ebey.qc.model.MsCheckResultInfo;
import com.aek.ebey.qc.model.MsCheckStorage;
import com.aek.ebey.qc.query.MsAssetsRecordQuery;
import com.aek.ebey.qc.request.MsAssetsRecordResponse;
import com.aek.ebey.qc.request.MsAssetsResponse;
import com.aek.ebey.qc.request.MsCheckStorageRequest;
import com.aek.ebey.qc.service.MsCheckResultInfoService;
import com.aek.ebey.qc.utils.QcUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 计量设备检测结果信息表（已检测） 服务实现类
 * </p>
 *
 * @author Honghui
 * @since 2018-04-25
 */
@Service
@Transactional
public class MsCheckResultInfoServiceImpl extends BaseServiceImpl<MsCheckResultInfoMapper, MsCheckResultInfo> implements MsCheckResultInfoService {

	
	@Autowired
	private MsAssetsMapper msAssetsMapper;
	
	@Autowired
	private MsCheckResultInfoMapper msCheckResultInfoMapper;
	
	@Autowired
	private MsCheckStorageMapper msCheckStorageMapper;
	
	@Autowired
	private MsCheckMapper msCheckMapper;
	
	@Autowired
	private MsCheckHistoryMapper msCheckHistoryMapper;
	
	@Override
	public MsCheckResultInfo saved(MsCheckStorageRequest request) {
		//检测ID
		Long id=request.getMsCheckId();
		Wrapper<MsCheckStorage> wrapper=new EntityWrapper<>();
		wrapper.eq("ms_check_id", id);
		//删暂存表
		msCheckStorageMapper.delete(wrapper);
		//删除检测重新复制检测
		
		//复制检测表冰保存检测信息
		MsCheck msCheck=msCheckMapper.selectById(id);
		//计量台帐ID
		Long msId=null;
		MsCheckResultInfo msCheckResultInfo = null;
		if(msCheck!=null){
			Date date=new Date();
			msId=msCheck.getMeasureAssetsId();
			MsCheckHistory msCheckHistory = BeanMapper.map(msCheck, MsCheckHistory.class);
			msCheckHistory.setId(null);
			msCheckHistory.setSubmitTime(date);
			msCheckHistoryMapper.insert(msCheckHistory);
			msCheckResultInfo = BeanMapper.map(request, MsCheckResultInfo.class);
			msCheckResultInfo.setCheckHistoryId(msCheckHistory.getId());
			msCheckResultInfo.setCheckReportNo(QcUtils.mSreportNo());
			msCheckResultInfo.setSubmitTime(date);
			msCheckResultInfo.setCreateTime(date);
			msCheckResultInfoMapper.insert(msCheckResultInfo);
		}
		//删检测表
		msCheckMapper.deleteById(id);
		//新增检测
		if(msId!=null){
			MsAssets msAssets=msAssetsMapper.selectById(msId);
			if(msAssets != null && !msAssets.getDelFlag()){
				msAssets.setNextCheckTime(request.getNextCheckTime());
				msAssetsMapper.updateById(msAssets);
				MsCheck newMsCheck = BeanMapper.map(msAssets, MsCheck.class);
				newMsCheck.setId(null);
				newMsCheck.setCheckStatus(StatusConstants.UNCHECK_STATUS);
				newMsCheck.setMeasureAssetsId(msAssets.getId());
				msCheckMapper.insert(newMsCheck);
			}
		}
		return msCheckResultInfo;
		
	}

	@Override
	public LiuHuiPage<MsAssetsRecordResponse> search(MsAssetsRecordQuery query) {
		AuthUser authUser = WebSecurityUtils.getCurrentUser();
		Page<MsAssetsRecordResponse> pageMsAssetsRecordResponse = query.getPagePlus();
		List<MsAssetsRecordResponse> list=msCheckResultInfoMapper.search(pageMsAssetsRecordResponse,query,authUser);
		LiuHuiPage<MsAssetsRecordResponse> page=new LiuHuiPage<>(pageMsAssetsRecordResponse);
		page.setRecords(list);
		return page;
	}
	
}
