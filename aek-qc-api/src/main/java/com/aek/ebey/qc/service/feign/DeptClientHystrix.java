package com.aek.ebey.qc.service.feign;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.aek.common.core.Result;
import com.aek.ebey.qc.model.vo.UserVO;
import com.aek.ebey.qc.query.QcUserDept;
import com.aek.ebey.qc.query.ReturnMsg;
import com.aek.ebey.qc.request.Dept;
/**
 * 断路器
 */
@Component
public class DeptClientHystrix implements DeptClientService {
	
	private static final Logger logger = LoggerFactory.getLogger(DeptClientHystrix.class);

	@Override
	public Result<List<Dept>> getQcDeptList(Long tenantId, String token, String keyword) {
		logger.error("tenantId="+String.valueOf(tenantId)+", keyword="+keyword);
		logger.error(token);
		return null;
	}

	@Override
	public Result<ReturnMsg> verifyCreateQcPlan(QcUserDept qcUserDept, String token) {
		logger.error("verifyCreateQcPlan="+qcUserDept.toString());
		return null;
	}

	@Override
	public Result<UserVO> getUser(Long id, Long tenantId, String token) {
		logger.error("getUser="+ id +"," + tenantId);
		logger.error(token);
		return null;
	}

	@Override
	public Result<Boolean> checkUserPermission(Long id, String rht, String token) {
		logger.error("checkUserPermission="+ id +"," + rht);
		logger.error(token);
		return null;
	}

    @Override
    public Result<Integer> checkIsMsCheck(Long userId, String token) {
        logger.error("checkIsMsCheck="+ userId);
        logger.error(token);
        return null;
    }

	@Override
	public Result<Boolean> checkUserPermissions(List<Long> id, String rht,Long tenantId, String token) {
		logger.error("checkUserPermissions="+ id +"," + rht);
		logger.error(token);
		return null;
	}

	
}
