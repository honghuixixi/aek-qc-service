package com.aek.ebey.qc.mapper;

import com.aek.ebey.qc.model.MtTemplateItemCommon;

import java.util.List;

import com.aek.common.core.base.BaseMapper;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author Honghui
 * @since 2018-03-23
 */
public interface MtTemplateItemCommonMapper extends BaseMapper<MtTemplateItemCommon> {

	/**
	 * 查询系统项目
	 * @return
	 */
	List<MtTemplateItemCommon> getSysItems();
}