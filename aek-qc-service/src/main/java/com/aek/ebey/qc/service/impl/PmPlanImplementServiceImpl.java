package com.aek.ebey.qc.service.impl;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.aek.common.core.base.BaseServiceImpl;
import com.aek.common.core.serurity.WebSecurityUtils;
import com.aek.common.core.serurity.model.AuthUser;
import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.mapper.PmPlanImplementMapper;
import com.aek.ebey.qc.mapper.PmPlanMapper;
import com.aek.ebey.qc.model.PmPlanImplement;
import com.aek.ebey.qc.query.PmImplementQuery;
import com.aek.ebey.qc.request.PmImplementResponse;
import com.aek.ebey.qc.request.PmMounth;
import com.aek.ebey.qc.request.PmOverView;
import com.aek.ebey.qc.request.PmPlanResponse;
import com.aek.ebey.qc.request.PmPlanTotal;
import com.aek.ebey.qc.request.QcMounth;
import com.aek.ebey.qc.request.TimeQuery;
import com.aek.ebey.qc.service.PmPlanImplementService;
import com.aek.ebey.qc.utils.QcUtils;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * PM计划实施help 服务实现类
 * </p>
 *
 * @author aek
 * @since 2017-11-30
 */
@Service
@Transactional
public class PmPlanImplementServiceImpl extends BaseServiceImpl<PmPlanImplementMapper, PmPlanImplement> implements PmPlanImplementService {

	@Autowired
	private PmPlanImplementMapper pmPlanImplementMapper;
	
	@Autowired
	private PmPlanMapper pmPlanMapper;
	
	@Override
	@Transactional(readOnly=true)
	public LiuHuiPage<PmImplementResponse> search(PmImplementQuery query) {
		AuthUser authUser = WebSecurityUtils.getCurrentUser();
		Page<PmImplementResponse> pagePmImplement = query.getPagePlus();
		List<PmImplementResponse> list=pmPlanImplementMapper.search(pagePmImplement,query,authUser);
		LiuHuiPage<PmImplementResponse> page=new LiuHuiPage<>(pagePmImplement);
		page.setRecords(list);
		return page;
	}

	@Override
	public List<PmOverView> countPM(TimeQuery query) {
		//年度PM计划总数
		List<PmOverView> pmPlanTotalYearList = pmPlanMapper.selcetByCondtion(query);
		//年度pm实施总数及pm执行率
		List<PmOverView> pmRateYearList = pmPlanImplementMapper.selectByCondtion(query);
		//组装数据
		for (PmOverView pmPlanTotalYear : pmPlanTotalYearList) {
            for (PmOverView pmRateYear : pmRateYearList) {
                if (pmPlanTotalYear.getTenantId().longValue() == pmRateYear.getTenantId().longValue()) {
                    pmPlanTotalYear.setPmImplementTotalYear(pmRateYear.getPmImplementTotalYear());
                    pmPlanTotalYear.setPmRateYear(pmRateYear.getPmRateYear());
                }
            }
        }
		return pmPlanTotalYearList;
	}

	@Override
	public List<PmMounth> countPMAssets(TimeQuery query) {
	    //月度pm执行设备总数
		List<PmMounth> list = pmPlanImplementMapper.selectCountPMAssets(query);
		if(query != null && query.getStartDate() != null){
			if(list != null && list.size() > 0){
				for(PmMounth pmMounth:list){
					pmMounth.setCountMonth(query.getStartDate().substring(0, query.getStartDate().length()-3));
				}
			}
		}
		return list;
	}
	
}
