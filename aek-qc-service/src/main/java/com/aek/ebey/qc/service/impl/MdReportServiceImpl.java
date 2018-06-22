package com.aek.ebey.qc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aek.common.core.BeanMapper;
import com.aek.common.core.Result;
import com.aek.common.core.base.BaseServiceImpl;
import com.aek.common.core.exception.ExceptionFactory;
import com.aek.common.core.serurity.WebSecurityUtils;
import com.aek.common.core.serurity.model.AuthUser;
import com.aek.ebey.qc.constant.MdConstant;
import com.aek.ebey.qc.mapper.MdReportAssetsMapper;
import com.aek.ebey.qc.mapper.MdReportMapper;
import com.aek.ebey.qc.mapper.MdReportTemplateItemMapper;
import com.aek.ebey.qc.mapper.MdReportTemplateItemOptionMapper;
import com.aek.ebey.qc.mapper.MdReportTemplateItemResultMapper;
import com.aek.ebey.qc.mapper.MdReportTemplateMapper;
import com.aek.ebey.qc.mapper.MdTemplateItemMapper;
import com.aek.ebey.qc.mapper.MdTemplateItemOptionMapper;
import com.aek.ebey.qc.mapper.MdTemplateMapper;
import com.aek.ebey.qc.model.MdReport;
import com.aek.ebey.qc.model.MdReportAssets;
import com.aek.ebey.qc.model.MdReportTemplate;
import com.aek.ebey.qc.model.MdReportTemplateItem;
import com.aek.ebey.qc.model.MdReportTemplateItemOption;
import com.aek.ebey.qc.model.MdReportTemplateItemResult;
import com.aek.ebey.qc.model.MdTemplate;
import com.aek.ebey.qc.model.bo.MdAssetsBO;
import com.aek.ebey.qc.model.bo.MdMaximumAllowableErrorDetailBO;
import com.aek.ebey.qc.model.bo.MdReportBO;
import com.aek.ebey.qc.model.bo.MdSysTemplateItemBO;
import com.aek.ebey.qc.model.vo.MdAssetsVO;
import com.aek.ebey.qc.model.vo.MdReportDetailVO;
import com.aek.ebey.qc.model.vo.MdReportPageVO;
import com.aek.ebey.qc.model.vo.MdReportTemplateChildItemVO;
import com.aek.ebey.qc.model.vo.MdReportTemplateDetailVO;
import com.aek.ebey.qc.model.vo.MdReportTemplateItemOptionVO;
import com.aek.ebey.qc.model.vo.MdReportTemplateItemVO;
import com.aek.ebey.qc.query.MdReportQuery;
import com.aek.ebey.qc.query.MdVerifyReportQuery;
import com.aek.ebey.qc.query.PmQAssets;
import com.aek.ebey.qc.request.SendMessage;
import com.aek.ebey.qc.service.MdReportAssetsService;
import com.aek.ebey.qc.service.MdReportService;
import com.aek.ebey.qc.service.MdReportTemplateItemOptionService;
import com.aek.ebey.qc.service.MdReportTemplateItemResultService;
import com.aek.ebey.qc.service.MdReportTemplateItemService;
import com.aek.ebey.qc.service.feign.AssetsClientService;
import com.aek.ebey.qc.service.feign.DeptClientService;
import com.aek.ebey.qc.service.feign.RepairClientService;
import com.aek.ebey.qc.utils.MdUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 质控检测单表 服务实现类
 * </p>
 *
 * @author Honghui
 * @since 2018-05-16
 */
@Transactional
@Service
public class MdReportServiceImpl extends BaseServiceImpl<MdReportMapper, MdReport> implements MdReportService {
	
	@Autowired
	private AssetsClientService assetsClientService;
	@Autowired
	private MdReportMapper mdReportMapper;
	@Autowired
	private MdReportAssetsService mdReportAssetsService;
	@Autowired
	private MdReportAssetsMapper mdReportAssetsMapper;
	@Autowired
	private MdReportTemplateMapper mdReportTemplateMapper;
	@Autowired
	private MdReportTemplateItemMapper mdReportTemplateItemMapper;
	@Autowired
	private MdReportTemplateItemOptionService mdReportTemplateItemOptionService;
	@Autowired
	private MdReportTemplateItemOptionMapper mdReportTemplateItemOptionMapper;
	@Autowired
	private MdReportTemplateItemService mdReportTemplateItemService;
	@Autowired
	private MdReportTemplateItemResultMapper mdReportTemplateItemResultMapper;
	@Autowired
	private MdReportTemplateItemResultService mdReportTemplateItemResultService;
	@Autowired
	private MdTemplateMapper mdTemplateMapper;
	@Autowired
	private MdTemplateItemMapper mdTemplateItemMapper;
	@Autowired
	private MdTemplateItemOptionMapper mdTemplateItemOptionMapper;
	@Autowired
	private DeptClientService deptClientService;
	@Autowired
	private RepairClientService repairClientService;
		
	private static final Logger logger = LoggerFactory.getLogger(MdReportServiceImpl.class);
	
	@Override
	public List<Long> getMdAssetsExist() {	
		AuthUser currentUser = WebSecurityUtils.getCurrentUser();
		Long tenantId = currentUser.getTenantId();
		List<Long> mdAssetsIds = mdReportMapper.getMdAssetsExist(tenantId);			
		return mdAssetsIds;
	}
		
	@Override
	public Long tempSave(MdReportBO mdReportBO, 
			MdAssetsBO mdAsset,
			List<MdMaximumAllowableErrorDetailBO> maximumAllowableErrorList,
			List<MdReportTemplateItemResult> mdItemResultList) {
		AuthUser user = WebSecurityUtils.getCurrentUser();
		String token = WebSecurityUtils.getCurrentToken();
		Long mdReportId = mdReportBO.getId();
		Date now = new Date();
		Long userId = user.getId();
		String userName = user.getRealName();
		Long tenantId = user.getTenantId();
		
		//无质控上报权限，请联系管理员确认
		Result<Boolean> checkUserPermission = deptClientService.checkUserPermission(userId, "MD_APPLY_MANAGE", token);
		if(checkUserPermission!=null&&"200".equals(checkUserPermission.getCode())){
			if(!checkUserPermission.getData())throw ExceptionFactory.create("MDR_011");
		}
		Long assetsId=null;
		if(mdAsset!=null){
			assetsId = mdAsset.getAssetsId();
			//资产已报损/退货，请选择其他设备填报
			Result<PmQAssets> assetsStatus = assetsClientService.getAssetsStatus(token, assetsId);
			if(assetsStatus!=null&&"200".equals(assetsStatus.getCode())){
				PmQAssets data = assetsStatus.getData();
				if(data.getStatus()==5)throw ExceptionFactory.create("MDR_012");
				if(data.getStatus()==6)throw ExceptionFactory.create("MDR_013");
			}
		}
		
			
		//最大允差
		if(maximumAllowableErrorList!=null&&maximumAllowableErrorList.size()>0){
			mdReportBO.setMaximumAllowableError(JSON.toJSONString(maximumAllowableErrorList));
		}
		mdReportBO.setUpdateBy(userId);
		mdReportBO.setUpdateTime(now);
		
		if(mdReportId!=null){
			MdReport mdReportDb = mdReportMapper.selectById(mdReportId);
			//单据不存在
			if(mdReportDb==null)throw ExceptionFactory.create("MDR_004");
			//此条质控上报单据已提交，请在列表页中查看
			if(mdReportDb!=null&&mdReportDb.getStatus()!=1)throw ExceptionFactory.create("MDR_003");
			
			if(assetsId!=null){
				//单据中设备状态校验
				int n = mdReportAssetsMapper.getAssetsById(mdReportId,assetsId);
				//该设备资产已经填写质控上报单据，请选择其他设备填报
				if(n>0)throw ExceptionFactory.create("MDR_002");
			}
					
			//更新质控检测单表
			mdReportBO.setCheckBy(userId);
			mdReportBO.setCheckName(userName);
			mdReportBO.setCheckTime(now);
			mdReportMapper.updateReport(mdReportBO);
			
			if(mdAsset!=null){
				//更新质控检测设备信息表
				mdAsset.setMdReportId(mdReportId);
				int n = mdReportAssetsMapper.updateReportAssets(mdAsset);
				if(n<1){
					Wrapper<MdReportAssets> wrapper = new EntityWrapper<MdReportAssets>();
					wrapper.eq("md_report_id", mdReportId).eq("del_flag", false);
					mdReportAssetsMapper.delete(wrapper);
					MdReportAssets mdReportAssets = BeanMapper.map(mdAsset, MdReportAssets.class);
					mdReportAssetsMapper.insert(mdReportAssets);
				}
			}
		
			//更新质控报告模板子项目结果表	
			if(mdItemResultList!=null){
				//删除旧答案			
				List<Long> templateItemIds = mdReportMapper.getReportTemplateItemIds(mdReportId);
				if(templateItemIds.size()>0){
					mdReportTemplateItemResultMapper.deletResultByItemId(templateItemIds);
				}
				if(mdItemResultList.size()>0){
					for (MdReportTemplateItemResult mdItemResult : mdItemResultList) {
						mdItemResult.setCreateTime(now);
						mdItemResult.setUpdateTime(now);
					}
					mdReportTemplateItemResultService.insertBatch(mdItemResultList);
				}											
			}
			return mdReportId;
		}else{
			//模板不存在，请重新选择
			Wrapper<MdTemplate> wrapper = new EntityWrapper<MdTemplate>();
			wrapper.eq("id", mdReportBO.getReportTemplateId()).eq("enable", true).eq("del_flag", false);
			List<MdTemplate> sourceTemplate = mdTemplateMapper.selectList(wrapper);
			if(sourceTemplate!=null&&sourceTemplate.size()<1)throw ExceptionFactory.create("MDR_014");
			
			if (mdAsset!=null) {
				//设备状态以及单据状态校验
				int n = mdReportAssetsMapper.getAssetsById(mdReportId,mdAsset.getAssetsId());
				//该设备资产已经填写质控上报单据，请选择其他设备填报
				if(n>0)throw ExceptionFactory.create("MDR_002");
			}		
		
			//插入质控检测单表
			MdReport mdReport = BeanMapper.map(mdReportBO, MdReport.class);
			mdReport.setTenantId(tenantId);
			mdReport.setMdNum(MdUtil.generateMdNum());
			mdReport.setCheckBy(userId);
			mdReport.setCheckName(userName);
			mdReport.setCheckTime(now);
			mdReport.setCreateBy(userId);
			mdReport.setCreateTime(now);
			mdReportMapper.insert(mdReport);
			if(mdAsset!=null){
				//插入质控检测设备信息表
				MdReportAssets mdReportAssets = BeanMapper.map(mdAsset, MdReportAssets.class);
				mdReportAssets.setMdReportId(mdReport.getId());
				mdReportAssetsMapper.insert(mdReportAssets);
			}		
			//插入质控报告模板表
			MdTemplate sysMdTemplate = mdTemplateMapper.selectById(mdReportBO.getReportTemplateId());
			MdReportTemplate mdReportTemplate=null;
			if(sysMdTemplate!=null){
				mdReportTemplate = BeanMapper.map(sysMdTemplate, MdReportTemplate.class);
				mdReportTemplate.setId(null);
				mdReportTemplate.setSourceTemplateId(sysMdTemplate.getId());
				mdReportTemplate.setMdReportId(mdReport.getId());
				mdReportTemplateMapper.insert(mdReportTemplate);
			}else {
				throw ExceptionFactory.create("MDR_001");
			}
			//插入质控报告模板子项目表
			Long templateId = sysMdTemplate.getId();
			List<MdSysTemplateItemBO> mdSysTemplateItemList = mdTemplateItemMapper.getMdSysTemplateItemList(templateId);
			if(mdSysTemplateItemList!=null&&mdSysTemplateItemList.size()>0){
				List<MdReportTemplateItem> mdReportTemplateItemList = BeanMapper.mapList(mdSysTemplateItemList, MdReportTemplateItem.class);
				if(mdReportTemplateItemList!=null&&mdReportTemplateItemList.size()>0){
					for (MdReportTemplateItem mdReportTemplateItem : mdReportTemplateItemList) {
						mdReportTemplateItem.setReportTemplateId(mdReportTemplate.getId());
					}
					mdReportTemplateItemService.insertBatch(mdReportTemplateItemList);
					//更新项目和插入质控模板项目选项表以及答案	
					for (MdReportTemplateItem mdReportTemplateItem : mdReportTemplateItemList) {						
						Long sourceItemId = mdReportTemplateItem.getSourceItemId();
						Long itemId = mdReportTemplateItem.getId();
						Long parentId = mdReportTemplateItem.getParentId();
						//二级项目父id同步
						if(parentId!=null){		
							Long currentId = mdReportTemplateItemMapper.getCurrentIdBySourceItemId(parentId,mdReportTemplate.getId());
							if(currentId!=null)mdReportTemplateItem.setParentId(currentId);
						}
						//选择项目关联选项
						List<MdReportTemplateItemOption> mdSysTemplateItemOptionList=new ArrayList<MdReportTemplateItemOption>();
						if(mdReportTemplateItem.getInputType()==2){										
							mdSysTemplateItemOptionList = mdTemplateItemOptionMapper.getMdSysTemplateItemOptionList(sourceItemId);
							if(mdSysTemplateItemOptionList!=null&&mdSysTemplateItemOptionList.size()>0){
								for (MdReportTemplateItemOption mdReportTemplateItemOption : mdSysTemplateItemOptionList) {
									mdReportTemplateItemOption.setItemId(itemId);
								}
								mdReportTemplateItemOptionService.insertBatch(mdSysTemplateItemOptionList);
							}						
						}
						//更新质控报告模板子项目结果表
						if (mdItemResultList!=null&&mdItemResultList.size()>0) {
							for (MdReportTemplateItemResult mdItemResultBO : mdItemResultList) {
								Long itemIdFront = mdItemResultBO.getItemId();
								if(itemIdFront.equals(sourceItemId)){					
									mdItemResultBO.setItemId(itemId);
									mdItemResultBO.setCreateTime(now);
									mdItemResultBO.setUpdateTime(now);																																							
								}
								if(mdReportTemplateItem.getInputType()==2&&mdItemResultBO.getItemId().equals(mdReportTemplateItem.getId())){
									String result = mdItemResultBO.getResult();
									Long resultOld = Long.parseLong(result);
									if(mdSysTemplateItemOptionList.size()>0){
										for (MdReportTemplateItemOption mdSysTemplateItemOption : mdSysTemplateItemOptionList) {
											if(resultOld.equals(mdSysTemplateItemOption.getSourceItemOptionId())){
												mdItemResultBO.setResult(String.valueOf(mdSysTemplateItemOption.getId()));
											}
										}
									}
								}												
							}							
						}					
					}
					if (mdItemResultList!=null&&mdItemResultList.size()>0)mdReportTemplateItemResultService.insertBatch(mdItemResultList);
					mdReportTemplateItemService.updateBatchById(mdReportTemplateItemList);
				}
			}
			Long id = mdReport.getId();
			if(id!=null){
				return id;
			}
		}
		return null;		
	}
	
	@Override
	public Long againTempSave(MdReportBO mdReportBO, MdAssetsBO mdAsset,
			List<MdMaximumAllowableErrorDetailBO> maximumAllowableErrorList,
			List<MdReportTemplateItemResult> mdItemResultList) {
		AuthUser user = WebSecurityUtils.getCurrentUser();
		String token = WebSecurityUtils.getCurrentToken();
		Long mdReportId = mdReportBO.getId();
		Date now = new Date();
		Long userId = user.getId();
		String userName = user.getRealName();
		Long tenantId = user.getTenantId();
		
		//无质控上报权限，请联系管理员确认
		Result<Boolean> checkUserPermission = deptClientService.checkUserPermission(userId, "MD_APPLY_MANAGE", token);
		if(checkUserPermission!=null&&"200".equals(checkUserPermission.getCode())){
			if(!checkUserPermission.getData())throw ExceptionFactory.create("MDR_011");
		}
		Long assetsId=null;
		if(mdAsset!=null){
			assetsId = mdAsset.getAssetsId();
			//资产已报损/退货，请选择其他设备填报
			Result<PmQAssets> assetsStatus = assetsClientService.getAssetsStatus(token, assetsId);
			if(assetsStatus!=null&&"200".equals(assetsStatus.getCode())){
				PmQAssets data = assetsStatus.getData();
				if(data.getStatus()==5)throw ExceptionFactory.create("MDR_012");
				if(data.getStatus()==6)throw ExceptionFactory.create("MDR_013");
			}
		}
		
			
		//最大允差
		if(maximumAllowableErrorList!=null&&maximumAllowableErrorList.size()>0){
			mdReportBO.setMaximumAllowableError(JSON.toJSONString(maximumAllowableErrorList));
		}
		mdReportBO.setUpdateBy(userId);
		mdReportBO.setUpdateTime(now);
		
		if(mdReportId!=null){
			MdReport mdReportDb = mdReportMapper.selectById(mdReportId);
			//单据不存在
			if(mdReportDb==null)throw ExceptionFactory.create("MDR_004");
			//此条质控上报单据已提交，请在列表页中查看
			if(mdReportDb!=null&&mdReportDb.getStatus()!=1)throw ExceptionFactory.create("MDR_003");
			
			if(assetsId!=null){
				//单据中设备状态校验
				int n = mdReportAssetsMapper.getAssetsById(mdReportId,assetsId);
				//该设备资产已经填写质控上报单据，请选择其他设备填报
				if(n>0)throw ExceptionFactory.create("MDR_002");
			}
					
			//更新质控检测单表
			mdReportBO.setCheckBy(userId);
			mdReportBO.setCheckTime(now);
			mdReportMapper.updateReport(mdReportBO);
			
			if(mdAsset!=null){
				//更新质控检测设备信息表
				mdAsset.setMdReportId(mdReportId);
				int n = mdReportAssetsMapper.updateReportAssets(mdAsset);
				if(n<1){
					Wrapper<MdReportAssets> wrapper = new EntityWrapper<MdReportAssets>();
					wrapper.eq("md_report_id", mdReportId).eq("del_flag", false);
					mdReportAssetsMapper.delete(wrapper);
					MdReportAssets mdReportAssets = BeanMapper.map(mdAsset, MdReportAssets.class);
					mdReportAssetsMapper.insert(mdReportAssets);
				}
			}
		
			//更新质控报告模板子项目结果表	
			if(mdItemResultList!=null){
				//删除旧答案			
				List<Long> templateItemIds = mdReportMapper.getReportTemplateItemIds(mdReportId);
				if(templateItemIds.size()>0){
					mdReportTemplateItemResultMapper.deletResultByItemId(templateItemIds);
				}
				if(mdItemResultList.size()>0){
					for (MdReportTemplateItemResult mdItemResult : mdItemResultList) {
						mdItemResult.setCreateTime(now);
						mdItemResult.setUpdateTime(now);
					}
					mdReportTemplateItemResultService.insertBatch(mdItemResultList);
				}											
			}
			return mdReportId;
		}else{
			//设备状态以及单据状态校验
			int n = mdReportAssetsMapper.getAssetsById(mdReportId,mdAsset.getAssetsId());
			//该设备资产已经填写质控上报单据，请选择其他设备填报
			if(n>0)throw ExceptionFactory.create("MDR_002");
		
			//插入质控检测单表
			MdReport mdReport = BeanMapper.map(mdReportBO, MdReport.class);
			mdReport.setTenantId(tenantId);
			mdReport.setMdNum(MdUtil.generateMdNum());
			mdReport.setCheckBy(userId);
			mdReport.setCheckName(userName);
			mdReport.setCheckTime(now);
			mdReport.setCreateBy(userId);
			mdReport.setCreateTime(now);
			mdReportMapper.insert(mdReport);
			if(mdAsset!=null){
				//插入质控检测设备信息表
				MdReportAssets mdReportAssets = BeanMapper.map(mdAsset, MdReportAssets.class);
				mdReportAssets.setMdReportId(mdReport.getId());
				mdReportAssetsMapper.insert(mdReportAssets);
			}	
			//插入质控报告模板表
			MdReportTemplate mdReportTemplateOld = mdReportTemplateMapper.selectById(mdReportBO.getReportTemplateId());
			MdReportTemplate mdReportTemplateNew=null;
			if(mdReportTemplateOld!=null){
				mdReportTemplateNew = BeanMapper.map(mdReportTemplateOld, MdReportTemplate.class);
				mdReportTemplateNew.setId(null);
				mdReportTemplateNew.setMdReportId(mdReport.getId());
				mdReportTemplateMapper.insert(mdReportTemplateNew);
			}else {
				logger.debug("======================="+"old template not exist!!!"+"=======================");
				throw ExceptionFactory.create("MDR_001");
			}
			//item
			 Wrapper<MdReportTemplateItem> wrapper = new EntityWrapper<MdReportTemplateItem>();
			 wrapper.eq("report_template_id", mdReportTemplateOld.getId());
			 List<MdReportTemplateItem> itemList = mdReportTemplateItemMapper.selectList(wrapper);
			 if(itemList!=null&&itemList.size()>0) {			
				for (MdReportTemplateItem item : itemList) {
					item.setCopy(item.getId());
					item.setId(null);
					Long templateId = mdReportTemplateNew.getId();
					item.setReportTemplateId(templateId);			
				}			
				mdReportTemplateItemService.insertBatch(itemList);
			}
			if(itemList!=null&&itemList.size()>0){
				for (MdReportTemplateItem item : itemList) {
					Long templateId = item.getReportTemplateId();
					Long parentId = item.getParentId();
					Long copyId = item.getCopy();
					Long id = item.getId();
					if(parentId!=null){
						//二级项目父id同步
						if(parentId!=null){
							MdReportTemplateItem itemOld = mdReportTemplateItemMapper.selectById(parentId);
							Long sourceItemId = itemOld.getSourceItemId();
							Long currentId = mdReportTemplateItemMapper.getCurrentIdBySourceItemId(sourceItemId,templateId);
							if(currentId!=null)item.setParentId(currentId);
						}
					}
					//选择项目关联选项
					List<MdReportTemplateItemOption> collection = new ArrayList<MdReportTemplateItemOption>();
					if(item.getInputType()==2){
						Wrapper<MdReportTemplateItemOption> wrapper2 = new EntityWrapper<MdReportTemplateItemOption>();
						wrapper2.eq("item_id", copyId);
						collection = mdReportTemplateItemOptionMapper.selectList(wrapper2);
						if(collection!=null&&collection.size()>0){
							for (MdReportTemplateItemOption c : collection) {
								c.setCopy(c.getId());
								c.setId(null);
								c.setItemId(id);
							}
							mdReportTemplateItemOptionService.insertBatch(collection);
						}
					}
					//更新质控报告模板子项目结果表
					if (mdItemResultList!=null&&mdItemResultList.size()>0) {
						for (MdReportTemplateItemResult mdItemResultBO : mdItemResultList) {
							Long itemIdOld = mdItemResultBO.getItemId();
							if(item.getInputType()==2&&itemIdOld.equals(copyId)){						
								String result = mdItemResultBO.getResult();						
								Long resultOld = Long.parseLong(result);
								MdReportTemplateItemOption optionOld = mdReportTemplateItemOptionMapper.selectById(resultOld);
								Long sourceItemOptionId = optionOld.getSourceItemOptionId();
								Wrapper<MdReportTemplateItemOption> wrapper3 = new EntityWrapper<MdReportTemplateItemOption>();
								wrapper3.eq("source_item_option_id", sourceItemOptionId).eq("item_id", id);
								List<MdReportTemplateItemOption> list = mdReportTemplateItemOptionMapper.selectList(wrapper3);
								if(list!=null&&list.size()>0)mdItemResultBO.setResult(String.valueOf(list.get(0).getId()));					
							}
							if(itemIdOld.equals(copyId)){					
								mdItemResultBO.setItemId(id);
								mdItemResultBO.setCreateTime(now);
								mdItemResultBO.setUpdateTime(now);																																							
							}
						}							
					}					
				}
				if (mdItemResultList!=null&&mdItemResultList.size()>0)mdReportTemplateItemResultService.insertBatch(mdItemResultList);
				mdReportTemplateItemService.updateBatchById(itemList);
			}		 
			Long id = mdReport.getId();
			if(id!=null){
				return id;
			}
		}			
		return null;
	}
	
	@Override
	public void againSave(MdReportBO mdReportBO,
			MdAssetsBO mdAsset,
			List<MdMaximumAllowableErrorDetailBO> maximumAllowableErrorList,
			List<MdReportTemplateItemResult> mdItemResultList) {
		AuthUser user = WebSecurityUtils.getCurrentUser();
		String token = WebSecurityUtils.getCurrentToken();
		Date now = new Date();
		Long userId = user.getId();
		String userName = user.getRealName();
		Long tenantId = user.getTenantId();
		Long mdReportId = mdReportBO.getId();
		if(mdAsset==null)throw ExceptionFactory.create("MDR_016");
		Long assetsId = mdAsset.getAssetsId();
		
		//无质控上报权限，请联系管理员确认
		Result<Boolean> checkUserPermission = deptClientService.checkUserPermission(userId, "MD_APPLY_MANAGE", token);
		if(checkUserPermission!=null&&"200".equals(checkUserPermission.getCode())){
			if(!checkUserPermission.getData())throw ExceptionFactory.create("MDR_011");
		}
		//资产已报损/退货，请选择其他设备填报
		Result<PmQAssets> assetsStatus = assetsClientService.getAssetsStatus(token, assetsId);
		if(assetsStatus!=null&&"200".equals(assetsStatus.getCode())){
			PmQAssets data = assetsStatus.getData();
			if(data.getStatus()==5)throw ExceptionFactory.create("MDR_012");
			if(data.getStatus()==6)throw ExceptionFactory.create("MDR_013");
		}
			
		//最大允差
		if(maximumAllowableErrorList!=null&&maximumAllowableErrorList.size()>0){
			mdReportBO.setMaximumAllowableError(JSON.toJSONString(maximumAllowableErrorList));
		}
		mdReportBO.setUpdateBy(userId);
		mdReportBO.setUpdateTime(now);
				
		if(mdReportId!=null){
			MdReport mdReportDb = mdReportMapper.selectById(mdReportId);
			//单据不存在
			if(mdReportDb==null)throw ExceptionFactory.create("MDR_004");
			//此条质控上报单据已提交，请在列表页中查看
			if(mdReportDb!=null&&mdReportDb.getStatus()!=1)throw ExceptionFactory.create("MDR_003");
			//单据中设备状态校验
			int n = mdReportAssetsMapper.getAssetsById(mdReportId,assetsId);
			//该设备资产已经填写质控上报单据，请选择其他设备填报
			if(n>0)throw ExceptionFactory.create("MDR_002");
			
			//更新质控检测单表
			mdReportBO.setCheckBy(userId);
			mdReportBO.setCheckTime(now);
			mdReportMapper.updateReport(mdReportBO);
			
			//更新质控检测设备信息表
			mdAsset.setMdReportId(mdReportId);
			int k = mdReportAssetsMapper.updateReportAssets(mdAsset);
			if(k<1){
				Wrapper<MdReportAssets> wrapper = new EntityWrapper<MdReportAssets>();
				wrapper.eq("md_report_id", mdReportId).eq("del_flag", false);
				mdReportAssetsMapper.delete(wrapper);
				MdReportAssets reportAssets = BeanMapper.map(mdAsset, MdReportAssets.class);
				mdReportAssetsMapper.insert(reportAssets);
			}
			
			//更新质控报告模板子项目结果表	
			if(mdItemResultList!=null){
				//删除旧答案			
				List<Long> templateItemIds = mdReportMapper.getReportTemplateItemIds(mdReportId);
				if(templateItemIds.size()>0){
					mdReportTemplateItemResultMapper.deletResultByItemId(templateItemIds);
				}
				if(mdItemResultList.size()>0){
					for (MdReportTemplateItemResult mdItemResult : mdItemResultList) {
						mdItemResult.setCreateTime(now);
						mdItemResult.setUpdateTime(now);
					}
					mdReportTemplateItemResultService.insertBatch(mdItemResultList);
				}											
			}
		}else{
			//设备状态以及单据状态校验
			int n = mdReportAssetsMapper.getAssetsById(mdReportId,assetsId);
			//该设备资产已经填写质控上报单据，请选择其他设备填报
			if(n>0)throw ExceptionFactory.create("MDR_002");
			
			//插入质控检测单表
			MdReport mdReport = BeanMapper.map(mdReportBO, MdReport.class);
			mdReport.setTenantId(tenantId);
			mdReport.setMdNum(MdUtil.generateMdNum());
			mdReport.setCheckBy(userId);
			mdReport.setCheckName(userName);
			mdReport.setCheckTime(now);
			mdReport.setCreateBy(userId);
			mdReport.setCreateTime(now);
			mdReportMapper.insert(mdReport);
			//插入质控检测设备信息表
			MdReportAssets mdReportAssets = BeanMapper.map(mdAsset, MdReportAssets.class);
			mdReportAssets.setMdReportId(mdReport.getId());
			mdReportAssetsMapper.insert(mdReportAssets);
			//插入质控报告模板表
			MdReportTemplate mdReportTemplateOld = mdReportTemplateMapper.selectById(mdReportBO.getReportTemplateId());
			MdReportTemplate mdReportTemplateNew=null;
			if(mdReportTemplateOld!=null){
				mdReportTemplateNew = BeanMapper.map(mdReportTemplateOld, MdReportTemplate.class);
				mdReportTemplateNew.setId(null);
				mdReportTemplateNew.setMdReportId(mdReport.getId());
				mdReportTemplateMapper.insert(mdReportTemplateNew);
			}else {
				logger.debug("======================="+"old template not exist!!!"+"=======================");
				throw ExceptionFactory.create("MDR_001");
			}
			//item
			 Wrapper<MdReportTemplateItem> wrapper = new EntityWrapper<MdReportTemplateItem>();
			 wrapper.eq("report_template_id", mdReportTemplateOld.getId());
			 List<MdReportTemplateItem> itemList = mdReportTemplateItemMapper.selectList(wrapper);
			 if(itemList!=null&&itemList.size()>0) {			
				for (MdReportTemplateItem item : itemList) {
					item.setCopy(item.getId());
					item.setId(null);
					Long templateId = mdReportTemplateNew.getId();
					item.setReportTemplateId(templateId);			
				}			
				mdReportTemplateItemService.insertBatch(itemList);
			}
			if(itemList!=null&&itemList.size()>0){
				for (MdReportTemplateItem item : itemList) {
					Long templateId = item.getReportTemplateId();
					Long parentId = item.getParentId();
					Long copyId = item.getCopy();
					Long id = item.getId();
					if(parentId!=null){
						//二级项目父id同步
						if(parentId!=null){
							MdReportTemplateItem itemOld = mdReportTemplateItemMapper.selectById(parentId);
							Long sourceItemId = itemOld.getSourceItemId();
							Long currentId = mdReportTemplateItemMapper.getCurrentIdBySourceItemId(sourceItemId,templateId);
							if(currentId!=null)item.setParentId(currentId);
						}
					}
					//选择项目关联选项
					List<MdReportTemplateItemOption> collection = new ArrayList<MdReportTemplateItemOption>();
					if(item.getInputType()==2){
						Wrapper<MdReportTemplateItemOption> wrapper2 = new EntityWrapper<MdReportTemplateItemOption>();
						wrapper2.eq("item_id", copyId);
						collection = mdReportTemplateItemOptionMapper.selectList(wrapper2);
						if(collection!=null&&collection.size()>0){
							for (MdReportTemplateItemOption c : collection) {
								c.setCopy(c.getId());
								c.setId(null);
								c.setItemId(id);
							}
							mdReportTemplateItemOptionService.insertBatch(collection);
						}
					}
					//更新质控报告模板子项目结果表
					if (mdItemResultList!=null&&mdItemResultList.size()>0) {
						for (MdReportTemplateItemResult mdItemResultBO : mdItemResultList) {
							Long itemIdOld = mdItemResultBO.getItemId();
							if(item.getInputType()==2&&itemIdOld.equals(copyId)){						
								String result = mdItemResultBO.getResult();						
								Long resultOld = Long.parseLong(result);
								MdReportTemplateItemOption optionOld = mdReportTemplateItemOptionMapper.selectById(resultOld);
								Long sourceItemOptionId = optionOld.getSourceItemOptionId();
								Wrapper<MdReportTemplateItemOption> wrapper3 = new EntityWrapper<MdReportTemplateItemOption>();
								wrapper3.eq("source_item_option_id", sourceItemOptionId).eq("item_id", id);
								List<MdReportTemplateItemOption> list = mdReportTemplateItemOptionMapper.selectList(wrapper3);
								if(list!=null&&list.size()>0)mdItemResultBO.setResult(String.valueOf(list.get(0).getId()));					
							}
							if(itemIdOld.equals(copyId)){					
								mdItemResultBO.setItemId(id);
								mdItemResultBO.setCreateTime(now);
								mdItemResultBO.setUpdateTime(now);																																							
							}
						}							
					}					
				}			
			}	
			if (mdItemResultList!=null&&mdItemResultList.size()>0)mdReportTemplateItemResultService.insertBatch(mdItemResultList);
			mdReportTemplateItemService.updateBatchById(itemList);
		}
		
	}

	@Override
	public void save(MdReportBO mdReportBO,
			MdAssetsBO mdAsset,
			List<MdMaximumAllowableErrorDetailBO> maximumAllowableErrorList, 
			List<MdReportTemplateItemResult> mdItemResultList) {					
		AuthUser user = WebSecurityUtils.getCurrentUser();
		String token = WebSecurityUtils.getCurrentToken();
		Long mdReportId = mdReportBO.getId();
		Date now = new Date();
		Long userId = user.getId();
		String userName = user.getRealName();
		Long tenantId = user.getTenantId();
		if(mdAsset==null)throw ExceptionFactory.create("MDR_016");
		Long assetsId = mdAsset.getAssetsId();
		
		//无质控上报权限，请联系管理员确认
		Result<Boolean> checkUserPermission = deptClientService.checkUserPermission(userId, "MD_APPLY_MANAGE", token);
		if(checkUserPermission!=null&&"200".equals(checkUserPermission.getCode())){
			if(!checkUserPermission.getData())throw ExceptionFactory.create("MDR_011");
		}
		//资产已报损/退货，请选择其他设备填报
		Result<PmQAssets> assetsStatus = assetsClientService.getAssetsStatus(token, assetsId);
		if(assetsStatus!=null&&"200".equals(assetsStatus.getCode())){
			PmQAssets data = assetsStatus.getData();
			if(data.getStatus()==5)throw ExceptionFactory.create("MDR_012");
			if(data.getStatus()==6)throw ExceptionFactory.create("MDR_013");
		}
			
		//最大允差
		if(maximumAllowableErrorList!=null&&maximumAllowableErrorList.size()>0){
			mdReportBO.setMaximumAllowableError(JSON.toJSONString(maximumAllowableErrorList));
		}
		mdReportBO.setUpdateBy(userId);
		mdReportBO.setUpdateTime(now);
		
		if(mdReportId!=null){
			MdReport mdReportDb = mdReportMapper.selectById(mdReportId);
			//单据不存在
			if(mdReportDb==null)throw ExceptionFactory.create("MDR_004");
			//此条质控上报单据已提交，请在列表页中查看
			if(mdReportDb!=null&&mdReportDb.getStatus()!=1)throw ExceptionFactory.create("MDR_003");
			//单据中设备状态校验
			int n = mdReportAssetsMapper.getAssetsById(mdReportId,assetsId);
			//该设备资产已经填写质控上报单据，请选择其他设备填报
			if(n>0)throw ExceptionFactory.create("MDR_002");
			
			//更新质控检测单表
			mdReportBO.setCheckBy(userId);
			mdReportBO.setCheckTime(now);
			mdReportMapper.updateReport(mdReportBO);
			
			//更新质控检测设备信息表
			mdAsset.setMdReportId(mdReportId);
			int k = mdReportAssetsMapper.updateReportAssets(mdAsset);
			if(k<1){
				Wrapper<MdReportAssets> wrapper = new EntityWrapper<MdReportAssets>();
				wrapper.eq("md_report_id", mdReportId).eq("del_flag", false);
				mdReportAssetsMapper.delete(wrapper);
				MdReportAssets reportAssets = BeanMapper.map(mdAsset, MdReportAssets.class);
				mdReportAssetsMapper.insert(reportAssets);
			}
			
			//更新质控报告模板子项目结果表	
			if(mdItemResultList!=null){
				//删除旧答案			
				List<Long> templateItemIds = mdReportMapper.getReportTemplateItemIds(mdReportId);
				if(templateItemIds.size()>0){
					mdReportTemplateItemResultMapper.deletResultByItemId(templateItemIds);
				}
				if(mdItemResultList.size()>0){
					for (MdReportTemplateItemResult mdItemResult : mdItemResultList) {
						mdItemResult.setCreateTime(now);
						mdItemResult.setUpdateTime(now);
					}
					mdReportTemplateItemResultService.insertBatch(mdItemResultList);
				}											
			}		
		}else{
			//模板不存在，请重新选择
			Wrapper<MdTemplate> wrapper = new EntityWrapper<MdTemplate>();
			wrapper.eq("id", mdReportBO.getReportTemplateId()).eq("enable", true).eq("del_flag", false);
			List<MdTemplate> sourceTemplate = mdTemplateMapper.selectList(wrapper);
			if(sourceTemplate!=null&&sourceTemplate.size()<1)throw ExceptionFactory.create("MDR_014");
			
			//设备状态以及单据状态校验
			int n = mdReportAssetsMapper.getAssetsById(mdReportId,mdAsset.getAssetsId());
			//该设备资产已经填写质控上报单据，请选择其他设备填报
			if(n>0)throw ExceptionFactory.create("MDR_002");
		
			//插入质控检测单表
			MdReport mdReport = BeanMapper.map(mdReportBO, MdReport.class);
			mdReport.setTenantId(tenantId);
			mdReport.setMdNum(MdUtil.generateMdNum());
			mdReport.setCheckBy(userId);
			mdReport.setCheckName(userName);
			mdReport.setCheckTime(now);
			mdReport.setCreateBy(userId);
			mdReport.setCreateTime(now);
			mdReportMapper.insert(mdReport);
			//插入质控检测设备信息表
			MdReportAssets mdReportAssets = BeanMapper.map(mdAsset, MdReportAssets.class);
			mdReportAssets.setMdReportId(mdReport.getId());
			mdReportAssetsMapper.insert(mdReportAssets);
			//插入质控报告模板表
			MdTemplate sysMdTemplate = mdTemplateMapper.selectById(mdReportBO.getReportTemplateId());
			MdReportTemplate mdReportTemplate=null;
			if(sysMdTemplate!=null){
				mdReportTemplate = BeanMapper.map(sysMdTemplate, MdReportTemplate.class);
				mdReportTemplate.setId(null);
				mdReportTemplate.setSourceTemplateId(sysMdTemplate.getId());
				mdReportTemplate.setMdReportId(mdReport.getId());
				mdReportTemplateMapper.insert(mdReportTemplate);
			}else {
				throw ExceptionFactory.create("MDR_001");
			}
			//插入质控报告模板子项目表
			Long templateId = sysMdTemplate.getId();
			List<MdSysTemplateItemBO> mdSysTemplateItemList = mdTemplateItemMapper.getMdSysTemplateItemList(templateId);
			if(mdSysTemplateItemList!=null&&mdSysTemplateItemList.size()>0){
				List<MdReportTemplateItem> mdReportTemplateItemList = BeanMapper.mapList(mdSysTemplateItemList, MdReportTemplateItem.class);
				if(mdReportTemplateItemList!=null&&mdReportTemplateItemList.size()>0){
					for (MdReportTemplateItem mdReportTemplateItem : mdReportTemplateItemList) {
						mdReportTemplateItem.setReportTemplateId(mdReportTemplate.getId());
					}
					mdReportTemplateItemService.insertBatch(mdReportTemplateItemList);
					//更新项目和插入质控模板项目选项表以及答案	
					for (MdReportTemplateItem mdReportTemplateItem : mdReportTemplateItemList) {						
						Long sourceItemId = mdReportTemplateItem.getSourceItemId();
						Long itemId = mdReportTemplateItem.getId();
						Long parentId = mdReportTemplateItem.getParentId();
						//二级项目父id同步
						if(parentId!=null){
							Long currentId = mdReportTemplateItemMapper.getCurrentIdBySourceItemId(parentId,mdReportTemplate.getId());
							if(currentId!=null)mdReportTemplateItem.setParentId(currentId);
						}
						//选择项目关联选项
						List<MdReportTemplateItemOption> mdSysTemplateItemOptionList=new ArrayList<MdReportTemplateItemOption>();
						if(mdReportTemplateItem.getInputType()==2){										
							mdSysTemplateItemOptionList = mdTemplateItemOptionMapper.getMdSysTemplateItemOptionList(sourceItemId);
							if(mdSysTemplateItemOptionList!=null&&mdSysTemplateItemOptionList.size()>0){
								for (MdReportTemplateItemOption mdReportTemplateItemOption : mdSysTemplateItemOptionList) {
									mdReportTemplateItemOption.setItemId(itemId);
								}
								mdReportTemplateItemOptionService.insertBatch(mdSysTemplateItemOptionList);
							}						
						}
						//更新质控报告模板子项目结果表
						if (mdItemResultList!=null&&mdItemResultList.size()>0) {
							for (MdReportTemplateItemResult mdItemResultBO : mdItemResultList) {
								Long itemIdFront = mdItemResultBO.getItemId();
								if(itemIdFront.equals(sourceItemId)){					
									mdItemResultBO.setItemId(itemId);
									mdItemResultBO.setCreateTime(now);
									mdItemResultBO.setUpdateTime(now);																																							
								}
								if(mdReportTemplateItem.getInputType()==2&&mdItemResultBO.getItemId().equals(mdReportTemplateItem.getId())){
									String result = mdItemResultBO.getResult();
									Long resultOld = Long.parseLong(result);
									if(mdSysTemplateItemOptionList.size()>0){
										for (MdReportTemplateItemOption mdSysTemplateItemOption : mdSysTemplateItemOptionList) {
											if(resultOld.equals(mdSysTemplateItemOption.getSourceItemOptionId())){
												mdItemResultBO.setResult(String.valueOf(mdSysTemplateItemOption.getId()));
											}
										}
									}
								}
							}					
						}					
					}
					if (mdItemResultList!=null&&mdItemResultList.size()>0)mdReportTemplateItemResultService.insertBatch(mdItemResultList);
					mdReportTemplateItemService.updateBatchById(mdReportTemplateItemList);
				}
			}								
		}		
	}

	@Override
	public Page<MdReportPageVO> getApplyMdReportPage(MdReportQuery query) {
		Page<MdReportPageVO> page = query.getPage();
		AuthUser user = WebSecurityUtils.getCurrentUser();
		String keyword = StringUtils.trimToNull(query.getKeyword());
		if(keyword !=null){
			if (keyword.startsWith("%") || keyword.startsWith("[") || keyword.startsWith("[]") || keyword.startsWith("_")) {
				query.setKeyword('\\'+keyword);
			}
		}
		List<MdReportPageVO> mdReportList= mdReportMapper.getApplyMdReportPage(page,query,user);
		if(mdReportList!=null&&mdReportList.size()>0){
			for (MdReportPageVO mdReport : mdReportList) {
				mdReport.setStatusStr(MdConstant.STATUS_MAP.get(mdReport.getStatus()));
			}
		}
		page.setRecords(mdReportList);
		return page;
	}
	
	@Override
	public Page<MdReportPageVO> getVerifyMdReportPage(MdVerifyReportQuery query) {
		Page<MdReportPageVO> page = query.getPage();
		AuthUser user = WebSecurityUtils.getCurrentUser();
		String keyword = StringUtils.trimToNull(query.getKeyword());
		if(keyword !=null){
			if (keyword.startsWith("%") || keyword.startsWith("[") || keyword.startsWith("[]") || keyword.startsWith("_")) {
				query.setKeyword('\\'+keyword);
			}
		}
		List<MdReportPageVO> mdReportList= mdReportMapper.getVerifyMdReportPage(page,query,user);
		if(mdReportList!=null&&mdReportList.size()>0){
			for (MdReportPageVO mdReport : mdReportList) {
				Integer status = mdReport.getStatus();
				if(status==3)mdReport.setStatus(4);
				if(status==4)mdReport.setStatus(3);
				mdReport.setStatusStr(MdConstant.STATUS_MAP.get(mdReport.getStatus()));
			}
		}
		page.setRecords(mdReportList);
		return page;
	}
	
	@Override
	public Page<MdReportPageVO> getArchiveMdReportPage(MdReportQuery query) {
		Page<MdReportPageVO> page = query.getPage();
		AuthUser user = WebSecurityUtils.getCurrentUser();
		String keyword = StringUtils.trimToNull(query.getKeyword());
		if(keyword !=null){
			if (keyword.startsWith("%") || keyword.startsWith("[") || keyword.startsWith("[]") || keyword.startsWith("_")) {
				query.setKeyword('\\'+keyword);
			}
		}
		List<MdReportPageVO> mdReportList= mdReportMapper.getArchiveMdReportPage(page,query,user);	
		page.setRecords(mdReportList);
		return page;
	}

	@Override
	public MdReportDetailVO getMdReportDetail(Long id) {		
		MdReport mdReportDb = mdReportMapper.selectById(id);
		if(mdReportDb==null)throw ExceptionFactory.create("MDR_004");
		MdReportDetailVO mdReportDetailVO = BeanMapper.map(mdReportDb, MdReportDetailVO.class);
		AuthUser currentUser = WebSecurityUtils.getCurrentUser();
		mdReportDetailVO.setTenantName(currentUser.getTenantName());
		//最大允差
		String maximumAllowableError = mdReportDb.getMaximumAllowableError();
		if(maximumAllowableError!=null || !"".equals(maximumAllowableError)){
			List<MdMaximumAllowableErrorDetailBO> maximumAllowableErrorList = JSON.parseArray(maximumAllowableError, MdMaximumAllowableErrorDetailBO.class);
			mdReportDetailVO.setMaximumAllowableErrorList(maximumAllowableErrorList);
		}
		//质控关联设备
		Wrapper<MdReportAssets> wrapper = new EntityWrapper<MdReportAssets>();
		wrapper.eq("md_report_id", id).eq("del_flag", false);
		List<MdReportAssets> mdAssetList = mdReportAssetsMapper.selectList(wrapper);
		if(mdAssetList!=null&&mdAssetList.size()>0){
			MdReportAssets mdAsset = mdAssetList.get(0);
			mdReportDetailVO.setMdAsset(mdAsset);
		}
		//质控单关联模版		
		MdReportTemplateDetailVO reportTemplate = mdReportTemplateMapper.getReportTemplate(id);
		//组装质控模版明细
		if(reportTemplate!=null){
			Long templateId = reportTemplate.getId();
			//一级项目
			List<MdReportTemplateItemVO> reportTemplateItemList = mdReportTemplateMapper.getReportTemplateItemList(templateId);
			if(reportTemplateItemList!=null&&reportTemplateItemList.size()>0){
				for (MdReportTemplateItemVO mdReportTemplateItemVO : reportTemplateItemList) {
					Integer inputType = mdReportTemplateItemVO.getInputType();
					Long parentItemId = mdReportTemplateItemVO.getId();
					//组装二级文本
					if(inputType==1){					
						List<MdReportTemplateChildItemVO> reportTemplateChidItemList = mdReportTemplateMapper.getReportTemplateChidItemList(parentItemId);
						mdReportTemplateItemVO.setChildItems(reportTemplateChidItemList);
					}
					//组装选择项
					if(inputType==2){
						List<MdReportTemplateItemOptionVO> childItemOptions = mdReportTemplateItemOptionMapper.getChildItemOptions(parentItemId);
						mdReportTemplateItemVO.setChildItemOptions(childItemOptions);
					}
				}
				reportTemplate.setItems(reportTemplateItemList);
				//组装答案集合
				List<MdReportTemplateItemResult> results = mdReportTemplateItemResultMapper.getAllItemsByTemplateId(templateId);
				reportTemplate.setResults(results);
			}
		}
		mdReportDetailVO.setTemplateDetail(reportTemplate);
		return mdReportDetailVO;
	}

	@Override
	public void recallMdReport(Long id) {
		MdReport mdReportDb = mdReportMapper.selectById(id);
		if(mdReportDb==null)throw ExceptionFactory.create("MDR_004");
		AuthUser currentUser = WebSecurityUtils.getCurrentUser();
		String token = WebSecurityUtils.getCurrentToken();
		Date now = new Date();
		Long userId = currentUser.getId();
		Long createBy = mdReportDb.getCreateBy();
		//无质控上报权限，请联系管理员确认
		Result<Boolean> checkUserPermission = deptClientService.checkUserPermission(userId, "MD_APPLY_MANAGE", token);
		if(checkUserPermission!=null&&"200".equals(checkUserPermission.getCode())){
			if(!checkUserPermission.getData())throw ExceptionFactory.create("MDR_011");
		}
		if(userId.longValue() != createBy.longValue())throw ExceptionFactory.create("MDR_005");
		Integer status = mdReportDb.getStatus();
		if(status==3 || status==4)throw ExceptionFactory.create("MDR_015");
		if(status==5)throw ExceptionFactory.create("MDR_006");	
		mdReportDb.setUpdateBy(userId);
		mdReportDb.setUpdateTime(now);
		mdReportDb.setStatus(5);
		mdReportMapper.updateById(mdReportDb);
	}

	@Override
	public void verifyMdReport(Long id, String verifyRemark, Integer verifyResult) {
		AuthUser currentUser = WebSecurityUtils.getCurrentUser();
		String token = WebSecurityUtils.getCurrentToken();
		Long userId = currentUser.getId();
		//无质控审核权限，请联系管理员确认
		Result<Boolean> checkUserPermission = deptClientService.checkUserPermission(userId, "MD_CHECK_MANAGE", token);
		if(checkUserPermission!=null&&"200".equals(checkUserPermission.getCode())){
			if(!checkUserPermission.getData())throw ExceptionFactory.create("MDR_011");
		}
		
		MdReport mdReportDb = mdReportMapper.selectById(id);
		if(mdReportDb==null)throw ExceptionFactory.create("MDR_004");
		Integer status = mdReportDb.getStatus();
		//暂存状态无法审核
		if(status==1)throw ExceptionFactory.create("MDR_007");
		//单据已审核，请返回审核列表
		if(status==3 || status==4)throw ExceptionFactory.create("MDR_008");
		//单据已被撤回
		if(status==5)throw ExceptionFactory.create("MDR_009");
			
		Date now = new Date();
		String userName = currentUser.getRealName();
		mdReportDb.setVerifyRemark(verifyRemark);
		mdReportDb.setVerifyResult(verifyResult);
		mdReportDb.setUpdateBy(userId);
		mdReportDb.setUpdateTime(now);
		if(verifyResult==0)mdReportDb.setStatus(4);
		if(verifyResult==1){
			mdReportDb.setStatus(3);
			mdReportDb.setMdArchiveNum(MdUtil.generateMdArchiveNum());
		}
		mdReportDb.setVerifyBy(userId);
		mdReportDb.setVerifyName(userName);
		mdReportDb.setVerifyTime(now);
		mdReportMapper.updateById(mdReportDb);
		//质控审核通过、未通过发消息
		SendMessage msg = new SendMessage();
		msg.setUserId(mdReportDb.getCreateBy());
		msg.setModuleId(id);
		msg.setRemarks(userName);
		if(verifyResult==0){		
			msg.setStatus(9);			
			msg.setMessageContent("质控单审核未通过，查看详情");
		}else if (verifyResult==1) {
			msg.setStatus(9);			
			msg.setMessageContent("质控单审核通过，查看详情");
		}	
		Result<Object> rst = repairClientService.send(msg);
		if(rst!=null&&"200".equals(rst.getCode())){
			logger.debug("=============="+"质控审核消息发送成功"+"================");
		}else {
			logger.debug("=============="+"质控审核消息发送失败"+"================");
		}
	}

	@Override
	public void deleteMdReport(Long id) {
		AuthUser currentUser = WebSecurityUtils.getCurrentUser();
		String token = WebSecurityUtils.getCurrentToken();
		Long userId = currentUser.getId();
		//无质控上报权限，请联系管理员确认
		Result<Boolean> checkUserPermission = deptClientService.checkUserPermission(userId, "MD_APPLY_MANAGE", token);
		if(checkUserPermission!=null&&"200".equals(checkUserPermission.getCode())){
			if(!checkUserPermission.getData())throw ExceptionFactory.create("MDR_011");
		}
		MdReport mdReportDb = mdReportMapper.selectById(id);
		//质控单据不存在！
		if(mdReportDb==null)throw ExceptionFactory.create("MDR_004");
		Wrapper<MdReport> wrapper = new EntityWrapper<MdReport>();
		wrapper.eq("id", id).eq("del_flag", true);
		List<MdReport> selectList = mdReportMapper.selectList(wrapper);
		if(selectList!=null&&selectList.size()>0){
			MdReport mdReportDEL = selectList.get(0);
			//此条质控上报单据已作废
			if(mdReportDEL!=null)throw ExceptionFactory.create("MDR_010");			
		}
		
		Wrapper<MdReport> wrapper2 = new EntityWrapper<MdReport>();
		wrapper2.eq("id", id).eq("del_flag", false);
		List<MdReport> re = mdReportMapper.selectList(wrapper2);
		if(re!=null&&re.size()>0){
			MdReport r = re.get(0);
			Integer status = r.getStatus();
			//质控上报单据已提交，无法作废
			if(2==status)throw ExceptionFactory.create("MDR_017");
			//质控上报单据已审核，无法作废
			if(4==status || 3==status)throw ExceptionFactory.create("MDR_018");
		}

		Date now = new Date();
		mdReportDb.setUpdateBy(userId);
		mdReportDb.setUpdateTime(now);
		mdReportDb.setDelFlag(true);
		mdReportMapper.updateById(mdReportDb);
		Wrapper<MdReportAssets> wrapper1 = new EntityWrapper<MdReportAssets>();
		wrapper1.eq("md_report_id", id);
		List<MdReportAssets> reportAssetsList = mdReportAssetsMapper.selectList(wrapper1);
		if(reportAssetsList!=null&&reportAssetsList.size()>0){
			for (MdReportAssets mdReportAssets : reportAssetsList) {
				mdReportAssets.setDelFlag(true);
			}
			mdReportAssetsService.updateBatchById(reportAssetsList);
		}
	}

	@Override
	public Integer count(Long tenantId) {
		Integer count=0;
		count = mdReportMapper.count(tenantId);
		return count;
	}	
	
}
