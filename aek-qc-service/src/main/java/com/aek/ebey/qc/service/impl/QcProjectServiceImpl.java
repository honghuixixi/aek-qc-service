package com.aek.ebey.qc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aek.common.core.base.BaseServiceImpl;
import com.aek.ebey.qc.inter.StatusConstants;
import com.aek.ebey.qc.mapper.QcOptionMapper;
import com.aek.ebey.qc.mapper.QcProjectMapper;
import com.aek.ebey.qc.mapper.QcTemplateMapper;
import com.aek.ebey.qc.model.QcOption;
import com.aek.ebey.qc.model.QcProject;
import com.aek.ebey.qc.model.QcTemplate;
import com.aek.ebey.qc.request.QcOptionRequest;
import com.aek.ebey.qc.request.QcProjectRequest;
import com.aek.ebey.qc.request.QcProjectRequest2;
import com.aek.ebey.qc.service.QcOptionService;
import com.aek.ebey.qc.service.QcProjectService;
import com.aek.ebey.qc.service.QcTemplateService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

/**
 * <p>
 * 项目 服务实现类
 * </p>
 *
 * @author aek
 * @since 2017-11-02
 */
@Service
@Transactional
public class QcProjectServiceImpl extends BaseServiceImpl<QcProjectMapper, QcProject> implements QcProjectService {

	
	@Autowired
	private QcTemplateService qcTemplateService;
	
	
	@Autowired
	private QcProjectMapper qcProjectMapper;
	
	@Autowired
	private QcOptionService qcOptionService;
	
	@Override
	public void save(QcProjectRequest qcProjectRequest) {
		if(qcProjectRequest!=null){
			QcProject qcProject=new QcProject();
			List<QcOption> list=new  ArrayList<>();
			qcProject.setName(qcProjectRequest.getName());
			qcProject.setTemplateId(qcProjectRequest.getId());
			qcProject.setStatus(StatusConstants.ONE);
			this.qcProjectMapper.insert(qcProject);
			QcTemplate  qcTemplate =qcTemplateService.selectById(qcProjectRequest.getId());
			if(qcTemplate!=null){
				qcTemplate.setHasProject(true);
				qcTemplateService.updateById(qcTemplate);
			}
			List<QcOptionRequest> listQcOption=qcProjectRequest.getList();
			if(listQcOption!=null&&listQcOption.size()>0){
				for(QcOptionRequest o:listQcOption){
					QcOption qcOption=new QcOption();
					qcOption.setHaveDefault(o.getIsDefault());
					qcOption.setProjectId(qcProject.getId());
					qcOption.setName(o.getName());
					qcOption.setStatus(StatusConstants.ONE);
					list.add(qcOption);
				}
				
			}
			if(list.size()>0){
				for(QcOption ocOption:list){
					qcOptionService.insert(ocOption);
				}
				
			}
		}
	}

	@Override
	public void edit(QcProjectRequest2 request) {
		if (request != null) {
			List<QcOption> list = new ArrayList<>();
			QcProject qcProject = qcProjectMapper.selectById(request.getId());
			if (qcProject != null) {
				qcProject.setName(request.getName());
				this.qcProjectMapper.updateById(qcProject);
			} 
			Wrapper<QcOption> wrapper=new EntityWrapper<>();
			wrapper.eq("project_id", request.getId());
			//删除
			qcOptionService.delete(wrapper);
			List<QcOptionRequest> listQcOption = request.getList();
			if(listQcOption!=null&&listQcOption.size()>0){
				for(QcOptionRequest o:listQcOption){
					QcOption qcOption=new QcOption();
					qcOption.setHaveDefault(o.getIsDefault());
					qcOption.setProjectId(qcProject.getId());
					qcOption.setName(o.getName());
					qcOption.setStatus(StatusConstants.ONE);
					list.add(qcOption);
				}
				
			}
			//新增
			if(list.size()>0){
				for(QcOption ocOption:list){
					qcOptionService.insert(ocOption);
				}
				
			}

		}
	}

	@Override
	public void deleteProject(QcProject qcProject) {
		if (qcProject != null) {
			qcProject.setDelFlag(true);
			qcProjectMapper.updateById(qcProject);
			Wrapper<QcProject> wrapper = new EntityWrapper<>();
			wrapper.eq("template_id", qcProject.getTemplateId()).eq("del_flag", false);
			List<QcProject> list = qcProjectMapper.selectList(wrapper);
			QcTemplate qcTemplate = qcTemplateService.selectById(qcProject.getTemplateId());
			if (list != null && list.size() > 0) {
				if (qcTemplate != null) {
					qcTemplate.setHasProject(true);
					qcTemplateService.updateById(qcTemplate);
				}
			} else {
				if (qcTemplate != null) {
					qcTemplate.setHasProject(false);
					qcTemplateService.updateById(qcTemplate);
				}
			}
		} 
		
	}
	
}
