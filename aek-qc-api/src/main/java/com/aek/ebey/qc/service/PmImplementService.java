package com.aek.ebey.qc.service;

import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.model.PmImplement;
import com.aek.ebey.qc.query.PmImplementRecordQuery;
import com.aek.ebey.qc.request.PmImplementRecord;
import com.aek.common.core.base.BaseService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author aek
 * @since 2017-11-30
 */
public interface PmImplementService extends BaseService<PmImplement> {

	LiuHuiPage<PmImplementRecord> getRecord(PmImplementRecordQuery query);

	void check(Long id);

	Integer getPmCheckWaitToDo(Long id);
	
}
