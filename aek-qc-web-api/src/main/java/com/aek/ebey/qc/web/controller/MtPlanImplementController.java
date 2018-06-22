package com.aek.ebey.qc.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aek.common.core.BeanMapper;
import com.aek.common.core.Result;
import com.aek.common.core.base.BaseController;
import com.aek.common.core.exception.ExceptionFactory;
import com.aek.common.core.serurity.WebSecurityUtils;
import com.aek.common.core.serurity.model.AuthUser;
import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.model.MtImplementResult;
import com.aek.ebey.qc.model.MtPlan;
import com.aek.ebey.qc.model.MtPlanAssets;
import com.aek.ebey.qc.model.MtPlanImplement;
import com.aek.ebey.qc.model.MtPlanTemplate;
import com.aek.ebey.qc.model.MtPlanTemplateItem;
import com.aek.ebey.qc.model.vo.UserVO;
import com.aek.ebey.qc.query.MtPlanImplementQuery;
import com.aek.ebey.qc.request.AssetsInfoDetailReponse;
import com.aek.ebey.qc.request.MtImplementResultRequest;
import com.aek.ebey.qc.request.MtPlanImplementDetailResponse;
import com.aek.ebey.qc.request.MtPlanImplementResponse;
import com.aek.ebey.qc.service.MtImplementResultService;
import com.aek.ebey.qc.service.MtPlanAssetsService;
import com.aek.ebey.qc.service.MtPlanImplementService;
import com.aek.ebey.qc.service.MtPlanService;
import com.aek.ebey.qc.service.MtPlanTemplateItemService;
import com.aek.ebey.qc.service.MtPlanTemplateService;
import com.aek.ebey.qc.service.feign.AssetsClientService;
import com.aek.ebey.qc.service.feign.DeptClientService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * 保养计划实施表  前端控制器
 *
 * @author Honghui
 * @since 2018-03-21
 */
@RestController
@RequestMapping("/qc/mtPlanImplement")
@Api(value = "MtPlanImplementController", description = "保养实施")
public class MtPlanImplementController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(MtPlanImplementController.class);
	
	@Autowired
	private MtPlanImplementService  mtPlanImplementService;
	@Autowired
	private MtPlanService mtPlanService;
	@Autowired
	private MtImplementResultService mtImplementResultService;
	@Autowired
	private MtPlanAssetsService mtPlanAssetsService;
	@Autowired
	private MtPlanTemplateService  mtPlanTemplateService;
	@Autowired
	private MtPlanTemplateItemService  mtPlanTemplateItemService;
	@Autowired
	private DeptClientService deptClientService;
	@Autowired
	private AssetsClientService assetsClientService;
	
	
	
	/**
	 * 保养实施分页列表
	 * @param query
	 * @return
	 */
	@PreAuthorize("hasAuthority('MT_PLAN_IMPLEMENT_MANAGE')")
	@GetMapping(value = "/list")
	@ApiOperation(value = "保养实施列表")
	@ApiImplicitParams(value={
			@ApiImplicitParam(name = "keyword",value = "设备名称/设备编号",paramType = "query" ,required = false),
			@ApiImplicitParam(name = "pageNo",value = "当前页",paramType = "query",required = true),
			@ApiImplicitParam(name = "pageSize",value = "每页显示多少数",paramType = "query",required = true)})
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<LiuHuiPage<MtPlanImplementResponse>> getMtPlanImplementPage(MtPlanImplementQuery query) {
		logger.debug("查询参数="+JSON.toJSONString(query));
		AuthUser user = WebSecurityUtils.getCurrentUser();
		LiuHuiPage<MtPlanImplementResponse> data = mtPlanImplementService.getMtPlanImplementPage(query.getPagePlus(),user,query);
		return response(data);
	}
	
	/**
	 * 点击【实施】
	 * 	1：校验用户权限，部门信息，保养计划是否停用，判断当前实施是否已提交实施内容
	 *  2：若可实施时，则进入实施页面，进行实施操作
	 * @return
	 */
	@PreAuthorize("hasAuthority('MT_PLAN_IMPLEMENT_MANAGE')")
	@GetMapping(value = "/detail")
	@ApiOperation(value = "点击实施进入实施页面")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<MtPlanImplementDetailResponse> getMtPlanImplementDetail(@RequestParam("id") Long id) {
		//当前登录用户
		AuthUser user = WebSecurityUtils.getCurrentUser();
		//实施数据
		MtPlanImplement mtPlanImplement = mtPlanImplementService.selectById(id);
		if (null == mtPlanImplement) {
			//此条实施数据已经提交
			throw ExceptionFactory.create("MT_006");
		}
		//校验当前用户权限部门等信息
		this.checkMtPlanImplementUserInfo(user);
		MtPlan mtPlan = mtPlanService.selectById(mtPlanImplement.getPlanId());
		if (!mtPlan.getEnable()) {
			//计划已被停用
			throw ExceptionFactory.create("MT_005");
		}
		Wrapper<MtPlanAssets> mtPlanAssetsWrapper = new EntityWrapper<MtPlanAssets>();
		mtPlanAssetsWrapper.eq("plan_id", mtPlan.getId());
		MtPlanAssets mtPlanAssets = mtPlanAssetsService.selectOne(mtPlanAssetsWrapper);
		//获取资产信息
        Result<AssetsInfoDetailReponse> assetsResult = assetsClientService.getAssetsDetail(WebSecurityUtils.getCurrentToken(), mtPlanAssets.getAssetsId());
        if (null == assetsResult) {
            //网络异常
            throw ExceptionFactory.create("MT_001");
        }
        AssetsInfoDetailReponse assetsInfo = assetsResult.getData();
        if (null == assetsInfo) {
            //资产信息不存在
            throw ExceptionFactory.create("MT_008");
        }
        if (!mtPlanAssets.getAssetsDeptId().equals(assetsInfo.getDeptId())) {
            //该资产已转科
            logger.info("====该资产已转科===");
            mtPlanAssets.setAssetsDeptId(assetsInfo.getDeptId());
            mtPlanAssets.setAssetsDeptName(assetsInfo.getDeptName());
            mtPlanAssetsService.updateById(mtPlanAssets);
            throw ExceptionFactory.create("MT_012");
        }
		if (!mtPlanAssets.getAssetsDeptId().equals(user.getDeptId())) {
			// 用户部门发生变更提示：所在部门信息已修改，请刷新页面
			throw ExceptionFactory.create("MT_003");
		}
		Wrapper<MtImplementResult> wrapper = new EntityWrapper<MtImplementResult>();
		wrapper.eq("plan_implement_id", id);
		Integer count = mtImplementResultService.selectCount(wrapper);
		if (count > 0) {
			//此条实施数据已经提交
			throw ExceptionFactory.create("MT_006");
		}
		// 组装数据返回
		MtPlanImplementDetailResponse mtPlanImplementDetailResponse = BeanMapper.map(mtPlanImplement, MtPlanImplementDetailResponse.class);
		// 保养设备信息
		BeanUtils.copyProperties(mtPlanAssets, mtPlanImplementDetailResponse);
		mtPlanImplementDetailResponse.setTenantName(mtPlan.getTenantName());
		Wrapper<MtPlanTemplate> mtPlanTemplateWrapper = new EntityWrapper<MtPlanTemplate>();
		mtPlanTemplateWrapper.eq("plan_id", mtPlan.getId());
		MtPlanTemplate mtPlanTemplate = mtPlanTemplateService.selectOne(mtPlanTemplateWrapper);
		Wrapper<MtPlanTemplateItem> mtPlanTemplateItemWrapper = new EntityWrapper<MtPlanTemplateItem>();
		mtPlanTemplateItemWrapper.eq("plan_template_id", mtPlanTemplate.getId());
		List<MtPlanTemplateItem> mtPlanTemplateItemList = mtPlanTemplateItemService.selectList(mtPlanTemplateItemWrapper);
		mtPlanImplementDetailResponse.setTemplateItems(mtPlanTemplateItemList);
		return response(mtPlanImplementDetailResponse);
	}
	
	/**
	 * 提交实施内容
	 * 	1：验证当前用户是否有保养实施权限，验证是否已提交实施结果
	 *  2：若可实施时，保存实施结果（结果、附件、项目结果）
	 *  3：删除当前计划实施，重新生成实施表数据，用户输入下次实施日期，更新保养计划下次实施日期
	 * @return
	 */
	@PreAuthorize("hasAuthority('MT_PLAN_IMPLEMENT_MANAGE')")
	@PostMapping(value = "/submit")
	@ApiOperation(value = "提交实施报告")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> submitPlanImplementResult(@RequestBody MtImplementResultRequest request) {
		//当前登录用户
		AuthUser user = WebSecurityUtils.getCurrentUser();
		// 检查用户是否具有保养实施权限
		Result<Boolean> mtPlanImplementPermission = deptClientService.checkUserPermission(user.getId(), "MT_PLAN_IMPLEMENT_MANAGE", WebSecurityUtils.getCurrentToken());
		if (null == mtPlanImplementPermission) {
			// 调用用户中心异常
			throw ExceptionFactory.create("MT_001");
		}
		boolean hasMtImplementPermission = mtPlanImplementPermission.getData();
		if (!hasMtImplementPermission) {
			// 实施权限已取消，请重新登录
			throw ExceptionFactory.create("MT_004");
		}
		
		Wrapper<MtImplementResult> wrapper = new EntityWrapper<MtImplementResult>();
		wrapper.eq("plan_implement_id", request.getPlanImplementId());
		Integer count = mtImplementResultService.selectCount(wrapper);
		if (count > 0) {
			//此条实施数据已经提交
			throw ExceptionFactory.create("MT_006");
		}
		
		Wrapper<MtPlanAssets> mtPlanAssetsWrapper = new EntityWrapper<MtPlanAssets>();
        mtPlanAssetsWrapper.eq("plan_id", request.getPlanId());
        MtPlanAssets mtPlanAssets = mtPlanAssetsService.selectOne(mtPlanAssetsWrapper);
        //获取资产信息
        Result<AssetsInfoDetailReponse> assetsResult = assetsClientService.getAssetsDetail(WebSecurityUtils.getCurrentToken(), mtPlanAssets.getAssetsId());
        if (null == assetsResult) {
            //网络异常
            throw ExceptionFactory.create("MT_001");
        }
        AssetsInfoDetailReponse assetsInfo = assetsResult.getData();
        if (null == assetsInfo) {
            //资产信息不存在
            throw ExceptionFactory.create("MT_008");
        }
        if (!mtPlanAssets.getAssetsDeptId().equals(assetsInfo.getDeptId())) {
            //该资产已转科
            logger.info("====该资产已转科===");
            mtPlanAssets.setAssetsDeptId(assetsInfo.getDeptId());
            mtPlanAssets.setAssetsDeptName(assetsInfo.getDeptName());
            mtPlanAssetsService.updateById(mtPlanAssets);
            throw ExceptionFactory.create("MT_012");
        }
		//保存实施内容
		MtImplementResult mtImplementResult = mtImplementResultService.submitImplementResult(request);
		return response(mtImplementResult);
	}
	
	/**
	 * 扫描资产ID验证
	 * @param assetsId
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "/scanAssetsId")
	@ApiOperation(value = "扫描资产ID验证")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> scanAssetsId(@RequestParam("assetsId") Long assetsId) {
		//当前登录用户
		AuthUser user = WebSecurityUtils.getCurrentUser();
		//获取资产信息
		Result<AssetsInfoDetailReponse> assetsResult = assetsClientService.getAssetsDetail(WebSecurityUtils.getCurrentToken(), assetsId);
		if (null == assetsResult) {
			//网络异常
			throw ExceptionFactory.create("MT_001");
		}
		AssetsInfoDetailReponse assetsInfo = assetsResult.getData();
		if (null == assetsInfo) {
			//资产信息不存在
			throw ExceptionFactory.create("MT_008");
		}
		logger.info("当前登录人部门ID=" + user.getDeptId());
		logger.info("该设备所在部门ID=" + assetsInfo.getDeptId());
		if (!user.getDeptId().equals(assetsInfo.getDeptId())) {
			//该资产所在部门与 用户登录所在部门不在同一部门
			logger.info("====该资产所在部门与 用户登录所在部门不在同一部门===");
			throw ExceptionFactory.create("MT_009");
		}
		if (assetsInfo.getStatus().equals(5)) {
			//资产状态在报损
			logger.info("====该资产状态在报损状态===");
			throw ExceptionFactory.create("MT_009");
		} 
		Wrapper<MtPlanAssets> mtPlanAssetsWrapper = new EntityWrapper<MtPlanAssets>();
		mtPlanAssetsWrapper.eq("assets_id", assetsId);
		List<MtPlanAssets> mtPlanAssetsList = mtPlanAssetsService.selectList(mtPlanAssetsWrapper);
		List<MtPlan> mtPlanList = new ArrayList<MtPlan>();
		for (MtPlanAssets mtPlanAssets : mtPlanAssetsList) {
			MtPlan mtPlan = mtPlanService.selectById(mtPlanAssets.getPlanId());
			if (null != mtPlan && mtPlan.getEnable()) {
				mtPlanList.add(mtPlan);
			}
		}
		if (mtPlanList.size() == 0) {
			logger.info("====该资产未加入保养计划===");
			throw ExceptionFactory.create("MT_009");
		}
		//当前保养计划
		MtPlan mtPlan = mtPlanList.get(0);
		Wrapper<MtPlanImplement> mtPlanImplementWrapper = new EntityWrapper<MtPlanImplement>();
		mtPlanImplementWrapper.eq("plan_id", mtPlan.getId());
		MtPlanImplement mtPlanImplement = mtPlanImplementService.selectOne(mtPlanImplementWrapper);
		if (null == mtPlanImplement) {
			//实施不存在
			throw ExceptionFactory.create("MT_011");
		}
		if (mtPlan.getNextImplementTime().getTime() > System.currentTimeMillis()) {
			//还未到规定保养日期，确定实施么？
			return new Result<Object>("MT_010", "还未到规定保养日期，确定实施么?", mtPlanImplement.getId());
		}
		return response(mtPlanImplement.getId());
	}
	
	/**
	 * 校验用户权限，部门信息是否发生变更
	 * @param user
	 */
	private void checkMtPlanImplementUserInfo(AuthUser user) {
		Result<UserVO> currentUserDeptInfo = deptClientService.getUser(user.getId(), user.getTenantId(), WebSecurityUtils.getCurrentToken());
		if (null == currentUserDeptInfo) {
			// 调用用户中心异常
			throw ExceptionFactory.create("MT_001");
		}
		UserVO userVo = currentUserDeptInfo.getData();
		if (null == userVo) {
			// 用户信息不存在
			throw ExceptionFactory.create("MT_002");
		}
		if(!user.getDeptId().equals(userVo.getDeptId())){
			// 用户部门发生变更提示：所在部门信息已修改，请刷新页面
			throw ExceptionFactory.create("MT_003");
		}
		// 检查用户是否具有保养实施权限
		Result<Boolean> mtPlanImplementPermission = deptClientService.checkUserPermission(user.getId(), "MT_PLAN_IMPLEMENT_MANAGE", WebSecurityUtils.getCurrentToken());
		if (null == mtPlanImplementPermission) {
			// 调用用户中心异常
			throw ExceptionFactory.create("MT_001");
		}
		boolean hasMtImplementPermission = mtPlanImplementPermission.getData();
		if (!hasMtImplementPermission) {
			// 实施权限已取消，请重新登录
			throw ExceptionFactory.create("MT_004");
		}
	}
	
}
