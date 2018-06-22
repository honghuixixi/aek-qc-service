package com.aek.ebey.qc.service.impl;

import com.aek.ebey.qc.model.MsCheck;
import com.aek.ebey.qc.query.MsAssetsQuery;
import com.aek.ebey.qc.request.MsAssetsResponse;
import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.mapper.MsCheckMapper;
import com.aek.ebey.qc.service.MsCheckService;
import com.aek.ebey.qc.utils.QcUtils;
import com.baomidou.mybatisplus.plugins.Page;
import com.aek.common.core.base.BaseServiceImpl;
import com.aek.common.core.serurity.WebSecurityUtils;
import com.aek.common.core.serurity.model.AuthUser;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class MsCheckServiceImpl extends BaseServiceImpl<MsCheckMapper, MsCheck> implements MsCheckService {
	
	@Autowired
	private MsCheckMapper msCheckMapper;

	@Override
	@Transactional(readOnly=true)
	public LiuHuiPage<MsAssetsResponse> search(MsAssetsQuery query) {
		AuthUser authUser = WebSecurityUtils.getCurrentUser();
		Page<MsAssetsResponse> pageMsAssetsResponse = query.getPagePlus();
		List<MsAssetsResponse> list=msCheckMapper.search(pageMsAssetsResponse,query,authUser);
		LiuHuiPage<MsAssetsResponse> page=new LiuHuiPage<>(pageMsAssetsResponse);
		if(list!=null&&list.size()>0){
			for(MsAssetsResponse msAssetsResponse:list){
				if(msAssetsResponse.getNextCheckTime()!=null){
					msAssetsResponse.setAttention(QcUtils.getAttentionMs(new Date(),msAssetsResponse.getNextCheckTime()));
				}
				
			}
		}
		page.setRecords(list);
		return page;
	}
	
}
