package com.aek.ebey.qc.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aek.common.core.base.BaseMapper;
import com.aek.common.core.serurity.model.AuthUser;
import com.aek.ebey.qc.model.QcTemplate;
import com.aek.ebey.qc.query.QcTemplateQuery;
import com.aek.ebey.qc.query.Query;
import com.aek.ebey.qc.request.QcTemplateResponse;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author aek
 * @since 2017-11-02
 */
public interface QcTemplateMapper extends BaseMapper<QcTemplate> {

	List<QcTemplateResponse> search(Page<QcTemplateResponse> page,@Param("q") QcTemplateQuery query,@Param("user") AuthUser authUser);

	List<QcTemplateResponse> changeSearch(@Param("q")String keyword,@Param("user") AuthUser authUser);

	List<QcTemplate> selectList2(@Param("q") Query query);
	

}