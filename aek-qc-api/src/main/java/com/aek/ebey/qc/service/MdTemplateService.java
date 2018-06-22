package com.aek.ebey.qc.service;

import java.util.List;

import com.aek.common.core.base.BaseService;
import com.aek.ebey.qc.model.MdTemplate;
import com.aek.ebey.qc.model.vo.MdTemplateDetailVO;
import com.aek.ebey.qc.query.MdTemplateQuery;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Honghui
 * @since 2018-05-16
 */
public interface MdTemplateService extends BaseService<MdTemplate> {

    /**
     * 新增质控模板
     * @param mdTemplate
     */
    void save(MdTemplate mdTemplate);
    
    /**
     * 编辑质控模板
     * @param mdTemplate
     */
    void edit(MdTemplate mdTemplate); 
    
    /**
     * 分页查询质控模板
     * @param query
     * @return
     */
    Page<MdTemplate> getMdTemplatePage(MdTemplateQuery query);
    
    /**
     * 查询质控模板列表（质控填报时使用）
     * @param query
     * @return
     */
    List<MdTemplate> getMdTemplateList();
    
    /**
     * 逻辑删除模板
     * @param id
     */
    void delete(Long id);
    
    /**
     * 根据模板ID停用/启用保养模版
     * @param id
     */
    void enableOrDisable(Long id,Boolean enable);
    
    /**
     * 发布质控模板
     * @param id
     */
    void release(Long id);
    
    /**
     * 获取质控模板详情
     * @param id
     * @return
     */
    MdTemplateDetailVO getMdTemplateDetail(Long id);
}
