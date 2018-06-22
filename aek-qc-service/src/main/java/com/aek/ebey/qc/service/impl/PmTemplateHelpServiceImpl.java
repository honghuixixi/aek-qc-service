package com.aek.ebey.qc.service.impl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.aek.common.core.base.BaseServiceImpl;
import com.aek.ebey.qc.inter.StatusConstants;
import com.aek.ebey.qc.mapper.PmOptionHelpMapper;
import com.aek.ebey.qc.mapper.PmProjectHelpMapper;
import com.aek.ebey.qc.mapper.PmTemplateHelpMapper;
import com.aek.ebey.qc.model.PmOptionHelp;
import com.aek.ebey.qc.model.PmProjectHelp;
import com.aek.ebey.qc.model.PmTemplateHelp;
import com.aek.ebey.qc.request.PmProjectDetail;
import com.aek.ebey.qc.service.PmTemplateHelpService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

/**
 * <p>
 * PM模版 服务实现类
 * </p>
 *
 * @author aek
 * @since 2017-11-28
 */
@Service
@Transactional
public class PmTemplateHelpServiceImpl extends BaseServiceImpl<PmTemplateHelpMapper, PmTemplateHelp> implements PmTemplateHelpService {


	@Autowired
	private PmTemplateHelpMapper pmTemplateHelpMapper;
	
	@Autowired
	private PmProjectHelpMapper pmProjectHelpMapper;
	
	@Autowired
	private PmOptionHelpMapper pmOptionHelpMapper;
	
	@Override
	@Transactional(readOnly=true)
	public List<PmProjectDetail> getAllById(Long id) {
		PmTemplateHelp  pmTemplateHelp =pmTemplateHelpMapper.selectById(id);
		if(pmTemplateHelp!=null){
			Wrapper<PmProjectHelp> wrapper =new EntityWrapper<>();
			wrapper.eq("template_id", id).eq("status", StatusConstants.ONE);
			List<PmProjectHelp> list=pmProjectHelpMapper.selectList(wrapper);
			if(list!=null&&list.size()>0){
				List<PmProjectDetail> listProjects=new ArrayList<PmProjectDetail>();
				for(PmProjectHelp pmProjectHelp:list){
					PmProjectDetail pmProjectDetail=new PmProjectDetail();
					pmProjectDetail.setName(pmProjectHelp.getName());
					Wrapper<PmOptionHelp> optionwrapper =new EntityWrapper<>();
					optionwrapper.eq("project_id", pmProjectHelp.getId()).eq("status", StatusConstants.ONE);
					List<PmOptionHelp> listPmOption=pmOptionHelpMapper.selectList(optionwrapper);
					if(listPmOption!=null&listPmOption.size()>0){
						String[] options=new String[listPmOption.size()];
						for(int i=0;i<listPmOption.size();i++){
							options[i]=listPmOption.get(i).getName();
						}
						pmProjectDetail.setOptions(options);
					}
					listProjects.add(pmProjectDetail);
				}
				return	listProjects;
			}
			return null;
		}
		return null;
	}
	
}
