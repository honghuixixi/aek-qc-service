package com.aek.ebey.qc.mapper;

import com.aek.ebey.qc.model.MdReportAssets;
import com.aek.ebey.qc.model.bo.MdAssetsBO;

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
public interface MdReportAssetsMapper extends BaseMapper<MdReportAssets> {

	public int updateReportAssets(@Param("mdAsset")MdAssetsBO mdAsset);
	
	public int getAssetsById(@Param("mdReportId")Long mdReportId,@Param("assetsId")Long assetsId);
}