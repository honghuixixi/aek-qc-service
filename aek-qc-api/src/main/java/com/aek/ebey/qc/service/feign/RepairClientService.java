package com.aek.ebey.qc.service.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aek.common.core.Result;
import com.aek.ebey.qc.request.SendMessage;

/**
 * 用户远程调用接口value=${feign-sys.serviceId}
 */
@FeignClient(value="${feign-repair-new.serviceId}", fallback = RepairClientHystrix.class)
public interface RepairClientService {

	/**
	 * 调用接口，是否可以新建
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/newrepair/repRepairMessage/send")
	Result<Object> send(@RequestBody SendMessage sendMessage);

}