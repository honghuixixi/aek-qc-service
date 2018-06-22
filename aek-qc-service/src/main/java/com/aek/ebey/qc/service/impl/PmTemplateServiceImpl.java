package com.aek.ebey.qc.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.aek.common.core.base.BaseServiceImpl;
import com.aek.common.core.exception.ExceptionFactory;
import com.aek.common.core.serurity.WebSecurityUtils;
import com.aek.common.core.serurity.model.AuthUser;
import com.aek.ebey.qc.inter.Constants;
import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.inter.StatusConstants;
import com.aek.ebey.qc.mapper.PmTemplateMapper;
import com.aek.ebey.qc.model.PmOption;
import com.aek.ebey.qc.model.PmProject;
import com.aek.ebey.qc.model.PmTemplate;
import com.aek.ebey.qc.model.QcPlan;
import com.aek.ebey.qc.query.PmTemplateQuery;
import com.aek.ebey.qc.query.Query;
import com.aek.ebey.qc.request.PmOptionRequest;
import com.aek.ebey.qc.request.PmProjectRequest;
import com.aek.ebey.qc.request.PmTemplateDetail;
import com.aek.ebey.qc.request.PmTemplateResponse;
import com.aek.ebey.qc.service.PmOptionService;
import com.aek.ebey.qc.service.PmProjectService;
import com.aek.ebey.qc.service.PmTemplateService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * PM模版 服务实现类
 * </p>
 *
 * @author aek
 * @since 2017-11-27
 */
@Service
@Transactional
public class PmTemplateServiceImpl extends BaseServiceImpl<PmTemplateMapper, PmTemplate> implements PmTemplateService {

	@Autowired
	private PmTemplateMapper pmTemplateMapper;
	
	
	@Autowired
	private PmProjectService pmProjectService;
	
	
	@Autowired
	private PmOptionService pmOptionService;
	
	
	@Override
	public void save(PmTemplate pmTemplate) {
		AuthUser authUser = WebSecurityUtils.getCurrentUser();
		if(authUser!=null){
			pmTemplate.setTenantId(authUser.getTenantId());
			pmTemplate.setTemplateType(Constants.CUSTOM_TYPE);
			pmTemplate.setStatus(StatusConstants.ONE);
		}
		Wrapper<PmTemplate> wrapperXt=new EntityWrapper<>();
		wrapperXt.eq("template_type", 1);
		List<PmTemplate> listxt=pmTemplateMapper.selectList(wrapperXt);
		if(listxt!=null&&listxt.size()>0){
			for(PmTemplate pm:listxt){
				if(pmTemplate.getName().equals(pm.getName())){
					throw ExceptionFactory.create("Q_008");
				}
			}
			
		}
		
		/*Wrapper<PmTemplate> wrapper=new EntityWrapper<>();
		wrapper.eq("tenant_id", pmTemplate.getTenantId()).eq("name", pmTemplate.getName()).eq("del_flag", false);*/
		Query query=new Query();
		query.setTenantId(pmTemplate.getTenantId());
		query.setName(pmTemplate.getName());
		query.setDelFlag(false);
		//List<QcPlan> listQcPlan=qcPlanMapper.selectList2(query);
		List<PmTemplate> list=pmTemplateMapper.selectList2(query);
		//List<PmTemplate> list=pmTemplateMapper.selectList(wrapper);
		if(list!=null&&list.size()>0){
			throw ExceptionFactory.create("Q_008");
		}
		this.pmTemplateMapper.insert(pmTemplate);
	}
	@Override
	public void edit(PmTemplate pmTemplate) {
		AuthUser authUser = WebSecurityUtils.getCurrentUser();
		if(authUser!=null){
			pmTemplate.setTenantId(authUser.getTenantId());
			pmTemplate.setTemplateType(Constants.CUSTOM_TYPE);
		}
		PmTemplate pm=pmTemplateMapper.selectById(pmTemplate.getId());
		if(pm!=null){
			Wrapper<PmTemplate> wrapperXt=new EntityWrapper<>();
			wrapperXt.eq("template_type", 1);
			List<PmTemplate> listxt=pmTemplateMapper.selectList(wrapperXt);
			if(listxt!=null&&listxt.size()>0){
				for(PmTemplate pm1:listxt){
					if(pmTemplate.getName().equals(pm1.getName())){
						throw ExceptionFactory.create("Q_008");
					}
				}
				
			}
			if(!pm.getName().equals(pmTemplate.getName())){
				/*Wrapper<PmTemplate> wrapper=new EntityWrapper<>();
				wrapper.eq("tenant_id", pmTemplate.getTenantId()).eq("name", pmTemplate.getName()).eq("del_flag", false);
				List<PmTemplate> list=pmTemplateMapper.selectList(wrapper);*/
				Query query=new Query();
				query.setTenantId(pmTemplate.getTenantId());
				query.setName(pmTemplate.getName());
				query.setDelFlag(false);
				List<PmTemplate> list=pmTemplateMapper.selectList2(query);
				if(list!=null&&list.size()>0){
					throw ExceptionFactory.create("Q_008");
				}
			}
			this.pmTemplateMapper.updateById(pmTemplate);
		}else{
			throw ExceptionFactory.create("G_006");
		}
		
		
	}
	@Override
	@Transactional(readOnly=true)
	public PmTemplateDetail getAllById(Long id) {
		PmTemplate  pmTemplate =pmTemplateMapper.selectById(id);
		if(pmTemplate!=null){
			PmTemplateDetail pmTemplateDetail=new PmTemplateDetail();
			pmTemplateDetail.setId(id);
			pmTemplateDetail.setName(pmTemplate.getName());
			pmTemplateDetail.setRemarks(pmTemplate.getRemarks());
			pmTemplateDetail.setStatus(pmTemplate.getStatus());
			pmTemplateDetail.setType(pmTemplate.getTemplateType());
			Wrapper<PmProject> wrapper =new EntityWrapper<>();
			wrapper.eq("template_id", id).eq("del_flag", false).eq("status", StatusConstants.ONE);
			List<PmProject> list=pmProjectService.selectList(wrapper);
			List<PmProjectRequest> listProjects=new ArrayList<PmProjectRequest>();
			if(list!=null&&list.size()>0){
				for(PmProject pmProject:list){
					PmProjectRequest pmProjectRequest=new PmProjectRequest();
					pmProjectRequest.setId(pmProject.getId());
					pmProjectRequest.setName(pmProject.getName());
					Wrapper<PmOption> optionwrapper =new EntityWrapper<>();
					optionwrapper.eq("project_id", pmProject.getId()).eq("del_flag", false).eq("status", StatusConstants.ONE);
					List<PmOption> listPmOption=pmOptionService.selectList(optionwrapper);
					List<PmOptionRequest> results=new ArrayList<PmOptionRequest>();
					if(listPmOption!=null&listPmOption.size()>0){
						for(PmOption pmOption:listPmOption){
							PmOptionRequest pmOptionRequest=new PmOptionRequest();
							pmOptionRequest.setId(pmOption.getId());
							pmOptionRequest.setName(pmOption.getName());
							results.add(pmOptionRequest);
						}
					}
					pmProjectRequest.setOptions(results);
					listProjects.add(pmProjectRequest);
				}

			}
			pmTemplateDetail.setItems(listProjects);
			return pmTemplateDetail;
		}
		return null;
	}
	@Override
	@Transactional(readOnly=true)
	public LiuHuiPage<PmTemplateResponse> search(PmTemplateQuery query) {
		AuthUser authUser = WebSecurityUtils.getCurrentUser();
		Page<PmTemplateResponse> pageQcTemplate = query.getPagePlus();
		List<PmTemplateResponse> list=pmTemplateMapper.search(pageQcTemplate,query,authUser);
		LiuHuiPage<PmTemplateResponse> page=new LiuHuiPage<>(pageQcTemplate);
		page.setRecords(list);
		return page;
	}
	@Override
	@Transactional(readOnly=true)
	public List<PmTemplateResponse> changeSearch(String keyword) {
		AuthUser authUser = WebSecurityUtils.getCurrentUser();
		List<PmTemplateResponse> list=pmTemplateMapper.changeSearch(keyword,authUser);
		return list;
	}
	@Override
	@Transactional(readOnly=true)
	public PmTemplateDetail getAllById2(Long id) {
		PmTemplate  pmTemplate =pmTemplateMapper.selectById(id);
		if(pmTemplate!=null&&pmTemplate.getStatus()==1){
			PmTemplateDetail pmTemplateDetail=new PmTemplateDetail();
			pmTemplateDetail.setId(id);
			pmTemplateDetail.setName(pmTemplate.getName());
			pmTemplateDetail.setRemarks(pmTemplate.getRemarks());
			pmTemplateDetail.setStatus(pmTemplate.getStatus());
			pmTemplateDetail.setType(pmTemplate.getTemplateType());
			Wrapper<PmProject> wrapper =new EntityWrapper<>();
			wrapper.eq("template_id", id).eq("del_flag", false).eq("status", StatusConstants.ONE);
			List<PmProject> list=pmProjectService.selectList(wrapper);
			List<PmProjectRequest> listProjects=new ArrayList<PmProjectRequest>();
			if(list!=null&&list.size()>0){
				for(PmProject pmProject:list){
					PmProjectRequest pmProjectRequest=new PmProjectRequest();
					pmProjectRequest.setId(pmProject.getId());
					pmProjectRequest.setName(pmProject.getName());
					Wrapper<PmOption> optionwrapper =new EntityWrapper<>();
					optionwrapper.eq("project_id", pmProject.getId()).eq("del_flag", false).eq("status", StatusConstants.ONE);
					List<PmOption> listPmOption=pmOptionService.selectList(optionwrapper);
					List<PmOptionRequest> results=new ArrayList<PmOptionRequest>();
					if(listPmOption!=null&listPmOption.size()>0){
						for(PmOption pmOption:listPmOption){
							PmOptionRequest pmOptionRequest=new PmOptionRequest();
							pmOptionRequest.setId(pmOption.getId());
							pmOptionRequest.setName(pmOption.getName());
							results.add(pmOptionRequest);
						}
					}
					pmProjectRequest.setOptions(results);
					listProjects.add(pmProjectRequest);
				}

			}
			pmTemplateDetail.setItems(listProjects);
			return pmTemplateDetail;
		}else{
			throw ExceptionFactory.create("Q_013");
		}
	}
	
}
