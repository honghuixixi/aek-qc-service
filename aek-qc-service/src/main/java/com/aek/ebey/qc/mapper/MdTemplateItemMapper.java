package com.aek.ebey.qc.mapper;

import com.aek.ebey.qc.model.MdTemplateItem;
import com.aek.ebey.qc.model.bo.MdSysTemplateItemBO;
import com.aek.ebey.qc.model.vo.MdTemplateItemVO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aek.common.core.base.BaseMapper;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author Honghui
 * @since 2018-05-16
 */
public interface MdTemplateItemMapper extends BaseMapper<MdTemplateItem> {

	List<MdTemplateItemVO> getMdSysTemplateItem(@Param("templateId")Long templateId);
	
	List<MdSysTemplateItemBO> getMdSysTemplateItemList(@Param("templateId")Long templateId);
}