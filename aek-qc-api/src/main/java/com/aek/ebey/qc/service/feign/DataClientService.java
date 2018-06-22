package com.aek.ebey.qc.service.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aek.common.core.Result;
import com.aek.ebey.qc.request.PmMounth;
import com.aek.ebey.qc.request.PmOverView;
import com.aek.ebey.qc.request.QcMounth;
import com.aek.ebey.qc.request.QcOverView;


/**
 * 用户远程调用接口value=${feign-sys.serviceId}
 */
@FeignClient(value="${feign-data.serviceId}", fallback = DataClientHystrix.class)
public interface DataClientService {
	
	/**
	 * 调用接口，推送数据
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/data/qcPmData/addQcPmData")
	Result<Object> pushDataPmOverView(@RequestBody List<PmOverView> request);
	
	/**
	 * 调用接口，推送数据
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/data/qcPmData/addQcPmDataMonth")
	Result<Object> pushDataPmMounth(@RequestBody List<PmMounth> request);
	
	/**
	 * 调用接口，推送数据
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/data/qcInspectionData/addQcInspectionData")
	Result<Object> pushDataQcOverView(@RequestBody List<QcOverView> request);
	
	/**
	 * 调用接口，推送数据
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/data/qcInspectionData/addQcInspectionDataMonth")
	Result<Object> pushDataQcMounth(@RequestBody List<QcMounth> request);


}