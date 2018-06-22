package com.aek.ebey.qc.service.impl;

import com.aek.ebey.qc.model.MtAssets;
import com.aek.ebey.qc.model.MtPlan;
import com.aek.ebey.qc.model.MtPlanAssets;
import com.aek.ebey.qc.model.MtPlanImplement;
import com.aek.ebey.qc.model.MtPlanTemplate;
import com.aek.ebey.qc.model.MtPlanTemplateItem;
import com.aek.ebey.qc.model.MtTemplate;
import com.aek.ebey.qc.model.MtTemplateItem;
import com.aek.ebey.qc.model.vo.MtPlanDetailVO;
import com.aek.ebey.qc.model.vo.MtPlanResponseVO;
import com.aek.ebey.qc.query.MtPlanQuery;
import com.aek.ebey.qc.request.MtPlanEditRequest;
import com.aek.ebey.qc.mapper.MtPlanAssetsMapper;
import com.aek.ebey.qc.mapper.MtPlanImplementMapper;
import com.aek.ebey.qc.mapper.MtPlanMapper;
import com.aek.ebey.qc.mapper.MtPlanTemplateItemMapper;
import com.aek.ebey.qc.mapper.MtPlanTemplateMapper;
import com.aek.ebey.qc.mapper.MtTemplateItemMapper;
import com.aek.ebey.qc.mapper.MtTemplateMapper;
import com.aek.ebey.qc.service.MtPlanService;
import com.aek.ebey.qc.service.feign.AssetsClientService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Lists;
import com.aek.common.core.BeanMapper;
import com.aek.common.core.Result;
import com.aek.common.core.base.BaseServiceImpl;
import com.aek.common.core.exception.ExceptionFactory;
import com.aek.common.core.serurity.WebSecurityUtils;
import com.aek.common.core.serurity.model.AuthUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 保养计划 服务实现类
 *
 * @author Honghui
 * @since 2018-03-21
 */
@Service
@Transactional
public class MtPlanServiceImpl extends BaseServiceImpl<MtPlanMapper, MtPlan> implements MtPlanService {

	@Autowired
	private MtPlanMapper mtPlanMapper;
	
	@Autowired
	private MtPlanAssetsMapper mtPlanAssetsMapper;
	
	@Autowired
	private MtPlanTemplateMapper mtPlanTemplateMapper;
	
	@Autowired
	private MtTemplateMapper mtTemplateMapper;
	
	@Autowired
	private MtTemplateItemMapper mtTemplateItemMapper;
	
	@Autowired
	private MtPlanTemplateItemMapper mtPlanTemplateItemMapper;
	
	@Autowired
	private MtPlanImplementMapper mtPlanImplementMapper;
	
	@Autowired
	private AssetsClientService assetsClientService;
	
	@Override
	public void addPlan(MtPlan plan, List<MtAssets> mtAssetList) {
		AuthUser currentUser = WebSecurityUtils.getCurrentUser();
		Date now = new Date();
		MtTemplate mtTemplate = mtTemplateMapper.selectById(plan.getTemplateId());
		//校验模板是否可用未删除
		if(mtTemplate.getDelFlag()==true || mtTemplate.getEnable()==false)throw ExceptionFactory.create("M_015");
		//验证模版是否有项目
		Wrapper<MtTemplateItem> wrapper1 = new EntityWrapper<MtTemplateItem>();
		wrapper1.eq("template_id", plan.getTemplateId()).eq("del_flag", false);
		List<MtTemplateItem> notEmpty = mtTemplateItemMapper.selectList(wrapper1);
		if(notEmpty!=null&&notEmpty.size()<=0)throw ExceptionFactory.create("M_014");
		//收集设备id用于更新设备保养状态
	    Set<Long> assetIdSet = new HashSet<Long>();
		
		if(mtAssetList!=null&&mtAssetList.size()>0){
			for (MtAssets mtAsset: mtAssetList) {
				MtPlan planBo = BeanMapper.map(plan, MtPlan.class);			
				planBo.setEnable(true);	
				planBo.setCreateTime(now);
				planBo.setUpdateTime(now);
				planBo.setTenantId(currentUser.getTenantId());
				planBo.setTenantName(currentUser.getTenantName());
				mtPlanMapper.insert(planBo);
				
				MtPlanAssets mtPlanAsset = new MtPlanAssets();
				mtPlanAsset.setPlanId(planBo.getId());
				mtPlanAsset.setAssetsId(mtAsset.getId());
				mtPlanAsset.setAssetsNum(mtAsset.getAssetsNum());
				mtPlanAsset.setHospitalAssetsNum(mtAsset.getSerialNum());
				mtPlanAsset.setAssetsName(mtAsset.getAssetsName());
				mtPlanAsset.setAssetsSpec(mtAsset.getAssetsSpec());
				mtPlanAsset.setAssetsDeptId(mtAsset.getDeptId());
				mtPlanAsset.setAssetsDeptName(mtAsset.getDeptName());
				mtPlanAsset.setFactoryNum(mtAsset.getFactoryNum());
				mtPlanAsset.setCreateTime(now);
				mtPlanAssetsMapper.insert(mtPlanAsset);
				
				assetIdSet.add(mtAsset.getId());
				
				MtPlanImplement mtPlanImplement = new MtPlanImplement();
				mtPlanImplement.setPlanId(planBo.getId());
				mtPlanImplement.setAdministrator(planBo.getAdministrator());
				mtPlanImplement.setRate(planBo.getRate());
				mtPlanImplement.setCreateTime(now);
				mtPlanImplement.setNextImplementTime(planBo.getNextImplementTime());
				mtPlanImplementMapper.insert(mtPlanImplement);
				
				MtPlanTemplate mtPlanTemplate = new MtPlanTemplate();
				mtPlanTemplate.setTenantId(currentUser.getTenantId());
				mtPlanTemplate.setPlanId(planBo.getId());
				mtPlanTemplate.setTemplateId(plan.getTemplateId());
				mtPlanTemplate.setName(mtTemplate.getName());
				mtPlanTemplate.setType(mtTemplate.getType());
				mtPlanTemplate.setRemarks(mtTemplate.getRemarks());
				mtPlanTemplate.setEnable(true);
				mtPlanTemplate.setDelFlag(false);
				mtPlanTemplate.setCreateTime(now);
				mtPlanTemplate.setUpdateTime(now);
				mtPlanTemplateMapper.insert(mtPlanTemplate);
				
				planBo.setPlanTemplateId(mtPlanTemplate.getId());
				mtPlanMapper.updateById(planBo);
				
				Wrapper<MtTemplateItem> wrapper = new EntityWrapper<MtTemplateItem>();
				wrapper.eq("template_id", plan.getTemplateId()).eq("del_flag", false);
				List<MtTemplateItem> mtTemplateItemList = mtTemplateItemMapper.selectList(wrapper);
				for (MtTemplateItem mtTemplateItem : mtTemplateItemList) {
					MtPlanTemplateItem mtPlanTemplateItem = new MtPlanTemplateItem();
					mtPlanTemplateItem.setPlanTemplateId(mtPlanTemplate.getId());
					mtPlanTemplateItem.setItemName(mtTemplateItem.getItemName());
					mtPlanTemplateItem.setDelFlag(false);
					mtPlanTemplateItem.setCreateTime(now);
					mtPlanTemplateItem.setUpdateTime(now);
					mtPlanTemplateItemMapper.insert(mtPlanTemplateItem);
				}					
			}
			
			//批量更新设备mt_plan_flag=1字段
			List<Long> assetIdList = new ArrayList<Long>(assetIdSet);
			assetsClientService.changeMtPlanFlag(WebSecurityUtils.getCurrentToken(), assetIdList, 1);
		}
		
		
		
		
	}

	@Override
	public Page<MtPlanResponseVO> getPlanByPage(MtPlanQuery query) {
		Page<MtPlanResponseVO> page = query.getPage();
		AuthUser currentUser = WebSecurityUtils.getCurrentUser();
		String keyword = StringUtils.trimToNull(query.getKeyword());
		if(keyword !=null){
			if (keyword.startsWith("%") || keyword.startsWith("[") || keyword.startsWith("[]") || keyword.startsWith("_")) {
				query.setKeyword('\\'+keyword);
			}
		}
		List<MtPlanResponseVO> list = mtPlanMapper.getPlanByPage(page, query, currentUser);
		page.setRecords(list);
		return page;
	}

	@Override
	public void editPlan(MtPlanEditRequest plan) {
		Long id = plan.getId();
		MtPlan mtPlan = mtPlanMapper.selectById(id);
		if(mtPlan==null)return;
		mtPlan.setAdministrator(plan.getAdministrator());
		mtPlan.setNextImplementTime(plan.getNextImplementTime());
		mtPlanMapper.updateById(mtPlan);
		
		Wrapper<MtPlanImplement> wrapper = new EntityWrapper<MtPlanImplement>();
		wrapper.eq("plan_id", id);
		List<MtPlanImplement> list = mtPlanImplementMapper.selectList(wrapper);
		if(list!=null&&list.size()>0){
			MtPlanImplement mtPlanImplement = list.get(0);
			mtPlanImplement.setAdministrator(plan.getAdministrator());
			mtPlanImplement.setNextImplementTime(plan.getNextImplementTime());
			mtPlanImplementMapper.updateById(mtPlanImplement);
		}	
	}

	@Override
	public void disablePlan(Long id) {
		MtPlan mtPlan = mtPlanMapper.selectById(id);
		if(mtPlan==null)return;
		mtPlan.setEnable(false);
		mtPlanMapper.updateById(mtPlan);
		
		//更新设备mt_plan_flag=1字段
		List<Long> list = new ArrayList<Long>();
		list.add(id);
		assetsClientService.changeMtPlanFlag(WebSecurityUtils.getCurrentToken(), list, 0);
		
	}

	@Override
	public MtPlanDetailVO getPlanDetail(Long id) {
		MtPlan mtPlan = mtPlanMapper.selectById(id);
		if(mtPlan==null)return null;
		MtPlanDetailVO mtPlanDetail = BeanMapper.map(mtPlan, MtPlanDetailVO.class);
		Wrapper<MtPlanTemplate> wrapper1 = new EntityWrapper<MtPlanTemplate>();
		wrapper1.eq("plan_id", id);
		List<MtPlanTemplate> list1 = mtPlanTemplateMapper.selectList(wrapper1);
		Wrapper<MtPlanTemplateItem> wrapper2 = new EntityWrapper<MtPlanTemplateItem>();
		wrapper2.eq("plan_template_id", list1.get(0).getId());
		List<MtPlanTemplateItem> list2 = mtPlanTemplateItemMapper.selectList(wrapper2);
		mtPlanDetail.setMtPlanTemplateItems(list2);	
		Wrapper<MtPlanAssets> wrapper3 = new EntityWrapper<MtPlanAssets>();
		wrapper3.eq("plan_id", id);
		List<MtPlanAssets> list3 = mtPlanAssetsMapper.selectList(wrapper3);
		List<MtAssets> list4 = Lists.newArrayList();
		for (MtPlanAssets mtPlanAsset : list3) {
			MtAssets mtAsset = new MtAssets();
			mtAsset.setAssetsName(mtPlanAsset.getAssetsName());
			mtAsset.setId(mtPlanAsset.getId());
			mtAsset.setAssetsNum(mtPlanAsset.getAssetsNum());
			mtAsset.setAssetsSpec(mtPlanAsset.getAssetsSpec());
			mtAsset.setDeptName(mtPlanAsset.getAssetsDeptName());
			list4.add(mtAsset);
		}
		mtPlanDetail.setMtAssets(list4);
		return mtPlanDetail;
	}

	@Override
	public Boolean checkTemplate(Long id) {
		Wrapper<MtTemplateItem> wrapper = new EntityWrapper<MtTemplateItem>();
		wrapper.eq("template_id", id).eq("del_flag", false);
		List<MtTemplateItem> notEmpty = mtTemplateItemMapper.selectList(wrapper);
		if(notEmpty!=null&&notEmpty.size()>0)return true;
		return false;
	}
	
}
