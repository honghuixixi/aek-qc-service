package com.aek.ebey.qc.mapper;


import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.aek.common.core.base.BaseMapper;
import com.aek.ebey.qc.model.MdReportTemplateItemOption;
import com.aek.ebey.qc.model.MdTemplateItemOption;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author Honghui
 * @since 2018-05-16
 */
public interface MdTemplateItemOptionMapper extends BaseMapper<MdTemplateItemOption> {

	List<MdReportTemplateItemOption> getMdSysTemplateItemOptionList(@Param("itemId")Long itemId);
}