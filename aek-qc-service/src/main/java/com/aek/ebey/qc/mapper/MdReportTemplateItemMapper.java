package com.aek.ebey.qc.mapper;

import com.aek.ebey.qc.model.MdReportTemplateItem;

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
public interface MdReportTemplateItemMapper extends BaseMapper<MdReportTemplateItem> {

	Long getCurrentIdBySourceItemId(@Param("sourceItemId")Long sourceItemId,@Param("templateId")Long templateId);
}