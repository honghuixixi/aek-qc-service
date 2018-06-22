package com.aek.ebey.qc.service.impl;

import com.aek.ebey.qc.model.MtTemplate;
import com.aek.ebey.qc.model.MtTemplateItem;
import com.aek.ebey.qc.model.MtTemplateItemCommon;
import com.aek.ebey.qc.model.vo.MtTemplateVO;
import com.aek.ebey.qc.query.MtTemplateQuery;
import com.aek.ebey.qc.mapper.MtTemplateItemCommonMapper;
import com.aek.ebey.qc.mapper.MtTemplateItemMapper;
import com.aek.ebey.qc.mapper.MtTemplateMapper;
import com.aek.ebey.qc.service.MtTemplateItemService;
import com.aek.ebey.qc.service.MtTemplateService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.aek.common.core.BeanMapper;
import com.aek.common.core.base.BaseServiceImpl;
import com.aek.common.core.exception.ExceptionFactory;
import com.aek.common.core.serurity.WebSecurityUtils;
import com.aek.common.core.serurity.model.AuthUser;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 保养模板表 服务实现类
 *
 * @author Honghui
 * @since 2018-03-21
 */
@Service
@Transactional
public class MtTemplateServiceImpl extends BaseServiceImpl<MtTemplateMapper, MtTemplate> implements MtTemplateService {

	@Autowired
	private MtTemplateMapper mtTemplateMapper;
	@Autowired
	private MtTemplateItemMapper mtTemplateItemMapper;
	@Autowired
	private MtTemplateItemService mtTemplateItemService;
	@Autowired
	private MtTemplateItemCommonMapper mtTemplateItemCommonMapper;
	
	@Override
	public void add(MtTemplate mtp) {
		AuthUser user = WebSecurityUtils.getCurrentUser();
		String name = mtp.getName().trim();
		//模板名称验重
		MtTemplate exist = mtTemplateMapper.getByName(user, name);
		if(exist!=null)throw ExceptionFactory.create("M_005");		
		Date now = new Date();
		mtp.setCreateTime(now);
		mtp.setUpdateTime(now);
		mtp.setDelFlag(false);
		mtp.setEnable(true);
		//模板类型(1=系统模板，2=自定义模板)
		mtp.setType(2);
		mtp.setTenantId(user.getTenantId());
		mtTemplateMapper.insert(mtp);
	}

	@Override
	public void edit(MtTemplate mtp) {
		AuthUser user = WebSecurityUtils.getCurrentUser();
		Date now = new Date();
		MtTemplate mtpDb = mtTemplateMapper.selectById(mtp.getId());
		if(mtpDb==null)throw ExceptionFactory.create("M_006");
		if(mtpDb.getType()==1)throw ExceptionFactory.create("M_012");
		String nameDb = mtpDb.getName();
		String name = mtp.getName();
		if(!name.trim().equals(nameDb)){
			//模板名称验重
			MtTemplate exist = mtTemplateMapper.getByName(user, name);
			if(exist!=null)throw ExceptionFactory.create("M_005");
		}
		mtp.setUpdateTime(now);
		mtTemplateMapper.updateById(mtp);		
	}

	@Override
	public MtTemplateVO getDetail(Long id) {
		MtTemplate mtTemplate = mtTemplateMapper.selectById(id);
		if(mtTemplate==null)return null;
		MtTemplateVO mtTemplateVo = BeanMapper.map(mtTemplate, MtTemplateVO.class);
		Wrapper<MtTemplateItem> wrapper = new EntityWrapper<MtTemplateItem>();
		wrapper.eq("template_id", id).eq("del_flag", false);
		List<MtTemplateItem> items = mtTemplateItemMapper.selectList(wrapper);
		mtTemplateVo.setItems(items);
		return mtTemplateVo;
	}

	@Override
	public void delete(Long id) {
		MtTemplate mtTemplate = mtTemplateMapper.selectById(id);	
		if(mtTemplate==null)throw ExceptionFactory.create("M_007");//不存在
		Integer type = mtTemplate.getType();
		if(type==1)throw ExceptionFactory.create("M_008");//系统模版不可删除	
		//更新保养模板表
		Date now = new Date();
		mtTemplate.setEnable(false);
		mtTemplate.setDelFlag(true);
		mtTemplate.setUpdateTime(now);
		mtTemplateMapper.updateById(mtTemplate);
		//更新保养模板项目表
		MtTemplateItem mtTemplateItem = new MtTemplateItem();
		mtTemplateItem.setTemplateId(id);
		mtTemplateItem.setDelFlag(true);
		mtTemplateItem.setUpdateTime(now);
		Wrapper<MtTemplateItem> wrapper = new EntityWrapper<MtTemplateItem>();
		wrapper.eq("template_id", id);
		mtTemplateItemService.update(mtTemplateItem, wrapper);
	}

	@Override
	public void enableOnOff(Long id,Boolean enable) {
		MtTemplate mtTemplate = mtTemplateMapper.selectById(id);	
		if(mtTemplate==null)throw ExceptionFactory.create("M_007");//不存在		
		Date now = new Date();
		//模版可用开关
		if(enable==false){
			Integer type = mtTemplate.getType();
			if(type==1)throw ExceptionFactory.create("M_013");//系统模版不可停用
			mtTemplate.setEnable(false);
			mtTemplate.setUpdateTime(now);
			mtTemplateMapper.updateById(mtTemplate);
		}else {
			mtTemplate.setEnable(true);
			mtTemplate.setUpdateTime(now);
			mtTemplateMapper.updateById(mtTemplate);
		}
				
	}

	@Override
	public void addItem(Long templateId, List<MtTemplateItem> items) {
		MtTemplate mtTemplate = mtTemplateMapper.selectById(templateId);	
		if(mtTemplate==null)throw ExceptionFactory.create("M_007");//模版不存在
		Date now = new Date();
		for (MtTemplateItem ite : items) {
			ite.setCreateTime(now);
			ite.setUpdateTime(now);
			ite.setDelFlag(false);
			ite.setTemplateId(templateId);
		}
		mtTemplateItemService.insertBatch(items);
	}

	@Override
	public void editItem(MtTemplateItem item) {
		MtTemplateItem itemDb = mtTemplateItemMapper.selectById(item.getId());
		Long templateId = itemDb.getTemplateId();
		MtTemplate mtTemplate = mtTemplateMapper.selectById(templateId);	
		if(mtTemplate==null)throw ExceptionFactory.create("M_007");//模版不存在
		String itemName = item.getItemName();
		Date now = new Date();
		if(!itemName.equals(itemDb.getItemName())){
			//项目名称验重
			MtTemplateItem exsit = mtTemplateMapper.getByItemName(templateId, itemName);
			if(exsit!=null)throw ExceptionFactory.create("M_010");			
		}
		item.setUpdateTime(now);
		mtTemplateItemMapper.updateById(item);
	}

	@Override
	public void deleteItem(Long itemId) {
		MtTemplateItem mtTemplateItem = new MtTemplateItem();
		mtTemplateItem.setId(itemId);
		mtTemplateItem.setDelFlag(true);
		mtTemplateItem.setUpdateTime(new Date());
		mtTemplateItemMapper.updateById(mtTemplateItem);
	}

	@Override
	public Page<MtTemplate> getByPage(MtTemplateQuery query) {
		Page<MtTemplate> page = query.getPage();
		AuthUser currentUser = WebSecurityUtils.getCurrentUser();
		String keyword = StringUtils.trimToNull(query.getKeyword());
		if(keyword !=null){
			if (keyword.startsWith("%") || keyword.startsWith("[") || keyword.startsWith("[]") || keyword.startsWith("_")) {
				query.setKeyword('\\'+keyword);
			}
		}
		List<MtTemplate> list = mtTemplateMapper.getByPage(page, query, currentUser);
		page.setRecords(list);
		return page;
	}

	@Override
	public List<MtTemplateItemCommon> getSysItems() {
		return mtTemplateItemCommonMapper.getSysItems();
	}

	@Override
	public List<MtTemplateVO> getList(String keyword) {
		AuthUser currentUser = WebSecurityUtils.getCurrentUser();
		keyword = StringUtils.trimToNull(keyword);
		if(keyword !=null&&keyword!=""){
			if (keyword.startsWith("%") || keyword.startsWith("[") || keyword.startsWith("[]") || keyword.startsWith("_")) {
				keyword='\\'+keyword;
			}
		}
		List<MtTemplateVO> list = mtTemplateMapper.getList(keyword, currentUser);
		//过滤无项目的模板
		Iterator<MtTemplateVO> it = list.iterator();
		while (it.hasNext()) {
			MtTemplateVO mtTemplateVo = it.next();
			Wrapper<MtTemplateItem> wrapper1 = new EntityWrapper<MtTemplateItem>();
			wrapper1.eq("template_id", mtTemplateVo.getId()).eq("del_flag", false);
			List<MtTemplateItem> mtTemplateItems = mtTemplateItemMapper.selectList(wrapper1);
			if(mtTemplateItems!=null&&mtTemplateItems.size()<=0)it.remove();	
		}
		//模板关联项目
		if(list!=null&&list.size()>0){
			for (MtTemplateVO lt : list) {
				Wrapper<MtTemplateItem> wrapper = new EntityWrapper<MtTemplateItem>();
				wrapper.eq("template_id", lt.getId()).eq("del_flag", false);
				List<MtTemplateItem> itemList = mtTemplateItemMapper.selectList(wrapper);
				lt.setItems(itemList);
			}
		}
		return list;
	}
	
}
