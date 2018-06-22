package com.aek.ebey.qc.service;

import java.util.List;

import com.aek.common.core.base.BaseService;
import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.model.PmPlanImplementHelp;
import com.aek.ebey.qc.query.PmPlanImplementQuery;
import com.aek.ebey.qc.request.CheckDetail;
import com.aek.ebey.qc.request.PmImplementDetail;
import com.aek.ebey.qc.request.PmImplementRequest;
import com.aek.ebey.qc.request.PmImplementSheet;
import com.aek.ebey.qc.request.PmPlanImplementResponse;
import com.aek.ebey.qc.request.PmProjectDetail;
import com.aek.ebey.qc.request.PmProjectDetail2;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author aek
 * @since 2017-11-30
 */
public interface PmPlanImplementHelpService extends BaseService<PmPlanImplementHelp> {

	LiuHuiPage<PmPlanImplementResponse> search(PmPlanImplementQuery query);

	CheckDetail checkById(Long id);

	void save(PmImplementRequest request);

	PmImplementDetail getPmImplementDetail(Long id);

	PmImplementSheet getImplementSheetById(Long id);
	
	List<PmProjectDetail> getItems(Long id);

	List<PmProjectDetail2> getItems2(Long id);

	CheckDetail scan(Long id);

	Integer getPmImplementWaitToDo(Long id);

	void autoSend();

	
}
