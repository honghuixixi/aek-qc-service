package com.aek.ebey.qc.service;

import com.aek.ebey.qc.model.MtTemplate;
import com.aek.ebey.qc.model.MtTemplateItem;
import com.aek.ebey.qc.model.MtTemplateItemCommon;
import com.aek.ebey.qc.model.vo.MtTemplateVO;
import com.aek.ebey.qc.query.MtTemplateQuery;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

import com.aek.common.core.base.BaseService;

/**
 * 保养模板服务类
 *
 * @author Honghui
 * @since 2018-03-21
 */
public interface MtTemplateService extends BaseService<MtTemplate> {
	
	/**
	 * 新建保养模版
	 * @param mtp
	 */
	public void add(MtTemplate mtp);
	
	/**
	 * 编辑保养模版
	 * @param mtp
	 */
	public void edit(MtTemplate mtp);
	
	/**
	 * 根据id删除保养模版
	 * @param id
	 */
	public void delete(Long id);
	
	/**
	 * 根据id停用保养模版
	 * @param id
	 */
	public void enableOnOff(Long id,Boolean status);
	
	/**
	 * 根据id查询保养模版
	 * @param id
	 * @return
	 */
	public MtTemplateVO getDetail(Long id);
	
	/**
	 * 查询系统项目
	 * @return
	 */
	public List<MtTemplateItemCommon> getSysItems();
	
	/**
	 * 添加项目
	 * @param templateId
	 * @param items
	 */
	public void addItem(Long templateId,List<MtTemplateItem> items);
	
	/**
	 * 编辑项目
	 * @param item
	 */
	public void editItem(MtTemplateItem item);
	
	/**
	 * 删除项目
	 * @param itemId
	 */
	public void deleteItem(Long itemId);
	
	/**
	 * 模版分页查询
	 * @param query
	 * @return
	 */
	public Page<MtTemplate> getByPage(MtTemplateQuery query);
	
	/**
	 * 新建保养计划查保养模版集合
	 * @param keyword
	 * @return
	 */
	public List<MtTemplateVO> getList(String keyword);
}
