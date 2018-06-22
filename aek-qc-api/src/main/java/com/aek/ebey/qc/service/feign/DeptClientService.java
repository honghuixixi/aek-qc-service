package com.aek.ebey.qc.service.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aek.common.core.Result;
import com.aek.ebey.qc.model.vo.UserVO;
import com.aek.ebey.qc.query.QcUserDept;
import com.aek.ebey.qc.query.ReturnMsg;
import com.aek.ebey.qc.request.Dept;

/**
 * 用户远程调用接口value=${feign-sys.serviceId}
 */
@FeignClient(value="${feign-sys.serviceId}", fallback = DeptClientHystrix.class)
public interface DeptClientService {
	/**
	 * 调用接口，查找部门名称
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/sys/restAPI/getQcDeptList")
	Result<List<Dept>> getQcDeptList(@RequestParam(value="tenantId", required=true) Long tenantId,@RequestHeader("X-AEK56-Token") String token,@RequestParam(value="keyword", required=false)String keyword);

	/**
	 * 调用接口，是否可以新建
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/sys/restAPI/verifyCreateQcPlan")
	Result<ReturnMsg> verifyCreateQcPlan(@RequestBody QcUserDept qcUserDept,@RequestHeader("X-AEK56-Token") String token);
	
	  /**
	   * 查询用户信息
	   */
	  @RequestMapping(method = RequestMethod.GET, value = "/sys/invoke/user/getUser")
	  public Result<UserVO> getUser(@RequestParam(value = "id", required = true) Long id,@RequestParam(value = "tenantId", required = true) Long tenantId,@RequestHeader("X-AEK56-Token") String token);

	  /**
	   * 判断用户是否具有某个权限
	   * @param id
	   * @param rht
	   * @param token
	   * @return
	   */
	  @RequestMapping(method = RequestMethod.GET, value = "/sys/user/checkIsRht")
	  public Result<Boolean> checkUserPermission(@RequestParam("id") Long id,@RequestParam("rht") String rht,@RequestHeader("X-AEK56-Token") String token);
	  
	  /**
	   * 判断用户是否具有某个权限
	   * @param id
	   * @param rht
	   * @param token
	   * @return
	   */
	  @RequestMapping(method = RequestMethod.GET, value = "/sys/user/checkIsRhts")
	  public Result<Boolean> checkUserPermissions(@RequestParam("list")  List<Long> list,@RequestParam("rht") String rht,@RequestParam("tenantId") Long tenantId,@RequestHeader("X-AEK56-Token") String token);
	  
	  
	  /**
       * 判断用户是否具有计量检测权限，并且判断用户是否被停用
       * @param id
       * @param rht
       * @param token
       * @return
       */
      @RequestMapping(method = RequestMethod.GET, value = "/sys/restAPI/checkIsMsCheck")
      public Result<Integer> checkIsMsCheck(@RequestParam("userId") Long userId,@RequestHeader("X-AEK56-Token") String token);
}