package com.aek.ebey.qc.mapper;

import com.aek.ebey.qc.model.MdReportTemplateItemOption;
import com.aek.ebey.qc.model.vo.MdReportTemplateItemOptionVO;

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
public interface MdReportTemplateItemOptionMapper extends BaseMapper<MdReportTemplateItemOption> {

	List<MdReportTemplateItemOptionVO> getChildItemOptions(@Param("parentItemId")Long parentItemId);
}