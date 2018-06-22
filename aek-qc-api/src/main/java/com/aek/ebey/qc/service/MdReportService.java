package com.aek.ebey.qc.service;

import com.aek.ebey.qc.model.MdReport;
import com.aek.ebey.qc.model.MdReportTemplateItemResult;
import com.aek.ebey.qc.model.bo.MdAssetsBO;
import com.aek.ebey.qc.model.bo.MdMaximumAllowableErrorDetailBO;
import com.aek.ebey.qc.model.bo.MdReportBO;
import com.aek.ebey.qc.model.vo.MdAssetsVO;
import com.aek.ebey.qc.model.vo.MdReportDetailVO;
import com.aek.ebey.qc.model.vo.MdReportPageVO;
import com.aek.ebey.qc.query.MdReportQuery;
import com.aek.ebey.qc.query.MdVerifyReportQuery;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

import com.aek.common.core.base.BaseService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Honghui
 * @since 2018-05-16
 */
public interface MdReportService extends BaseService<MdReport> {
	
	/**
	 * 质控填报，暂存、待审核设备id集合
	 * @return
	 */
	List<Long> getMdAssetsExist();
	
	/**
	 * 新增填报暂存
	 * @param mdReport
	 * @param maximumAllowableErrorList
	 * @param mdAsset
	 * @param mdItemResultList
	 */
	Long tempSave(
			MdReportBO mdReportBO,
			MdAssetsBO mdAsset,
			List<MdMaximumAllowableErrorDetailBO> maximumAllowableErrorList,		
			List<MdReportTemplateItemResult> mdItemResultList
			);
	
	/**
	 * 新增填报保存
	 * @param mdReportBO
	 * @param mdAsset
	 * @param maximumAllowableErrorList
	 * @param mdItemResultList
	 */
	void save(MdReportBO mdReportBO,
			MdAssetsBO mdAsset,
			List<MdMaximumAllowableErrorDetailBO> maximumAllowableErrorList,		
			List<MdReportTemplateItemResult> mdItemResultList
			);
	
	/**
	 * 质控检测单再次编辑
	 * @param mdReportBO
	 * @param mdAsset
	 * @param maximumAllowableErrorList
	 * @param mdItemResultList
	 */
	void againSave(MdReportBO mdReportBO,
			MdAssetsBO mdAsset,
			List<MdMaximumAllowableErrorDetailBO> maximumAllowableErrorList,		
			List<MdReportTemplateItemResult> mdItemResultList
			);
	
	/**
	 * 质控检测单再次编辑暂存
	 * @param mdReportBO
	 * @param mdAsset
	 * @param maximumAllowableErrorList
	 * @param mdItemResultList
	 */
	Long againTempSave(MdReportBO mdReportBO,
			MdAssetsBO mdAsset,
			List<MdMaximumAllowableErrorDetailBO> maximumAllowableErrorList,		
			List<MdReportTemplateItemResult> mdItemResultList
			);
		
	
	/**
	 * 质控填报列表查询
	 * @param query
	 * @return
	 */
	Page<MdReportPageVO> getApplyMdReportPage(MdReportQuery query);
	
	/**
	 * 质控审核列表查询
	 * @param query
	 * @return
	 */
	Page<MdReportPageVO> getVerifyMdReportPage(MdVerifyReportQuery query);
	
	/**
	 * 质控档案列表查询
	 * @param query
	 * @return
	 */
	Page<MdReportPageVO> getArchiveMdReportPage(MdReportQuery query);
	
	/**
	 * 质控检测单明细查询
	 * @param id
	 * @return
	 */
	MdReportDetailVO getMdReportDetail(Long id);
	
	/**
	 * 质控检测单撤回
	 * @param id
	 */
	void recallMdReport(Long id);
	
	/**
	 * 质控检测单作废
	 * @param id
	 */
	void deleteMdReport(Long id);
	
	/**
	 * 统计某人审核质控单数目
	 * @param tenantId
	 * @return
	 */
	Integer count(Long tenantId);
	
	/**
	 * 质控检测单审核
	 * @param id
	 * @param verifyRemark
	 * @param verifyResult
	 */
	void verifyMdReport(Long id,String verifyRemark,Integer verifyResult);
}
