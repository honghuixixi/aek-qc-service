package com.aek.ebey.qc.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aek.common.core.BeanMapper;
import com.aek.common.core.base.BaseServiceImpl;
import com.aek.common.core.serurity.WebSecurityUtils;
import com.aek.common.core.serurity.model.AuthUser;
import com.aek.ebey.qc.inter.Constants;
import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.inter.MsStatus;
import com.aek.ebey.qc.inter.StatusConstants;
import com.aek.ebey.qc.mapper.MsAssetsMapper;
import com.aek.ebey.qc.mapper.MsCheckMapper;
import com.aek.ebey.qc.model.MsAssets;
import com.aek.ebey.qc.model.MsCheck;
import com.aek.ebey.qc.query.MsAssetsQuery;
import com.aek.ebey.qc.request.EditMsAssets;
import com.aek.ebey.qc.request.MsAssetsResponse;
import com.aek.ebey.qc.service.MsAssetsService;
import com.aek.ebey.qc.utils.QcUtils;
import com.aek.ebey.qc.utils.ToolUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 计量设备信息表 服务实现类
 * </p>
 *
 * @author Honghui
 * @since 2018-04-25
 */
@Service
@Transactional
public class MsAssetsServiceImpl extends BaseServiceImpl<MsAssetsMapper, MsAssets> implements MsAssetsService {

	@Autowired
	private MsAssetsMapper msAssetsMapper;

	@Autowired
	private MsCheckMapper msCheckMapper;

	@Override
	@Transactional(readOnly = true)
	public LiuHuiPage<MsAssetsResponse> search(MsAssetsQuery query) {
		AuthUser authUser = WebSecurityUtils.getCurrentUser();
		Page<MsAssetsResponse> pageMsAssetsResponse = query.getPagePlus();
		List<MsAssetsResponse> list = msAssetsMapper.search(pageMsAssetsResponse, query, authUser);
		LiuHuiPage<MsAssetsResponse> page = new LiuHuiPage<>(pageMsAssetsResponse);
		if (list != null && list.size() > 0) {
			for (MsAssetsResponse msAssetsResponse : list) {
				if (msAssetsResponse.getNextCheckTime() != null) {
					msAssetsResponse
							.setAttention(QcUtils.getAttentionMs(new Date(), msAssetsResponse.getNextCheckTime()));
				}

			}
		}
		page.setRecords(list);
		return page;
	}

	@Override
	public void updateMsAssets(EditMsAssets request) {
		msAssetsMapper.updateMsAssets(request);
		Wrapper<MsCheck> wrapper = new EntityWrapper<>();
		wrapper.eq("measure_assets_id", request.getId());
		List<MsCheck> list = msCheckMapper.selectList(wrapper);
		MsAssets msAssets = msAssetsMapper.selectById(request.getId());
		if (list != null && !list.isEmpty()) {
			// 不为空 说明有检测
		    MsCheck  msCheck = list.get(0);
		    if (msCheck.getCheckStatus() == StatusConstants.UNCHECK_STATUS) {
		        MsCheck editMsCheck = BeanMapper.map(msAssets, MsCheck.class);
		        editMsCheck.setId(msCheck.getId());
		        editMsCheck.setCheckStatus(StatusConstants.UNCHECK_STATUS);
		        editMsCheck.setMeasureAssetsId(request.getId());
		        msCheckMapper.updateById(editMsCheck); 
		    }
			return;
		} else {
			if (request.getStatus().intValue() == MsStatus.TWO.getCode()) {
				MsCheck msCheck = BeanMapper.map(msAssets, MsCheck.class);
				msCheck.setId(null);
				msCheck.setCheckStatus(StatusConstants.UNCHECK_STATUS);
				msCheck.setMeasureAssetsId(msAssets.getId());
				msCheckMapper.insert(msCheck);
				return;
			}
		}

	}

	@Override
	public void insertMsMsAssetsAndCheck(MsAssets request) {
		msAssetsMapper.insert(request);
		MsCheck msCheck = BeanMapper.map(request, MsCheck.class);
		msCheck.setId(null);
		msCheck.setCheckStatus(StatusConstants.UNCHECK_STATUS);
		msCheck.setMeasureAssetsId(request.getId());
		msCheckMapper.insert(msCheck);
	}

	@Override
	public void saveOrUpdate(MsAssets request) {
	    //默认非强制计量设备
	    request.setMeasureManageType(Constants.UNFORCE_MEASURE_ASSETS);
		Long assetsId = request.getAssetsId();
		if (assetsId != null) {
			Wrapper<MsAssets> wrapper=new EntityWrapper<>();
			wrapper.eq("assets_id", assetsId);
			List<MsAssets> list=msAssetsMapper.selectList(wrapper);
			if(list!=null&&list.size()>0){
				MsAssets msAssets=list.get(0);
				request.setId(msAssets.getId());
				request.setMeasureNum(msAssets.getMeasureNum());
				if (ToolUtil.isOneEmpty(msAssets.getMeasureType(), msAssets.getMeasureCategory(),
						msAssets.getMeasureCycle(), msAssets.getNextCheckTime(), msAssets.getCheckMode(),
						msAssets.getChargeUserId())) {
					request.setStatus(MsStatus.ONE.getCode());
				} else {
					request.setStatus(MsStatus.TWO.getCode());
				}
				msAssetsMapper.updateAllColumnById(request);
				
			    Wrapper<MsCheck> msCheckWrapper = new EntityWrapper<MsCheck>();
                msCheckWrapper.eq("measure_assets_id", msAssets.getId());
                msCheckWrapper.eq("tenant_id", msAssets.getTenantId());
                msCheckWrapper.eq("assets_id", msAssets.getAssetsId());
                msCheckWrapper.eq("check_status", StatusConstants.UNCHECK_STATUS);
				//若将计量台账信息改为非计量台账，则将未检测的检测信息删除
				if (request.getDelFlag()) {
				    msCheckMapper.delete(msCheckWrapper);
				    //清除计量台账信息计量信息，保留计量编号，改为待完善状态
				    MsAssets msAssetsDb = msAssetsMapper.selectById(request.getId());
				    msAssetsDb.setMeasureManageType(null);
				    msAssetsDb.setMeasureType(null);
				    msAssetsDb.setMeasureCategory(null);
				    msAssetsDb.setMeasureCycle(null);
				    msAssetsDb.setNextCheckTime(null);
				    msAssetsDb.setCheckMode(null);
				    msAssetsDb.setChargeUserId(null);
				    msAssetsDb.setChargeUserName(null);
				    msAssetsDb.setRemarks(null);
				    msAssetsDb.setStatus(1);
				    msAssetsMapper.updateAllColumnById(msAssetsDb);
				} else {
				    //同步更新计量检测未检测信息
                    MsAssets newMsAssets = msAssetsMapper.selectById(msAssets.getId());
                    MsCheck msCheck = BeanMapper.map(newMsAssets, MsCheck.class);
                    msCheck.setMeasureAssetsId(newMsAssets.getId());
                    msCheck.setCheckStatus(StatusConstants.UNCHECK_STATUS);
                    msCheckMapper.update(msCheck, msCheckWrapper);
				} 
			}else{
				request.setMeasureNum(QcUtils.mSno());
				request.setAssetsType(1);
				request.setStatus(MsStatus.ONE.getCode());
				msAssetsMapper.insert(request);
			}
		}
	}

}
