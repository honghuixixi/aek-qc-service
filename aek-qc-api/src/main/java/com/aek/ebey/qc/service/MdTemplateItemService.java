package com.aek.ebey.qc.service;

import com.aek.common.core.base.BaseService;
import com.aek.ebey.qc.model.MdTemplateItem;
import com.aek.ebey.qc.model.vo.MdTemplateItemVO;
import com.aek.ebey.qc.request.MdTemplateItemRequest;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Honghui
 * @since 2018-05-16
 */
public interface MdTemplateItemService extends BaseService<MdTemplateItem> {
	
    /**
     * 新增质控模板项目
     * @param mdTemplate
     */
    void save(MdTemplateItemRequest mdTemplateItemRequest);
    
    /**
     * 编辑质控模板项目
     * @param mdTemplate
     */
    void edit(MdTemplateItemRequest mdTemplateItemRequest); 
    
    /**
     * 删除质控模板项目
     * @param id
     */
    void delete(Long id);
    
    /**
     * 模板项目详情
     * @param id
     * @return
     */
    MdTemplateItemVO getMdTemplateItemDetail(Long id);
    
}
