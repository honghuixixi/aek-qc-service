package com.aek.ebey.qc.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aek.common.core.BeanMapper;
import com.aek.common.core.base.BaseServiceImpl;
import com.aek.common.core.exception.ExceptionFactory;
import com.aek.common.core.serurity.WebSecurityUtils;
import com.aek.common.core.serurity.model.AuthUser;
import com.aek.ebey.qc.enums.MdInputTypeEnum;
import com.aek.ebey.qc.enums.MdTypeEnum;
import com.aek.ebey.qc.mapper.MdTemplateItemMapper;
import com.aek.ebey.qc.mapper.MdTemplateItemOptionMapper;
import com.aek.ebey.qc.model.MdTemplate;
import com.aek.ebey.qc.model.MdTemplateItem;
import com.aek.ebey.qc.model.MdTemplateItemOption;
import com.aek.ebey.qc.model.vo.MdTemplateChildItemVO;
import com.aek.ebey.qc.model.vo.MdTemplateItemOptionVO;
import com.aek.ebey.qc.model.vo.MdTemplateItemVO;
import com.aek.ebey.qc.request.MdTemplateChildItemRequest;
import com.aek.ebey.qc.request.MdTemplateItemOptionRequest;
import com.aek.ebey.qc.request.MdTemplateItemRequest;
import com.aek.ebey.qc.service.MdTemplateItemService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

/**
 * <p>
 * 质控检测系统模板子项目表 服务实现类
 * </p>
 *
 * @author Honghui
 * @since 2018-05-16
 */
@Service
@Transactional
public class MdTemplateItemServiceImpl extends BaseServiceImpl<MdTemplateItemMapper, MdTemplateItem> implements MdTemplateItemService {

    @Autowired
    private MdTemplateItemMapper mdTemplateItemMapper;
    @Autowired
    private MdTemplateItemOptionMapper mdTemplateItemOptionMapper;
    
    @Override
    public void save(MdTemplateItemRequest mdTemplateItemRequest) {
        AuthUser authUser = WebSecurityUtils.getCurrentUser();
        // 项目名称重复
        String itemName = mdTemplateItemRequest.getName();
        Wrapper<MdTemplateItem> mdTemplateItemWrapper = new EntityWrapper<MdTemplateItem>();
        mdTemplateItemWrapper.eq("name", itemName);
        mdTemplateItemWrapper.eq("template_id", mdTemplateItemRequest.getTemplateId());
        mdTemplateItemWrapper.isNull("parent_id");
        List<MdTemplateItem> mdTemplateItemList = mdTemplateItemMapper.selectList(mdTemplateItemWrapper);
        if(null != mdTemplateItemList && mdTemplateItemList.size() > 0) {
            throw ExceptionFactory.create("MD_016");
        }
        //项目文本类型，子项目必填
        List<MdTemplateChildItemRequest> childItems = mdTemplateItemRequest.getChildItems();
        if (MdInputTypeEnum.TEXT.getNumber().equals(mdTemplateItemRequest.getInputType())) {
            if (null == childItems || (null != childItems && childItems.size() == 0)){
                throw ExceptionFactory.create("MD_012");
            }
            //子项目中是否存在相同的名称
            if (isEqualItemName(childItems)) {
                throw ExceptionFactory.create("MD_014");
            }
        }
        //项目选择类型，选项必填
        List<MdTemplateItemOptionRequest> childItemOptions = mdTemplateItemRequest.getChildItemOptions();
        if (MdInputTypeEnum.SELECT.getNumber().equals(mdTemplateItemRequest.getInputType())) {
            if (null == childItemOptions || (null != childItemOptions && childItemOptions.size() == 0)) {
                throw ExceptionFactory.create("MD_013");
            }
            //选项中是否存在相同的名称
            if (isEqualItemOptionName(childItemOptions)) {
                throw ExceptionFactory.create("MD_015");
            }
        }
        // 父项目存储
        MdTemplateItem mdTemplateItem = BeanMapper.map(mdTemplateItemRequest, MdTemplateItem.class);
        mdTemplateItemWrapper = new EntityWrapper<MdTemplateItem>();
        mdTemplateItemWrapper.eq("template_id", mdTemplateItemRequest.getTemplateId());
        mdTemplateItemWrapper.isNull("parent_id");
        mdTemplateItemList = mdTemplateItemMapper.selectList(mdTemplateItemWrapper);
        mdTemplateItem.setSort(mdTemplateItemList == null ? 1 : mdTemplateItemList.size() + 1);
        mdTemplateItem.setCreateBy(authUser.getId());
        mdTemplateItem.setCreateTime(new Date());
        mdTemplateItem.setUpdateBy(authUser.getId());
        mdTemplateItem.setUpdateTime(new Date());
        mdTemplateItemMapper.insert(mdTemplateItem);
        // 子项目存储 
        if (null != childItems && childItems.size() > 0) {
            for (int i=0 ; i < childItems.size(); i++) {
                MdTemplateItem mdTemplateChildItem = BeanMapper.map(childItems.get(i), MdTemplateItem.class);
                mdTemplateChildItem.setParentId(mdTemplateItem.getId());
                mdTemplateChildItem.setTemplateId(mdTemplateItemRequest.getTemplateId());
                mdTemplateChildItem.setCreateBy(authUser.getId());
                mdTemplateChildItem.setCreateTime(new Date());
                mdTemplateChildItem.setUpdateBy(authUser.getId());
                mdTemplateChildItem.setUpdateTime(new Date());
                mdTemplateChildItem.setSort(i+1);
                mdTemplateChildItem.setInputType(mdTemplateItem.getInputType());
                mdTemplateChildItem.setColumns(mdTemplateItem.getColumns());
                mdTemplateChildItem.setType(MdTypeEnum.SUBITEM.getNumber());
                mdTemplateItemMapper.insert(mdTemplateChildItem);
            }
        }
        // 子项目备注存储
        List<MdTemplateChildItemRequest> childItemRemarks = mdTemplateItemRequest.getChildItemRemarks();
        if (null != childItemRemarks && childItemRemarks.size() > 0) {
            for (int i=0 ; i < childItemRemarks.size(); i++) {
                MdTemplateItem mdTemplateItemRemark = BeanMapper.map(childItemRemarks.get(i), MdTemplateItem.class);
                mdTemplateItemRemark.setParentId(mdTemplateItem.getId());
                mdTemplateItemRemark.setTemplateId(mdTemplateItemRequest.getTemplateId());
                mdTemplateItemRemark.setCreateBy(authUser.getId());
                mdTemplateItemRemark.setCreateTime(new Date());
                mdTemplateItemRemark.setUpdateBy(authUser.getId());
                mdTemplateItemRemark.setUpdateTime(new Date());
                mdTemplateItemRemark.setSort(childItems.size() + (i+1));
                mdTemplateItemRemark.setInputType(mdTemplateItem.getInputType());
                mdTemplateItemRemark.setColumns(mdTemplateItem.getColumns());
                mdTemplateItemRemark.setType(MdTypeEnum.SUBITEM_REMARKS.getNumber());
                mdTemplateItemMapper.insert(mdTemplateItemRemark);
            }
        }
        //项目选项存储
        if (null != childItemOptions && childItemOptions.size() > 0) {
            for (int i=0 ; i < childItemOptions.size(); i++) {
                MdTemplateItemOption mdTemplateItemOption = BeanMapper.map(childItemOptions.get(i), MdTemplateItemOption.class);
                mdTemplateItemOption.setItemId(mdTemplateItem.getId());
                mdTemplateItemOption.setSort(i+1);
                mdTemplateItemOption.setCreateTime(new Date());
                mdTemplateItemOption.setUpdateTime(new Date());
                mdTemplateItemOptionMapper.insert(mdTemplateItemOption);
            }
        }
    }

    @Override
    public void edit(MdTemplateItemRequest mdTemplateItemRequest) {
        AuthUser authUser = WebSecurityUtils.getCurrentUser();
        //验证项目名称是否重复
        MdTemplateItem mdTemplateItemDb = mdTemplateItemMapper.selectById(mdTemplateItemRequest.getId());
        String itemName = mdTemplateItemRequest.getName();
        if (!itemName.equals(mdTemplateItemDb.getName())) {
            Wrapper<MdTemplateItem> mdTemplateItemWrapper = new EntityWrapper<MdTemplateItem>();
            mdTemplateItemWrapper.eq("name", itemName);
            mdTemplateItemWrapper.eq("template_id", mdTemplateItemRequest.getTemplateId());
            mdTemplateItemWrapper.isNull("parent_id");
            List<MdTemplateItem> mdTemplateItemList = mdTemplateItemMapper.selectList(mdTemplateItemWrapper);
            if(null != mdTemplateItemList && mdTemplateItemList.size() > 0) {
                throw ExceptionFactory.create("MD_016");
            }
        }
        //项目文本类型，子项目必填
        List<MdTemplateChildItemRequest> childItems = mdTemplateItemRequest.getChildItems();
        if (MdInputTypeEnum.TEXT.getNumber().equals(mdTemplateItemRequest.getInputType())) {
            if (null == childItems || (null != childItems && childItems.size() == 0)){
                throw ExceptionFactory.create("MD_012");
            }
            //子项目中是否存在相同的名称
            if (isEqualItemName(childItems)) {
                throw ExceptionFactory.create("MD_014");
            }
        }
        //项目选择类型，选项必填
        List<MdTemplateItemOptionRequest> childItemOptions = mdTemplateItemRequest.getChildItemOptions();
        if (MdInputTypeEnum.SELECT.getNumber().equals(mdTemplateItemRequest.getInputType())) {
            if (null == childItemOptions || (null != childItemOptions && childItemOptions.size() == 0)) {
                throw ExceptionFactory.create("MD_013");
            }
            //选项中是否存在相同的名称
            if (isEqualItemOptionName(childItemOptions)) {
                throw ExceptionFactory.create("MD_015");
            }
        }
        //父项目更新
        MdTemplateItem mdTemplateItem = BeanMapper.map(mdTemplateItemRequest, MdTemplateItem.class);
        mdTemplateItem.setUpdateBy(authUser.getId());
        mdTemplateItem.setUpdateTime(new Date());
        mdTemplateItemMapper.updateById(mdTemplateItem);
        
        // 子项目先删，后存储 
        Wrapper<MdTemplateItem> mdTemplateChildItemWrapper = new EntityWrapper<MdTemplateItem>();
        mdTemplateChildItemWrapper.eq("template_id", mdTemplateItemRequest.getTemplateId());
        mdTemplateChildItemWrapper.eq("parent_id", mdTemplateItem.getId());
        mdTemplateItemMapper.delete(mdTemplateChildItemWrapper);
        if (null != childItems && childItems.size() > 0) {
            for (int i=0 ; i < childItems.size(); i++) {
                MdTemplateItem mdTemplateChildItem = BeanMapper.map(childItems.get(i), MdTemplateItem.class);
                mdTemplateChildItem.setParentId(mdTemplateItem.getId());
                mdTemplateChildItem.setTemplateId(mdTemplateItemRequest.getTemplateId());
                mdTemplateChildItem.setCreateBy(authUser.getId());
                mdTemplateChildItem.setCreateTime(new Date());
                mdTemplateChildItem.setUpdateBy(authUser.getId());
                mdTemplateChildItem.setUpdateTime(new Date());
                mdTemplateChildItem.setSort(i+1);
                mdTemplateChildItem.setInputType(mdTemplateItem.getInputType());
                mdTemplateChildItem.setColumns(mdTemplateItem.getColumns());
                mdTemplateChildItem.setType(MdTypeEnum.SUBITEM.getNumber());
                mdTemplateItemMapper.insert(mdTemplateChildItem);
            }
        }
        // 子项目备注先删，后存储
        List<MdTemplateChildItemRequest> childItemRemarks = mdTemplateItemRequest.getChildItemRemarks();
        if (null != childItemRemarks && childItemRemarks.size() > 0) {
            for (int i=0 ; i < childItemRemarks.size(); i++) {
                MdTemplateItem mdTemplateItemRemark = BeanMapper.map(childItemRemarks.get(i), MdTemplateItem.class);
                mdTemplateItemRemark.setParentId(mdTemplateItem.getId());
                mdTemplateItemRemark.setTemplateId(mdTemplateItemRequest.getTemplateId());
                mdTemplateItemRemark.setCreateBy(authUser.getId());
                mdTemplateItemRemark.setCreateTime(new Date());
                mdTemplateItemRemark.setUpdateBy(authUser.getId());
                mdTemplateItemRemark.setUpdateTime(new Date());
                mdTemplateItemRemark.setSort(childItems.size() + (i+1));
                mdTemplateItemRemark.setInputType(mdTemplateItem.getInputType());
                mdTemplateItemRemark.setColumns(mdTemplateItem.getColumns());
                mdTemplateItemRemark.setType(MdTypeEnum.SUBITEM_REMARKS.getNumber());
                mdTemplateItemMapper.insert(mdTemplateItemRemark);
            }
        }
        //项目选项先删、后存储
        Wrapper<MdTemplateItemOption> mdTemplateItemOptionWrapper = new EntityWrapper<MdTemplateItemOption>();
        mdTemplateItemOptionWrapper.eq("item_id", mdTemplateItem.getId());
        mdTemplateItemOptionMapper.delete(mdTemplateItemOptionWrapper);
        if (null != childItemOptions && childItemOptions.size() > 0) {
            for (int i=0 ; i < childItemOptions.size(); i++) {
                MdTemplateItemOption mdTemplateItemOption = BeanMapper.map(childItemOptions.get(i), MdTemplateItemOption.class);
                mdTemplateItemOption.setItemId(mdTemplateItem.getId());
                mdTemplateItemOption.setSort(i+1);
                mdTemplateItemOption.setCreateTime(new Date());
                mdTemplateItemOption.setUpdateTime(new Date());
                mdTemplateItemOptionMapper.insert(mdTemplateItemOption);
            }
        }
    }
    
    @Override
    public void delete(Long id) {
        MdTemplateItem mdTemplateItem = mdTemplateItemMapper.selectById(id);
        // 模板项目不存在
        if (null == mdTemplateItem) throw ExceptionFactory.create("MD_017");
        // TODO 当前用户是否有质控模板权限
        AuthUser authUser = WebSecurityUtils.getCurrentUser();
        
        //删除子项目和子项目备注
        Wrapper<MdTemplateItem> mdTemplateChildItemWrapper = new EntityWrapper<MdTemplateItem>();
        mdTemplateChildItemWrapper.eq("parent_id", mdTemplateItem.getId());
        mdTemplateItemMapper.delete(mdTemplateChildItemWrapper);
        //删除选项
        Wrapper<MdTemplateItemOption> mdTemplateItemOptionWrapper = new EntityWrapper<MdTemplateItemOption>();
        mdTemplateItemOptionWrapper.eq("item_id", mdTemplateItem.getId());
        mdTemplateItemOptionMapper.delete(mdTemplateItemOptionWrapper);
        //删除项目
        mdTemplateItemMapper.deleteById(id);
    }

    @Override
    public MdTemplateItemVO getMdTemplateItemDetail(Long id) {
        MdTemplateItem mdTemplateItem = mdTemplateItemMapper.selectById(id);
        MdTemplateItemVO mdTemplateItemVO = BeanMapper.map(mdTemplateItem, MdTemplateItemVO.class);
        if (MdInputTypeEnum.TEXT.getNumber().equals(mdTemplateItemVO.getInputType())) {
            // 获取子文本项目和子项目备注
            Wrapper<MdTemplateItem> mdTemplateItemWrapper = new EntityWrapper<MdTemplateItem>();
            mdTemplateItemWrapper.eq("parent_id", mdTemplateItemVO.getId());
            mdTemplateItemWrapper.orderBy("sort",true);
            List<MdTemplateItem> mdTemplateChildItemList = mdTemplateItemMapper.selectList(mdTemplateItemWrapper);
            List<MdTemplateChildItemVO> mdTemplateChildItemVOList = BeanMapper.mapList(mdTemplateChildItemList, MdTemplateChildItemVO.class);
            if (null != mdTemplateChildItemVOList && mdTemplateChildItemVOList.size() > 0) {
                mdTemplateItemVO.setChildItems(mdTemplateChildItemVOList);
            }
        }
        if (MdInputTypeEnum.SELECT.getNumber().equals(mdTemplateItemVO.getInputType())) {
            // 获取项目选项
            Wrapper<MdTemplateItemOption> mdTemplateItemOptionWrapper = new EntityWrapper<MdTemplateItemOption>();
            mdTemplateItemOptionWrapper.eq("item_id", mdTemplateItemVO.getId());
            mdTemplateItemOptionWrapper.orderBy("sort",true);
            List<MdTemplateItemOption> mdTemplateItemOptionList = mdTemplateItemOptionMapper.selectList(mdTemplateItemOptionWrapper);
            List<MdTemplateItemOptionVO> mdTemplateItemOptionVOList = BeanMapper.mapList(mdTemplateItemOptionList, MdTemplateItemOptionVO.class);
            if (null != mdTemplateItemOptionVOList && mdTemplateItemOptionVOList.size() > 0) {
                mdTemplateItemVO.setChildItemOptions(mdTemplateItemOptionVOList);
            }
        }
        return mdTemplateItemVO;
    }

    /**
     * 判断集合中是否存在相同项目名称
     * @param list
     * @return
     */
    public static boolean isEqualItemName(List<MdTemplateChildItemRequest> list) {
        Set<String> itemNames = new HashSet<String>();
        for (MdTemplateChildItemRequest mdTemplateChildItemRequest : list) {
            itemNames.add(mdTemplateChildItemRequest.getName()); 
        }
        if (null != list) {
            if (itemNames.size() < list.size()) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 判断集合中是否存在相同项目名称
     * @param list
     * @return
     */
    public static boolean isEqualItemOptionName(List<MdTemplateItemOptionRequest> list) {
        Set<String> itemOptionNames = new HashSet<String>();
        for (MdTemplateItemOptionRequest mdTemplateItemOptionRequest : list) {
            itemOptionNames.add(mdTemplateItemOptionRequest.getName()); 
        }
        if (null != list) {
            if (itemOptionNames.size() < list.size()) {
                return true;
            }
        }
        return false;
    }
}
