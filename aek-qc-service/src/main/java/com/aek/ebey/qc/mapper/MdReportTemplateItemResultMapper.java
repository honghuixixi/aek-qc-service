package com.aek.ebey.qc.mapper;

import com.aek.ebey.qc.model.MdReportTemplateItemResult;
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
public interface MdReportTemplateItemResultMapper extends BaseMapper<MdReportTemplateItemResult> {
	
	List<String> getResultArrayByItemId(@Param("ChildItemId")Long ChildItemId);

	int deletResultByItemId(@Param("ids")List<Long> templateItemIds);
		
	List<MdReportTemplateItemResult> getAllItemsByTemplateId(@Param("templateId")Long templateId);
}