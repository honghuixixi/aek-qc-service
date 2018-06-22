package com.aek.ebey.qc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aek.common.core.base.BaseMapper;
import com.aek.ebey.qc.model.MdTemplate;
import com.aek.ebey.qc.query.MdTemplateQuery;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author Honghui
 * @since 2018-05-16
 */
public interface MdTemplateMapper extends BaseMapper<MdTemplate> {

    
    /**
     * 模版分页查询
     * @param page
     * @param query
     * @return
     */
    List<MdTemplate> getMdTemplateByPage(@Param("page") Page<MdTemplate> page, @Param("query") MdTemplateQuery query);
    
    /**
     * 模板列表查询（质控填报时使用）
     * @return
     */
    List<MdTemplate> getMdTemplateList();

}