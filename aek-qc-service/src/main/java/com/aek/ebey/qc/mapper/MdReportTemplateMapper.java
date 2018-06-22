package com.aek.ebey.qc.mapper;

import com.aek.ebey.qc.model.MdReportTemplate;
import com.aek.ebey.qc.model.vo.MdReportTemplateChildItemVO;
import com.aek.ebey.qc.model.vo.MdReportTemplateDetailVO;
import com.aek.ebey.qc.model.vo.MdReportTemplateItemVO;

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
public interface MdReportTemplateMapper extends BaseMapper<MdReportTemplate> {

	MdReportTemplateDetailVO getReportTemplate(@Param("ReportId")Long ReportId);
	
	/**
	 * 一级项目查询
	 * @param templateId
	 * @return
	 */
	List<MdReportTemplateItemVO> getReportTemplateItemList(@Param("templateId")Long templateId);
	
	/**
	 * 二级项目查询
	 * @param parentItemId
	 * @return
	 */
	List<MdReportTemplateChildItemVO> getReportTemplateChidItemList(@Param("parentItemId")Long parentItemId);
}