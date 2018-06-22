package com.aek.ebey.qc.mapper;

import com.aek.ebey.qc.model.MtTemplate;
import com.aek.ebey.qc.model.MtTemplateItem;
import com.aek.ebey.qc.model.MtTemplateItemCommon;
import com.aek.ebey.qc.model.vo.MtTemplateVO;
import com.aek.ebey.qc.query.MtTemplateQuery;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aek.common.core.base.BaseMapper;
import com.aek.common.core.serurity.model.AuthUser;

/**
 * Mapper接口
 *
 * @author Honghui
 * @since 2018-03-21
 */
public interface MtTemplateMapper extends BaseMapper<MtTemplate> {

	/**
	 * 根据模版名称查询模版
	 * @param name
	 * @return
	 */
	MtTemplate getByName(@Param("user")AuthUser user,@Param("name")String name);
	
	/**
	 * 模版分页查询
	 * @param page
	 * @param query
	 * @return
	 */
	List<MtTemplate> getByPage(@Param("page")Page<MtTemplate> page,@Param("query")MtTemplateQuery query,@Param("user")AuthUser user);
	
	/**
	 * 新建保养计划查保养模版集合
	 * @param page
	 * @param query
	 * @param user
	 * @return
	 */
	List<MtTemplateVO> getList(@Param("keyword")String keyword,@Param("user")AuthUser user);
	
	/**
	 * 根据项目名称查项目
	 * @param templateId
	 * @param itemName
	 * @return
	 */
	MtTemplateItem getByItemName(@Param("templateId")Long templateId,@Param("itemName")String itemName);
	
	/**
	 * 查询系统项目
	 * @return
	 */
	List<MtTemplateItemCommon> getSysItems();
}