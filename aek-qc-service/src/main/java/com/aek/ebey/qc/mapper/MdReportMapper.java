package com.aek.ebey.qc.mapper;

import com.aek.ebey.qc.model.MdReport;
import com.aek.ebey.qc.model.bo.MdReportBO;
import com.aek.ebey.qc.model.vo.MdReportPageVO;
import com.aek.ebey.qc.query.MdReportQuery;
import com.aek.ebey.qc.query.MdVerifyReportQuery;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aek.common.core.base.BaseMapper;
import com.aek.common.core.serurity.model.AuthUser;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author Honghui
 * @since 2018-05-16
 */
public interface MdReportMapper extends BaseMapper<MdReport> {

	List<Long> getMdAssetsExist(@Param("tenantId")Long tenantId);
	
	public int updateReport(@Param("mdReport")MdReportBO mdReport);
	
	/**
	 * 查询模版子项目id集合
	 * @param mdReportId
	 * @return
	 */
	List<Long> getReportTemplateItemIds(@Param("mdReportId")Long mdReportId);
	
	/**
	 * 质控填报列表查询
	 * @param page
	 * @param query
	 * @param authUser
	 * @return
	 */
	List<MdReportPageVO> getApplyMdReportPage(Page<MdReportPageVO> page,@Param("q")MdReportQuery query,
			@Param("user") AuthUser authUser);
	/**
	 * 质控审核列表查询
	 * @param page
	 * @param query
	 * @param authUser
	 * @return
	 */
	List<MdReportPageVO> getVerifyMdReportPage(Page<MdReportPageVO> page,@Param("q")MdVerifyReportQuery query,
			@Param("user") AuthUser authUser);
	
	/**
	 * 质控档案列表查询
	 * @param page
	 * @param query
	 * @param authUser
	 * @return
	 */
	List<MdReportPageVO> getArchiveMdReportPage(Page<MdReportPageVO> page,@Param("q")MdReportQuery query,
			@Param("user") AuthUser authUser);
	
	/**
	 * 统计某人审核质控单数目
	 * @param tenantId
	 * @return
	 */
	Integer count(@Param("tenantId") Long tenantId);
}