package com.aek.ebey.qc.service.feign;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.aek.common.core.Result;
import com.aek.ebey.qc.model.QcAssets;
import com.aek.ebey.qc.model.vo.MdAssetsVO;
import com.aek.ebey.qc.query.PmQAssets;
import com.aek.ebey.qc.request.AssetsInfoDetailReponse;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 断路器
 */
@Component
public class AssetsClientHystrix implements AssetsClientService {
	
	private static final Logger logger = LoggerFactory.getLogger(AssetsClientHystrix.class);

	@Override
	public Result<List<QcAssets>> getQcAssetsList(Long tenantId, String token, Long[] deptIds) {
		logger.error("tenantId="+String.valueOf(tenantId)+", deptIds="+deptIds);
		logger.error(token);
		return null;
	}

	@Override
	public Result<Page<QcAssets>> getQcAssetsPage(Page page, Long tenantId, String token, Long[] deptIds) {
		logger.error("tenantId="+String.valueOf(tenantId)+", deptIds="+deptIds);
		logger.error(token);
		return null;
	}

	@Override
	public Result<Object> updateAssetsPmPlanStatusByIds(String token, List<Long> assetsIds) {
		logger.error("assetsIds="+String.valueOf(assetsIds)+", assetsIds="+assetsIds);
		logger.error(token);
		return null;
	}

	@Override
	public Result<PmQAssets> getAssetsStatus(String token, Long assetsId) {
		logger.error("assetsId="+String.valueOf(assetsId)+", assetsId="+assetsId);
		logger.error(token);
		return null;
	}

	@Override
	public Result<AssetsInfoDetailReponse> getAssetsDetail(String token, Long assetsId) {
		logger.error("assetsId="+String.valueOf(assetsId)+", assetsId="+assetsId);
		logger.error(token);
		return null;
	}

	@Override
	public Result<Object> changeMtPlanFlag(String token, List<Long> assetIds, Integer flag) {
		return null;
	}
	
	
	
}
