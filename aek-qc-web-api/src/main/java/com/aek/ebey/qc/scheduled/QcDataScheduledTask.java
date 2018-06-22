package com.aek.ebey.qc.scheduled;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aek.common.core.Result;
import com.aek.common.core.base.BaseController;
import com.aek.ebey.qc.request.PmMounth;
import com.aek.ebey.qc.request.PmOverView;
import com.aek.ebey.qc.request.QcMounth;
import com.aek.ebey.qc.request.QcOverView;
import com.aek.ebey.qc.request.TimeQuery;
import com.aek.ebey.qc.service.PmPlanImplementService;
import com.aek.ebey.qc.service.QcImplementService;
import com.aek.ebey.qc.service.feign.DataClientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * 维修统计数据定时任务
 * 
 * @author HongHui
 * @date 2018年4月17日
 */
@Component
@RestController
@RequestMapping("/qc/QcDataScheduledTask")
@Api(value = "QcDataScheduledTask", description = "QC数据统计任务")
public class QcDataScheduledTask extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(QcDataScheduledTask.class);

    @Autowired
    private QcImplementService qcImplementService;
    @Autowired
    private DataClientService dataClientService;
    @Autowired
    private PmPlanImplementService pmPlanImplementService;

    /**
     * 定时统计Qc
     */
    @Scheduled(cron = "0 0/20 3 * * ?")
    // @Scheduled(cron="0/50 * * * * ? ")
    public void autoCountQcOverView() throws Exception {
        List<QcOverView> list = qcImplementService.countQc(new TimeQuery());
        if (null != list && list.size() > 0) {
            dataClientService.pushDataQcOverView(list);
        }
    }

    /**
     * 定时统计Qc设备
     */
    @Scheduled(cron = "0 0/20 4 * * ?")
    // @Scheduled(cron="0/50 * * * * ? ")
    public void autoCountQcMounth() throws Exception {
        List<QcMounth> list = qcImplementService.countQcAssets(new TimeQuery());
        if (null != list && list.size() > 0) {
            dataClientService.pushDataQcMounth(list);
        }
    }


    /**
     * 定时统计PM
     */
    @Scheduled(cron = "0 0/20 1 * * ?")
    // @Scheduled(cron="0/50 * * * * ? ")
    public void autoCountPmOverView() throws Exception {
        List<PmOverView> list = pmPlanImplementService.countPM(new TimeQuery());
        if (null != list && list.size() > 0) {
            dataClientService.pushDataPmOverView(list);
        }
    }

    /**
     * 定时统计PM
     */
    @Scheduled(cron = "0 0/20 2 * * ?")
    // @Scheduled(cron="0/50 * * * * ? ")
    public void autoCountPmMounth() throws Exception {
        List<PmMounth> list = pmPlanImplementService.countPMAssets(new TimeQuery());
        if (null != list && list.size() > 0) {
            dataClientService.pushDataPmMounth(list);
        }
    }



    /**
     * 定时统计Qc
     */
    @GetMapping(value = "/historyQc")
    @ApiOperation(value = "Qc统计")
    @ApiResponse(code = 200, message = "OK", response = Result.class)
    public Result<List<QcOverView>> historyQc(TimeQuery query) throws Exception {
        List<QcOverView> list = qcImplementService.countQc(query);
        if (null != list && list.size() > 0) {
            dataClientService.pushDataQcOverView(list);
        }
        return response(list);

    }

    /**
     * 定时统计Qc设备
     */
    @GetMapping(value = "/historyQcMounth")
    @ApiOperation(value = "Qc设备统计")
    @ApiResponse(code = 200, message = "OK", response = Result.class)
    public Result<List<QcMounth>> historyQcMounth(TimeQuery query) throws Exception {
        List<QcMounth> list = qcImplementService.countQcAssets(query);
        if (null != list && list.size() > 0) {
            dataClientService.pushDataQcMounth(list);
        }
        return response(list);

    }

    /**
     * 定时统计PM
     */
    @GetMapping(value = "/historyPm")
    @ApiOperation(value = "Qc设备统计")
    @ApiResponse(code = 200, message = "OK", response = Result.class)
    public Result<List<PmOverView>> historyPm(TimeQuery query) throws Exception {
        List<PmOverView> list = pmPlanImplementService.countPM(query);
        if (null != list && list.size() > 0) {
            dataClientService.pushDataPmOverView(list);
        }
        return response(list);
    }

    /**
     * 定时统计PM
     */
    @GetMapping(value = "/historyPmMounth")
    @ApiOperation(value = "Qc设备统计")
    @ApiResponse(code = 200, message = "OK", response = Result.class)
    public Result<List<PmMounth>> historyPmMounth(TimeQuery query) throws Exception {
        List<PmMounth> list = pmPlanImplementService.countPMAssets(query);
        if (null != list && list.size() > 0) {
            dataClientService.pushDataPmMounth(list);
        }
        return response(list);
    }
}
