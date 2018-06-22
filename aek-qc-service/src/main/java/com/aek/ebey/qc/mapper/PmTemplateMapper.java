package com.aek.ebey.qc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aek.common.core.base.BaseMapper;
import com.aek.common.core.serurity.model.AuthUser;
import com.aek.ebey.qc.model.PmTemplate;
import com.aek.ebey.qc.query.PmTemplateQuery;
import com.aek.ebey.qc.query.Query;
import com.aek.ebey.qc.request.PmTemplateResponse;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author aek
 * @since 2017-11-27
 */
public interface PmTemplateMapper extends BaseMapper<PmTemplate> {

	List<PmTemplateResponse> search(Page<PmTemplateResponse> pageQcTemplate,@Param("q")  PmTemplateQuery query,@Param("user") AuthUser authUser);

	List<PmTemplateResponse> changeSearch(@Param("q") String keyword, @Param("user") AuthUser authUser);

	List<PmTemplate> selectList2(@Param("q") Query query);

}