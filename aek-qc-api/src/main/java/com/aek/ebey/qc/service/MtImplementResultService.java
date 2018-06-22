package com.aek.ebey.qc.service;

import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.model.MtImplementResult;
import com.aek.ebey.qc.model.vo.MtRecordBatchReponseVO;
import com.aek.ebey.qc.query.MtImplementResultQuery;
import com.aek.ebey.qc.query.MtRecordBatchQuery;
import com.aek.ebey.qc.query.MtRecordQuery;
import com.aek.ebey.qc.request.MtImplementResultPageResponse;
import com.aek.ebey.qc.request.MtImplementResultRequest;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

import com.aek.common.core.base.BaseService;
import com.aek.common.core.serurity.model.AuthUser;

/**
 * 实施结果服务类
 *
 * @author Honghui
 * @since 2018-03-21
 */
public interface MtImplementResultService extends BaseService<MtImplementResult> {
	
	/**
	 * 提交实施报告内容
	 * @param request
	 */
	public MtImplementResult submitImplementResult(MtImplementResultRequest request);

	/**
	 * 实施报告分页列表
	 * @param page
	 * @param user
	 * @param query
	 * @return
	 */
	public LiuHuiPage<MtImplementResultPageResponse> getMtImplementResultPage(Page<MtImplementResultPageResponse> page,AuthUser user,MtImplementResultQuery query);
	
	public Page<MtImplementResult> getMtRecordPage(MtRecordQuery query);
	
	public MtRecordBatchReponseVO getMtRecordBatchPrint(MtRecordBatchQuery query);
}
