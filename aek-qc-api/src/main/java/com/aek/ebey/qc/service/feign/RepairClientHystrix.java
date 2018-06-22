package com.aek.ebey.qc.service.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.aek.common.core.Result;
import com.aek.ebey.qc.request.SendMessage;

/**
 * 断路器
 */
@Component
public class RepairClientHystrix implements RepairClientService {
	
	private static final Logger logger = LoggerFactory.getLogger(RepairClientHystrix.class);

	@Override
	public Result<Object> send(SendMessage sendMessage) {
		logger.error("-------------------------------------"+String.valueOf(sendMessage));
		return null;
	}

	

	
}
