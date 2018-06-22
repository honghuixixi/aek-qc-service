package com.aek.ebey.qc.service.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aek.common.core.Result;
import com.aek.ebey.qc.model.QcAssets;
import com.aek.ebey.qc.model.vo.MdAssetsVO;
import com.aek.ebey.qc.query.PmQAssets;
import com.aek.ebey.qc.request.AssetsInfoDetailReponse;
import com.baomidou.mybatisplus.plugins.Page;


/**
 * 用户远程调用接口value=${feign-sys.serviceId}
 */
@FeignClient(value="${feign-assets.serviceId}", fallback = AssetsClientHystrix.class)
public interface AssetsClientService {
	/**
	 * 调用接口，查找部门名称
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/assets/restAPI/getQcAssetsList")
	Result<List<QcAssets>> getQcAssetsList(@RequestParam(value="tenantId", required=true) Long tenantId,@RequestHeader("X-AEK56-Token") String token,@RequestParam(value="deptIds", required=true)Long[] deptIds);

	
	/**
	 * 调用接口，查找部门名称
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/assets/restAPI/getQcAssetsPage")
	Result<Page<QcAssets>> getQcAssetsPage(@RequestBody Page page,@RequestParam(value="tenantId", required=true) Long tenantId,@RequestHeader("X-AEK56-Token") String token,@RequestParam(value="deptIds", required=true)Long[] deptIds);

	/**
	 * 调用接口，查找部门名称
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/assets/restAPI/updateAssetsPmPlanStatusByIds")
	Result<Object> updateAssetsPmPlanStatusByIds(@RequestHeader("X-AEK56-Token") String token,@RequestParam(value="assetsIds", required=true)List<Long> assetsIds);


	
	/**
	 * 根据ID获取设备状态
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/assets/restAPI/getAssetsStatus")
	Result<PmQAssets> getAssetsStatus(@RequestHeader("X-AEK56-Token") String token,@RequestParam(value="assetsId", required=true)Long assetsId);
	
	/**
	 * 调用接口，根据资产ID查询资产详情信息
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/assets/restAPI/getAssetsDetail")
	Result<AssetsInfoDetailReponse> getAssetsDetail(@RequestHeader("X-AEK56-Token") String token,@RequestParam(value="assetsId", required=true)Long assetsId);

	/**
	 * 批量修改设备是否已经在保养计划
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET,value = "/assets/restAPI/changeMtPlanFlag")
	Result<Object> changeMtPlanFlag(@RequestHeader("X-AEK56-Token") String token,@RequestParam(value="assetIds", required=true)List<Long> assetIds,@RequestParam(value="flag", required=true)Integer flag);
	
}