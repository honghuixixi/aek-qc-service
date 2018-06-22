package com.aek.ebey.qc.service.feign;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.aek.common.core.Result;
import com.aek.ebey.qc.request.PmMounth;
import com.aek.ebey.qc.request.PmOverView;
import com.aek.ebey.qc.request.QcMounth;
import com.aek.ebey.qc.request.QcOverView;

/**
 * 断路器
 */
@Component
public class DataClientHystrix implements DataClientService {
	
	private static final Logger logger = LoggerFactory.getLogger(DataClientHystrix.class);

	@Override
	public Result<Object> pushDataPmOverView(List<PmOverView> request) {
		logger.error("request="+String.valueOf(request));
		return null;
	}

	@Override
	public Result<Object> pushDataPmMounth(List<PmMounth> request) {
		logger.error("request="+String.valueOf(request));
		return null;
	}

	@Override
	public Result<Object> pushDataQcOverView(List<QcOverView> request) {
		logger.error("request="+String.valueOf(request));
		return null;
	}

	@Override
	public Result<Object> pushDataQcMounth(List<QcMounth> request) {
		logger.error("request="+String.valueOf(request));
		return null;
	}

	
	
	
}
