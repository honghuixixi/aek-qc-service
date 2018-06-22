package com.aek.ebey.qc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aek.common.core.base.BaseServiceImpl;
import com.aek.ebey.qc.inter.StatusConstants;
import com.aek.ebey.qc.mapper.PmProjectMapper;
import com.aek.ebey.qc.model.PmOption;
import com.aek.ebey.qc.model.PmProject;
import com.aek.ebey.qc.model.PmTemplate;
import com.aek.ebey.qc.request.PmProjectRequest2;
import com.aek.ebey.qc.service.PmOptionService;
import com.aek.ebey.qc.service.PmProjectService;
import com.aek.ebey.qc.service.PmTemplateService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

/**
 * <p>
 * PM项目 服务实现类
 * </p>
 *
 * @author aek
 * @since 2017-11-27
 */
@Service
@Transactional
public class PmProjectServiceImpl extends BaseServiceImpl<PmProjectMapper, PmProject> implements PmProjectService {
	
	@Autowired
	private PmProjectMapper pmProjectMapper;
	
	@Autowired
	private PmTemplateService pmTemplateService;
	
	@Autowired
	private PmOptionService pmOptionService;

	@Override
	public void save(PmProjectRequest2 pmProjectRequest) {
		if(pmProjectRequest!=null){
			PmProject pmProject=new PmProject();
			List<PmOption> list=new  ArrayList<>();
			pmProject.setName(pmProjectRequest.getName());
			pmProject.setTemplateId(pmProjectRequest.getId());
			pmProject.setStatus(StatusConstants.ONE);
			this.pmProjectMapper.insert(pmProject);
			PmTemplate  pmTemplate =pmTemplateService.selectById(pmProjectRequest.getId());
			if(pmTemplate!=null){
				pmTemplate.setHasProject(true);
				pmTemplateService.updateById(pmTemplate);
			}
			String[] pmOption=pmProjectRequest.getOptions();
			if(pmOption!=null&&pmOption.length>0){
				for(String name:pmOption){
					PmOption qcOption=new PmOption();
					qcOption.setName(name);
					qcOption.setProjectId(pmProject.getId());
					qcOption.setStatus(StatusConstants.ONE);
					list.add(qcOption);
				}
				
			}
			if(list.size()>0){
				pmOptionService.insertBatch(list);
			}
		}
		
	}

	@Override
	public void edit(PmProjectRequest2 request) {
		if (request != null) {
			List<PmOption> list = new ArrayList<>();
			PmProject pmProject = pmProjectMapper.selectById(request.getId());
			if (pmProject != null) {
				pmProject.setName(request.getName());
				this.pmProjectMapper.updateById(pmProject);
			} 
			Wrapper<PmOption> wrapper=new EntityWrapper<>();
			wrapper.eq("project_id", request.getId());
			//删除
			pmOptionService.delete(wrapper);
			String[] pmOption = request.getOptions();
			if(pmOption!=null&&pmOption.length>0){
				for(String name:pmOption){
					PmOption pmOption1=new PmOption();
					pmOption1.setProjectId(pmProject.getId());
					pmOption1.setName(name);
					pmOption1.setStatus(StatusConstants.ONE);
					list.add(pmOption1);
				}
				
			}
			//新增
			if(list.size()>0){
				pmOptionService.insertBatch(list);
				
			}

		}
	}

	@Override
	public void deleteProject(PmProject pmProject) {
		if (pmProject != null) {
			pmProject.setDelFlag(true);
			pmProjectMapper.updateById(pmProject);
			Wrapper<PmProject> wrapper = new EntityWrapper<>();
			wrapper.eq("template_id", pmProject.getTemplateId()).eq("del_flag", false);
			List<PmProject> list = pmProjectMapper.selectList(wrapper);
			PmTemplate pmTemplate = pmTemplateService.selectById(pmProject.getTemplateId());
			if (list != null && list.size() > 0) {
				if (pmTemplate != null) {
					pmTemplate.setHasProject(true);
					pmTemplateService.updateById(pmTemplate);
				}
			} else {
				if (pmTemplate != null) {
					pmTemplate.setHasProject(false);
					pmTemplateService.updateById(pmTemplate);
				}
			}
		} 
		
	}
	
}
