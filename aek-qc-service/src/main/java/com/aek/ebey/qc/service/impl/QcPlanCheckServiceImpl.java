package com.aek.ebey.qc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aek.common.core.Result;
import com.aek.common.core.base.BaseServiceImpl;
import com.aek.common.core.exception.ExceptionFactory;
import com.aek.common.core.serurity.WebSecurityUtils;
import com.aek.common.core.serurity.model.AuthUser;
import com.aek.ebey.qc.inter.CheckStatus;
import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.mapper.QcImplementMapper;
import com.aek.ebey.qc.mapper.QcPlanCheckMapper;
import com.aek.ebey.qc.model.QcImplement;
import com.aek.ebey.qc.model.QcPlanCheck;
import com.aek.ebey.qc.query.QcPlanCheckQuery;
import com.aek.ebey.qc.request.QcPlanCheckResponse;
import com.aek.ebey.qc.service.QcPlanCheckService;
import com.aek.ebey.qc.service.feign.DeptClientService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * qc验收人员表 服务实现类
 * </p>
 *
 * @author Honghui
 * @since 2018-05-16
 */
@Service
@Transactional
public class QcPlanCheckServiceImpl extends BaseServiceImpl<QcPlanCheckMapper, QcPlanCheck> implements QcPlanCheckService {
	
	@Autowired
	private QcPlanCheckMapper qcPlanCheckMapper;
	
	@Autowired
	private QcImplementMapper qcImplementMapper;
	
	@Autowired
	private DeptClientService deptClientService;

	@Override
	public LiuHuiPage<QcPlanCheckResponse> search(QcPlanCheckQuery query) {
		AuthUser authUser = WebSecurityUtils.getCurrentUser();
		Page<QcPlanCheckResponse> pageResponse = query.getPagePlus();
		List<QcPlanCheckResponse> list=qcPlanCheckMapper.search(pageResponse,query,authUser);
		LiuHuiPage<QcPlanCheckResponse> page=new LiuHuiPage<>(pageResponse);
		page.setRecords(list);
		return page;
	}

	@Override
	public void check(Long id) {
		AuthUser  user=WebSecurityUtils.getCurrentUser();
		Result<Boolean> ret=deptClientService.checkUserPermission(user.getId(), "QC_CHECK_MANAGE", WebSecurityUtils.getCurrentToken());
		if(ret!=null){
			if(ret.getData()){
				Wrapper<QcPlanCheck> wrapper=new EntityWrapper<>();
				wrapper.eq("implement_id", id).eq("user_id", user.getId());
				List<QcPlanCheck> list=qcPlanCheckMapper.selectList(wrapper);
				if(list!=null&&list.size()>0){
					QcPlanCheck qcPlanCheck=list.get(0);
					if(qcPlanCheck.getStatus().intValue()!=CheckStatus.ONE.getCode()){
						throw ExceptionFactory.create("Q_019");
					}
					qcPlanCheck.setStatus(CheckStatus.TWO.getCode());
					
					qcPlanCheckMapper.updateById(qcPlanCheck);
					Wrapper<QcPlanCheck> wrapper2=new EntityWrapper<>();
					wrapper2.eq("implement_id", id).eq("status", CheckStatus.ONE.getCode());
					List<QcPlanCheck> list2=qcPlanCheckMapper.selectList(wrapper2);
					QcImplement qcImplement=qcImplementMapper.selectById(id);
					if(qcImplement!=null){
						if(list2!=null&&list2.size()>0){
							qcImplement.setIsCheck(CheckStatus.ONE.getCode());
						}else{
							qcImplement.setIsCheck(CheckStatus.TWO.getCode());
						}
						qcImplementMapper.updateById(qcImplement);
					}
					return ;
				}else{
					throw ExceptionFactory.create("Q_019");
				}
				
			}else{
				throw ExceptionFactory.create("Q_021");
			}
		}else{
			throw ExceptionFactory.create("Q_005");
		}
		
	}

	@Override
	public Integer getQcPlanCheckWaitToDo(Long id) {
		int i=0;
		Wrapper<QcPlanCheck> wrapper=new EntityWrapper<>();
		wrapper.eq("user_id", id).eq("status", CheckStatus.ONE.getCode()).eq("del_flag", false).isNotNull("implement_id");
		List<QcPlanCheck> list=qcPlanCheckMapper.selectList(wrapper);
		if(list!=null&&list.size()>0){
			i+=list.size();
		}
		return i;
	}
	
}
