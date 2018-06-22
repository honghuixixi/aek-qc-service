package com.aek.ebey.qc.mapper;

import com.aek.ebey.qc.model.MtImplementResult;
import com.aek.ebey.qc.query.MtImplementResultQuery;
import com.aek.ebey.qc.query.MtRecordBatchQuery;
import com.aek.ebey.qc.query.MtRecordQuery;
import com.aek.ebey.qc.request.MtImplementResultPageResponse;
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
public interface MtImplementResultMapper extends BaseMapper<MtImplementResult> {

	/**
	 * 分页查询实施报告
	 * @param page
	 * @param user
	 * @param query
	 * @return
	 */
	public List<MtImplementResultPageResponse> getMtImplementResultPage(Page<MtImplementResultPageResponse> page,@Param("user")AuthUser user,@Param("q") MtImplementResultQuery query);
	
	public List<MtImplementResult> getMtRecordPage(Page<MtImplementResult> page,@Param("user")AuthUser user,@Param("q") MtRecordQuery query);
	
	public List<MtImplementResult> getMplementResultList(@Param("user")AuthUser user,@Param("q") MtRecordBatchQuery query);
}