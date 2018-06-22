package com.aek.ebey.qc.web.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aek.common.core.Result;
import com.aek.common.core.base.BaseController;
import com.aek.common.core.exception.ExceptionFactory;
import com.aek.common.core.serurity.WebSecurityUtils;
import com.aek.common.core.serurity.model.AuthUser;
import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.inter.MsStatus;
import com.aek.ebey.qc.model.MsAssets;
import com.aek.ebey.qc.model.MsChecRecordResponse;
import com.aek.ebey.qc.model.MsCheck;
import com.aek.ebey.qc.model.MsCheckHistory;
import com.aek.ebey.qc.model.MsCheckInfoResponse;
import com.aek.ebey.qc.model.MsCheckResultInfo;
import com.aek.ebey.qc.model.MsCheckStorage;
import com.aek.ebey.qc.query.MsAssetsQuery;
import com.aek.ebey.qc.query.MsAssetsRecordQuery;
import com.aek.ebey.qc.request.EditMsAssets;
import com.aek.ebey.qc.request.MsAssetsRecordResponse;
import com.aek.ebey.qc.request.MsAssetsResponse;
import com.aek.ebey.qc.request.MsCheckStorageRequest;
import com.aek.ebey.qc.service.MsAssetsService;
import com.aek.ebey.qc.service.MsCheckHistoryService;
import com.aek.ebey.qc.service.MsCheckResultInfoService;
import com.aek.ebey.qc.service.MsCheckService;
import com.aek.ebey.qc.service.MsCheckStorageService;
import com.aek.ebey.qc.service.feign.DeptClientService;
import com.aek.ebey.qc.utils.QcUtils;
import com.aek.ebey.qc.utils.ToolUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * <p>
 * 计量设备信息表  前端控制器
 * </p>
 *
 * @author Honghui
 * @since 2018-04-25
 */
@RestController
@RequestMapping("/qc/msAssets")
@Api(value = "MsAssetsController", description = "计量管理")
public class MsAssetsController extends BaseController {
	
private static final Logger logger = LoggerFactory.getLogger(MsAssetsController.class);
	
	@Autowired
	private MsAssetsService msAssetsService;
	
	
	@Autowired
	private MsCheckService msCheckService;
	
	@Autowired
	private MsCheckStorageService msCheckStorageService;
	
	@Autowired
	private MsCheckResultInfoService msCheckResultInfoService;
	
	@Autowired
	private MsCheckHistoryService msCheckHistoryService;
	
	@Autowired
	private DeptClientService deptClientService;
	
	/**
	 * 查询计量台帐
	 */
	@PreAuthorize("hasAuthority('MS_ASSETS_VIEW_NEW_IMPORT_EXPORT')")
	@GetMapping(value = "/search")
	@ApiOperation(value = "查询计量台帐")
	@ApiImplicitParams({ @ApiImplicitParam(name = "status", value = "状态（1，待完善 2，已完善）", paramType = "query"),
		@ApiImplicitParam(name = "measureManageType", value = "管理类别（1，非强制性计量设备 2，强制性计量设备）", paramType = "query"),
		@ApiImplicitParam(name = "assetsDeptId", value = "设备所在科室id", paramType = "query"),
		@ApiImplicitParam(name = "keyword", value = "设备名称/计量编号", paramType = "query"),
		@ApiImplicitParam(name = "startDate", value = "开始时间", paramType = "query"),
		@ApiImplicitParam(name = "endDate", value = "结束时间", paramType = "query") })
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<LiuHuiPage<MsAssetsResponse>> search(MsAssetsQuery query) {
		logger.debug("<---------------------------------"+JSON.toJSONString(query));
		LiuHuiPage<MsAssetsResponse> page = msAssetsService.search(query);
		return response(page);
	}
	
	@PreAuthorize("hasAuthority('MS_ASSETS_VIEW_NEW_IMPORT_EXPORT')")
	@PostMapping(value = "/add")
	@ApiOperation(value = "新建非固定计量")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> add(@RequestBody MsAssets request){
		logger.debug("<---------------------------------"+JSON.toJSONString(request));
		if(ToolUtil.isOneEmpty(request,request.getAssetsDeptId(),request.getAssetsDeptName(),request.getAssetsName(),
		                request.getFactoryName(),request.getMeasureType(),request.getMeasureCategory(),request.getMeasureCycle(),
		                request.getNextCheckTime(),request.getCheckMode(),request.getChargeUserId())){
			throw ExceptionFactory.create("S_001");
		}else{
			request.setStatus(MsStatus.TWO.getCode());
		}
		request.setMeasureNum(QcUtils.mSno());
		this.msAssetsService.insertMsMsAssetsAndCheck(request);
		return response();
	}
	
	@PostMapping(value = "/saveOrUpdate")
	@ApiOperation(value = "同步固定计量台帐")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> saveOrUpdate(@RequestBody MsAssets request){
		logger.debug("<---------------------------------"+JSON.toJSONString(request));
		this.msAssetsService.saveOrUpdate(request);
		return response();
	}
	
	@GetMapping(value = "/isExistMsAssets")
    @ApiOperation(value = "判断计量台账是否存在")
    @ApiResponse(code = 200, message = "OK", response = Result.class)
    public Result<Boolean> isExistMsAssets(@RequestParam(name = "assetsId",required = true) Long assetsId){
        logger.debug("<---------------------------------"+JSON.toJSONString(assetsId));
        Wrapper<MsAssets> wrapper=new EntityWrapper<>();
        wrapper.eq("assets_id", assetsId);
        List<MsAssets> msAssetsList = msAssetsService.selectList(wrapper);
        if (null != msAssetsList && msAssetsList.size() > 0) {
            return response(true);
        }
        return response(false);
    }
	
	/**
	 * 编辑
	 */
	@PreAuthorize("hasAuthority('MS_ASSETS_VIEW_NEW_IMPORT_EXPORT')")
	@PostMapping(value = "/edit")
	@ApiOperation(value = "编辑计量")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> edit(@RequestBody EditMsAssets request){
		logger.debug("<---------------------------------"+JSON.toJSONString(request));
		if(ToolUtil.isOneEmpty(request,request.getMeasureType(),request.getMeasureCategory(),request.getMeasureCycle(),
		                request.getNextCheckTime(),request.getCheckMode(),request.getChargeUserId())){
			request.setStatus(MsStatus.ONE.getCode());
		}else{
			request.setStatus(MsStatus.TWO.getCode());
		}
		this.msAssetsService.updateMsAssets(request);
		return response();
	}
	
	
	   /**
     * 根据计量检测id查询计量信息
     */
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "根据计量检测id查询计量信息")
    @ApiResponse(code = 200, message = "OK", response = Result.class)
    public Result<MsCheck> getMsAssetsById(@PathVariable Long id) {
        logger.debug("<---------------------------------"+JSON.toJSONString(id));
        MsCheck  msCheck =msCheckService.selectById(id);
        return response(msCheck);
    }

    /**
     * 根据计量检测id查询计量信息(All)
     */
    @GetMapping(value = "/getAllById/{id}")
    @ApiOperation(value = "根据计量检测id查询计量信息(All)")
    @ApiResponse(code = 200, message = "OK", response = Result.class)
    public Result<MsCheckInfoResponse> getAllById(@PathVariable Long id) {
        logger.debug("<---------------------------------"+JSON.toJSONString(id));
        MsCheckInfoResponse msCheckInfoResponse=new MsCheckInfoResponse();
        MsCheck  msCheck =msCheckService.selectById(id);
        MsCheckStorage msCheckStorage=null;
        Wrapper<MsCheckStorage> wrapper=new EntityWrapper<>();
        wrapper.eq("ms_check_id", id);
        List<MsCheckStorage> list=msCheckStorageService.selectList(wrapper);
        if(list!=null&&list.size()>0){
            msCheckStorage=list.get(0);
        }
        msCheckInfoResponse.setMsCheck(msCheck);
        msCheckInfoResponse.setMsCheckStorage(msCheckStorage);
        if(msCheck!=null){
        	msCheckInfoResponse.setAttention(QcUtils.getAttentionMs(new Date(), msCheck.getNextCheckTime()));
        }
        return response(msCheckInfoResponse);
    }
    
    
	/**
	 * 查询计量检测
	 */
    @PreAuthorize("hasAuthority('MS_CHECK_EDIT_PRINT')")
	@GetMapping(value = "/search_check")
	@ApiOperation(value = "查询计量检测")
	@ApiImplicitParams({@ApiImplicitParam(name = "measureManageType", value = "管理类别（1，非强制性计量设备 2，强制性计量设备）", paramType = "query"),
		@ApiImplicitParam(name = "assetsDeptId", value = "设备所在科室id", paramType = "query"),
		@ApiImplicitParam(name = "keyword", value = "设备名称/设备编号/计量编号/院内编码/出厂编码", paramType = "query"),
		@ApiImplicitParam(name = "startDate", value = "开始时间", paramType = "query"),
		@ApiImplicitParam(name = "endDate", value = "结束时间", paramType = "query") })
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<LiuHuiPage<MsAssetsResponse>> searchCheck(MsAssetsQuery query) {
		logger.debug("<---------------------------------"+JSON.toJSONString(query));
		LiuHuiPage<MsAssetsResponse> page = msCheckService.search(query);
		return response(page);
	}
	
	
	/**
	 * 暂存
	 */
    @PreAuthorize("hasAuthority('MS_CHECK_EDIT_PRINT')")
	@PostMapping(value = "/saving")
	@ApiOperation(value = "暂存检测")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> saving(@RequestBody MsCheckStorageRequest request){
		logger.debug("<---------------------------------"+JSON.toJSONString(request));
		AuthUser authUser = WebSecurityUtils.getCurrentUser();
        Result<Integer> checkIsMsCheckResult = deptClientService.checkIsMsCheck(authUser.getId(), WebSecurityUtils.getCurrentToken());
        if (null == checkIsMsCheckResult) {
            throw ExceptionFactory.create("S_002");
        }
        Integer resultData = checkIsMsCheckResult.getData();
        if (resultData == 1) {
            //无计量检测权限
            throw ExceptionFactory.create("S_003");
        } else if (resultData == 2) {
            //用户被停用
            throw ExceptionFactory.create("S_004");
        }
        if (null != request.getMsCheckId()) {
            MsCheck msCheck = msCheckService.selectById(request.getMsCheckId());
            if (null == msCheck) {
            	Long assetsId=request.getMeasureAssetsId();
            	if(assetsId!=null){
            		Wrapper<MsAssets> wrapper=new EntityWrapper<>();
            		wrapper.eq("assets_id", assetsId);
            		MsAssets  msAssets =msAssetsService.selectOne(wrapper);
            		if(msAssets!=null&&msAssets.getDelFlag()){
            			//非计量设备
            			throw ExceptionFactory.create("S_007");
            		}
            	}
            	//该检测已被提交
                throw ExceptionFactory.create("S_005");
            }else{
            	if(authUser.getId().longValue()!=msCheck.getChargeUserId().longValue()){
            		throw ExceptionFactory.create("S_006");
            	}
            	
            }
        }
		this.msCheckStorageService.saving(request);
		return response();
	}
	
	
	/**
	 * 根据检测id查询暂存信息
	 */
    @PreAuthorize("hasAuthority('MS_CHECK_EDIT_PRINT')")
	@GetMapping(value = "/getSavingById/{id}")
	@ApiOperation(value = "根据检测id查询暂存信息")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<MsCheckStorage> getSavingById(@PathVariable Long id) {
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		Wrapper<MsCheckStorage> wrapper=new EntityWrapper<>();
		wrapper.eq("ms_check_id", id);
		List<MsCheckStorage> list=msCheckStorageService.selectList(wrapper);
		if(list!=null&&list.size()>0){
			return response(list.get(0));
		}
		return response(null);
	}
	/**
	 * 提交
	 */
	@PreAuthorize("hasAuthority('MS_CHECK_EDIT_PRINT')")
	@PostMapping(value = "/saved")
	@ApiOperation(value = "提交检测")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<MsCheckResultInfo> saved(@RequestBody MsCheckStorageRequest request){
		logger.debug("<---------------------------------"+JSON.toJSONString(request));
		AuthUser authUser = WebSecurityUtils.getCurrentUser();
		Result<Integer> checkIsMsCheckResult = deptClientService.checkIsMsCheck(authUser.getId(), WebSecurityUtils.getCurrentToken());
		if (null == checkIsMsCheckResult) {
		    throw ExceptionFactory.create("S_002");
		}
		Integer resultData = checkIsMsCheckResult.getData();
		if (resultData == 1) {
		    //无计量检测权限
		    throw ExceptionFactory.create("S_003");
		} else if (resultData == 2) {
		    //用户被停用
		    throw ExceptionFactory.create("S_004");
		}
		if (null != request.getMsCheckId()) {
		    MsCheck msCheck = msCheckService.selectById(request.getMsCheckId());
		    if (null == msCheck) {
		    	Long assetsId=request.getMeasureAssetsId();
            	if(assetsId!=null){
            		Wrapper<MsAssets> wrapper=new EntityWrapper<>();
            		wrapper.eq("assets_id", assetsId);
            		MsAssets  msAssets =msAssetsService.selectOne(wrapper);
            		if(msAssets!=null&&msAssets.getDelFlag()){
            			//非计量设备
            			throw ExceptionFactory.create("S_007");
            		}
            	}
		        //该检测已被提交
		        throw ExceptionFactory.create("S_005");
		    }else{
            	if(authUser.getId().longValue()!=msCheck.getChargeUserId().longValue()){
            		throw ExceptionFactory.create("S_006");
            	}
            }
		}
		MsCheckResultInfo msCheckResultInfo=this.msCheckResultInfoService.saved(request);
		return response(msCheckResultInfo);
	}
	
	
	/**
	 * 查询计量档案
	 */
	@PreAuthorize("hasAuthority('MS_RECORD_VIEW_PRINT')")
	@GetMapping(value = "/search_record")
	@ApiOperation(value = "查询计量档案")
	@ApiImplicitParams({@ApiImplicitParam(name = "measureManageType", value = "管理类别（1，非强制性计量设备 2，强制性计量设备）", paramType = "query"),
		@ApiImplicitParam(name = "assetsDeptId", value = "设备所在科室id", paramType = "query"),
		@ApiImplicitParam(name = "keyword", value = "设备名称/设备编号/计量编号/院内编码/出厂编码", paramType = "query"),
		@ApiImplicitParam(name = "startDate", value = "开始时间", paramType = "query"),
		@ApiImplicitParam(name = "endDate", value = "结束时间", paramType = "query") })
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<LiuHuiPage<MsAssetsRecordResponse>> searchRecord(MsAssetsRecordQuery query) {
		logger.debug("<---------------------------------"+JSON.toJSONString(query));
		LiuHuiPage<MsAssetsRecordResponse> page = msCheckResultInfoService.search(query);
		return response(page);
	}
	

	/**
	 * 根据计量信息id查询计量信息
	 */
	@PreAuthorize("hasAuthority('MS_RECORD_VIEW_PRINT')")
	@GetMapping(value = "/getMsAssetsRecordById/{id}")
	@ApiOperation(value = "根据计量信息id查询计量信息")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<MsCheckResultInfo> getMsAssetsRecordById(@PathVariable Long id) {
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		MsCheckResultInfo  msCheckResultInfo =msCheckResultInfoService.selectById(id);
		return response(msCheckResultInfo);
	}
	

	/**
	 * 根据计量信息id查询计量信息(All)
	 */
	@PreAuthorize("hasAuthority('MS_RECORD_VIEW_PRINT')")
	@GetMapping(value = "/getMsAssetsAllRecordById/{id}")
	@ApiOperation(value = "根据计量信息id查询计量信息(All)")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<MsChecRecordResponse> getMsAssetsAllRecordById(@PathVariable Long id) {
		logger.debug("<---------------------------------"+JSON.toJSONString(id));
		AuthUser authUser = WebSecurityUtils.getCurrentUser();
		MsChecRecordResponse msChecRecordResponse=new MsChecRecordResponse();
		MsCheckResultInfo  msCheckResultInfo =msCheckResultInfoService.selectById(id);
		MsCheckHistory msCheckHistory=null;
		if(msCheckResultInfo!=null){
			msCheckHistory=msCheckHistoryService.selectById(msCheckResultInfo.getCheckHistoryId());
		}
		msChecRecordResponse.setMsCheckHistory(msCheckHistory);
		msChecRecordResponse.setMsCheckResultInfo(msCheckResultInfo);
		msChecRecordResponse.setTenantName(authUser.getTenantName());
		return response(msChecRecordResponse);
	}
	
	
}
