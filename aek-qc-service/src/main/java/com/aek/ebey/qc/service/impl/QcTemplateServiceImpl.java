package com.aek.ebey.qc.service.impl;

import com.aek.ebey.qc.model.PmTemplate;
import com.aek.ebey.qc.model.QcOption;
import com.aek.ebey.qc.model.QcOptionHelp;
import com.aek.ebey.qc.model.QcProject;
import com.aek.ebey.qc.model.QcProjectHelp;
import com.aek.ebey.qc.model.QcTemplate;
import com.aek.ebey.qc.model.QcTemplateHelp;
import com.aek.ebey.qc.query.QcTemplateQuery;
import com.aek.ebey.qc.query.Query;
import com.aek.ebey.qc.request.QcOptionRequest2;
import com.aek.ebey.qc.request.QcProjectRequest3;
import com.aek.ebey.qc.request.QcTemplateDetail;
import com.aek.ebey.qc.request.QcTemplateResponse;
import com.aek.ebey.qc.inter.Constants;
import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.inter.StatusConstants;
import com.aek.ebey.qc.mapper.QcTemplateHelpMapper;
import com.aek.ebey.qc.mapper.QcTemplateMapper;
import com.aek.ebey.qc.service.QcOptionHelpService;
import com.aek.ebey.qc.service.QcOptionService;
import com.aek.ebey.qc.service.QcProjectHelpService;
import com.aek.ebey.qc.service.QcProjectService;
import com.aek.ebey.qc.service.QcTemplateHelpService;
import com.aek.ebey.qc.service.QcTemplateService;
import com.aek.ebey.qc.utils.QcUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.aek.common.core.base.BaseServiceImpl;
import com.aek.common.core.exception.ExceptionFactory;
import com.aek.common.core.serurity.WebSecurityUtils;
import com.aek.common.core.serurity.model.AuthUser;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 巡检模版 服务实现类
 * </p>
 *
 * @author aek
 * @since 2017-11-02
 */
@Service
@Transactional
public class QcTemplateServiceImpl extends BaseServiceImpl<QcTemplateMapper, QcTemplate> implements QcTemplateService {

	@Autowired
	private QcTemplateMapper qcTemplateMapper;
	
	@Autowired
	private QcTemplateHelpService qcTemplateHelpService;
	
	@Autowired
	private QcProjectService qcProjectService;
	
	@Autowired
	private QcProjectHelpService qcProjectHelpService;
	
	@Autowired
	private QcOptionService qcOptionService;
	
	@Autowired
	private QcOptionHelpService qcOptionHelpService;
	

	@Override
	public void save(QcTemplate qcTemplate) {
		AuthUser authUser = WebSecurityUtils.getCurrentUser();
		if(authUser!=null){
			qcTemplate.setTenantId(authUser.getTenantId());
			qcTemplate.setTemplateType(Constants.CUSTOM_TYPE);
			qcTemplate.setStatus(StatusConstants.ONE);
		}
		if("通用巡检模板".equals(qcTemplate.getName())){
			throw ExceptionFactory.create("Q_008");
		}
		
		Wrapper<QcTemplate> wrapperqc=new EntityWrapper<>();
		wrapperqc.eq("template_type", 1);
		List<QcTemplate> listqc=qcTemplateMapper.selectList(wrapperqc);
		if(listqc!=null&&listqc.size()>0){
			for(QcTemplate qc:listqc){
				if(qcTemplate.getName().equals(qc.getName())){
					throw ExceptionFactory.create("Q_008");
				}
			}
			
		}
		
		/*Wrapper<PmTemplate> wrapper=new EntityWrapper<>();
		wrapper.eq("tenant_id", pmTemplate.getTenantId()).eq("name", pmTemplate.getName()).eq("del_flag", false);*/
		Query query=new Query();
		query.setTenantId(qcTemplate.getTenantId());
		query.setName(qcTemplate.getName());
		query.setDelFlag(false);
		//List<QcPlan> listQcPlan=qcPlanMapper.selectList2(query);
		List<QcTemplate> list=qcTemplateMapper.selectList2(query);
		//Wrapper<QcTemplate> wrapper=new EntityWrapper<>();
		//wrapper.eq("tenant_id", qcTemplate.getTenantId()).eq("name", qcTemplate.getName()).eq("del_flag", false);
		//List<QcTemplate> list=qcTemplateMapper.selectList(wrapper);
		if(list!=null&&list.size()>0){
			throw ExceptionFactory.create("Q_008");
		}
		this.qcTemplateMapper.insert(qcTemplate);
	}

	@Override
	public void edit(QcTemplate qcTemplate) {
		AuthUser authUser = WebSecurityUtils.getCurrentUser();
		if(authUser!=null){
			qcTemplate.setTenantId(authUser.getTenantId());
			qcTemplate.setTemplateType(Constants.CUSTOM_TYPE);
		}
		QcTemplate qc=qcTemplateMapper.selectById(qcTemplate.getId());
		if(qc!=null){
			if(!qc.getName().equals(qcTemplate.getName())){
				/*Wrapper<QcTemplate> wrapper=new EntityWrapper<>();
				wrapper.eq("tenant_id", qcTemplate.getTenantId()).eq("name", qcTemplate.getName()).eq("del_flag", false);
				List<QcTemplate> list=qcTemplateMapper.selectList(wrapper);*/
				Query query=new Query();
				query.setTenantId(qcTemplate.getTenantId());
				query.setName(qcTemplate.getName());
				query.setDelFlag(false);
				//List<QcPlan> listQcPlan=qcPlanMapper.selectList2(query);
				List<QcTemplate> list=qcTemplateMapper.selectList2(query);
				if(list!=null&&list.size()>0){
					throw ExceptionFactory.create("Q_008");
				}
			}
			if("通用巡检模板".equals(qcTemplate.getName())){
				throw ExceptionFactory.create("Q_008");
			}
			this.qcTemplateMapper.updateById(qcTemplate);
		}
		
	}

	@Override
	@Transactional(readOnly=true)
	public LiuHuiPage<QcTemplateResponse> search(QcTemplateQuery query) {
		AuthUser authUser = WebSecurityUtils.getCurrentUser();
		Page<QcTemplateResponse> pageQcTemplate = query.getPagePlus();
		List<QcTemplateResponse> list=qcTemplateMapper.search(pageQcTemplate,query,authUser);
		LiuHuiPage<QcTemplateResponse> page=new LiuHuiPage<>(pageQcTemplate);
		if(list!=null&&list.size()>0){
			for(QcTemplateResponse qcTemplateResponse:list){
				if(Constants.SYSTEM_TYPE.intValue()==qcTemplateResponse.getTemplateType().intValue()){
					qcTemplateResponse.setType("系统模板");
				}
				if(Constants.CUSTOM_TYPE.intValue()==qcTemplateResponse.getTemplateType().intValue()){
					qcTemplateResponse.setType("自定义模板");
				}
			}
		}
		page.setRecords(list);
		return page;
	}

	@Override
	@Transactional(readOnly=true)
	public QcTemplateDetail getAllById(Long id) {
		QcTemplate  qcTemplate =qcTemplateMapper.selectById(id);
		if(qcTemplate!=null){
			QcTemplateDetail qcTemplateDetail=new QcTemplateDetail();
			qcTemplateDetail.setName(qcTemplate.getName());
			qcTemplateDetail.setRemarks(qcTemplate.getRemarks());
			qcTemplateDetail.setStatus(QcUtils.getStatus(qcTemplate.getStatus()));
			qcTemplateDetail.setType(qcTemplate.getTemplateType());
			Wrapper<QcProject> wrapper =new EntityWrapper<>();
			wrapper.eq("template_id", id).eq("del_flag", false).eq("status", StatusConstants.ONE);
			List<QcProject> list=qcProjectService.selectList(wrapper);
			List<QcProjectRequest3> listProjects=new ArrayList<QcProjectRequest3>();
			if(list!=null&&list.size()>0){
				for(QcProject qcProject:list){
					QcProjectRequest3 qcProjectRequest3=new QcProjectRequest3();
					qcProjectRequest3.setId(qcProject.getId());
					qcProjectRequest3.setName(qcProject.getName());
					Wrapper<QcOption> optionwrapper =new EntityWrapper<>();
					optionwrapper.eq("project_id", qcProject.getId()).eq("del_flag", false).eq("status", StatusConstants.ONE);
					List<QcOption> listQcOption=qcOptionService.selectList(optionwrapper);
					if(listQcOption!=null&listQcOption.size()>0){
						List<QcOptionRequest2> results=new ArrayList<QcOptionRequest2>();
						for(QcOption qcOption:listQcOption){
							QcOptionRequest2 qcOptionRequest2=new QcOptionRequest2();
							qcOptionRequest2.setName(qcOption.getName());
							qcOptionRequest2.setIsDefault(qcOption.getHaveDefault());
							results.add(qcOptionRequest2);
						}
						qcProjectRequest3.setResults(results);
					}
					listProjects.add(qcProjectRequest3);
				}

				qcTemplateDetail.setProjects(listProjects);
			}
			return qcTemplateDetail;
		}
		
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public QcTemplateDetail getAllById2(Long id) {
		QcTemplateHelp  qcTemplateHelp =qcTemplateHelpService.selectById(id);
		if(qcTemplateHelp!=null){
			QcTemplateDetail qcTemplateDetail=new QcTemplateDetail();
			qcTemplateDetail.setName(qcTemplateHelp.getName());
			qcTemplateDetail.setRemarks(qcTemplateHelp.getRemarks());
			qcTemplateDetail.setStatus(QcUtils.getStatus(qcTemplateHelp.getStatus()));
			Wrapper<QcProjectHelp> wrapper =new EntityWrapper<>();
			wrapper.eq("template_id", id).eq("status", StatusConstants.ONE);
			List<QcProjectHelp> list=qcProjectHelpService.selectList(wrapper);
			List<QcProjectRequest3> listProjects=new ArrayList<QcProjectRequest3>();
			
			if(list!=null&&list.size()>0){
				for(QcProjectHelp qcProjectHelp:list){
					QcProjectRequest3 qcProjectRequest3=new QcProjectRequest3();
					qcProjectRequest3.setId(qcProjectHelp.getId());
					qcProjectRequest3.setName(qcProjectHelp.getName());
					Wrapper<QcOptionHelp> optionwrapper =new EntityWrapper<>();
					optionwrapper.eq("project_id", qcProjectHelp.getId()).eq("status", StatusConstants.ONE);
					List<QcOptionHelp> listQcOption=qcOptionHelpService.selectList(optionwrapper);
					if(listQcOption!=null&listQcOption.size()>0){
						List<QcOptionRequest2> results=new ArrayList<QcOptionRequest2>();
						for(QcOptionHelp qcOptionHelp:listQcOption){
							QcOptionRequest2 qcOptionRequest2=new QcOptionRequest2();
							qcOptionRequest2.setName(qcOptionHelp.getName());
							qcOptionRequest2.setIsDefault(qcOptionHelp.getHaveDefault());
							results.add(qcOptionRequest2);
						}
						qcProjectRequest3.setResults(results);
					}
					listProjects.add(qcProjectRequest3);
				}

				qcTemplateDetail.setProjects(listProjects);
			}
			return qcTemplateDetail;
		}
		
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public List<QcTemplateResponse> changeSearch(String keyword) {
		AuthUser authUser = WebSecurityUtils.getCurrentUser();
		List<QcTemplateResponse> list=qcTemplateMapper.changeSearch(keyword,authUser);
		return list;
	}
	
}
