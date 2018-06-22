package com.aek.ebey.qc.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aek.common.core.BeanMapper;
import com.aek.common.core.Result;
import com.aek.common.core.base.BaseServiceImpl;
import com.aek.common.core.exception.ExceptionFactory;
import com.aek.common.core.serurity.WebSecurityUtils;
import com.aek.common.core.serurity.model.AuthUser;
import com.aek.ebey.qc.enums.MdInputTypeEnum;
import com.aek.ebey.qc.mapper.MdTemplateItemMapper;
import com.aek.ebey.qc.mapper.MdTemplateItemOptionMapper;
import com.aek.ebey.qc.mapper.MdTemplateMapper;
import com.aek.ebey.qc.model.MdTemplate;
import com.aek.ebey.qc.model.MdTemplateItem;
import com.aek.ebey.qc.model.MdTemplateItemOption;
import com.aek.ebey.qc.model.vo.MdTemplateChildItemVO;
import com.aek.ebey.qc.model.vo.MdTemplateDetailVO;
import com.aek.ebey.qc.model.vo.MdTemplateItemOptionVO;
import com.aek.ebey.qc.model.vo.MdTemplateItemVO;
import com.aek.ebey.qc.query.MdTemplateQuery;
import com.aek.ebey.qc.service.MdTemplateService;
import com.aek.ebey.qc.service.feign.DeptClientService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 质控检测系统模板表 服务实现类
 * </p>
 *
 * @author Honghui
 * @since 2018-05-16
 */
@Service
@Transactional
public class MdTemplateServiceImpl extends BaseServiceImpl<MdTemplateMapper, MdTemplate> implements MdTemplateService {

	@Autowired
	private MdTemplateMapper mdTemplateMapper;
	@Autowired
	private MdTemplateItemMapper mdTemplateItemMapper;
	@Autowired
	private MdTemplateItemOptionMapper mdTemplateItemOptionMapper;
	@Autowired
	private DeptClientService deptClientService;
	
    @Override
    public void save(MdTemplate mdTemplate) {
        String name  = mdTemplate.getName();
        if (StringUtils.isBlank(name)) {
            // 模板名称不能为空
            throw ExceptionFactory.create("MD_001");
        }
        Wrapper<MdTemplate> mdTemplateWrapper = new EntityWrapper<MdTemplate>();
        mdTemplateWrapper.eq("name", name);
        mdTemplateWrapper.eq("del_flag", false);
        List<MdTemplate> mdTemplateList = mdTemplateMapper.selectList(mdTemplateWrapper);
        if (null != mdTemplateList && mdTemplateList.size() > 0) {
            // 模板名称已存在
            throw ExceptionFactory.create("MD_009");
        }
        AuthUser authUser = WebSecurityUtils.getCurrentUser();
        mdTemplate.setEnable(true);
        mdTemplate.setReleaseFlag(false);
        mdTemplate.setCreateBy(authUser.getId());
        mdTemplate.setUpdateBy(authUser.getId());
        mdTemplate.setCreateTime(new Date());
        mdTemplate.setUpdateTime(new Date());
        mdTemplate.setDelFlag(false);
        mdTemplateMapper.insert(mdTemplate); 
    }

    @Override
    public void edit(MdTemplate mdTemplate) {
        MdTemplate mdTemplateDb = mdTemplateMapper.selectById(mdTemplate.getId());
        if (null != mdTemplateDb && !mdTemplate.getName().equals(mdTemplateDb.getName())) {
            Wrapper<MdTemplate> mdTemplateWrapper = new EntityWrapper<MdTemplate>();
            mdTemplateWrapper.eq("name", mdTemplate.getName());
            mdTemplateWrapper.eq("del_flag", false);
            List<MdTemplate> mdTemplateList = mdTemplateMapper.selectList(mdTemplateWrapper);
            if (null != mdTemplateList && mdTemplateList.size() > 0) {
                // 模板名称已存在
                throw ExceptionFactory.create("MD_009");
            }
        }
        mdTemplateDb.setName(mdTemplate.getName());
        mdTemplateDb.setRemarks(mdTemplate.getRemarks());
        AuthUser authUser = WebSecurityUtils.getCurrentUser();
        mdTemplateDb.setUpdateBy(authUser.getId());
        mdTemplateDb.setUpdateTime(new Date());
        mdTemplateMapper.updateAllColumnById(mdTemplateDb);
    }

    @Override
    public Page<MdTemplate> getMdTemplatePage(MdTemplateQuery query) {
        Page<MdTemplate> page = query.getPage();
        String keyword = StringUtils.trimToNull(query.getKeyword());
        if(StringUtils.isNotBlank(keyword)){
            if (keyword.startsWith("%") || keyword.startsWith("[") || keyword.startsWith("[]") || keyword.startsWith("_")) {
                query.setKeyword('\\'+keyword);
            }
        }
        List<MdTemplate> list = mdTemplateMapper.getMdTemplateByPage(page, query);
        page.setRecords(list);
        return page;
    }

    @Override
    public List<MdTemplate> getMdTemplateList() {
        return mdTemplateMapper.getMdTemplateList();
    }

    @Override
    public void delete(Long id) {
        MdTemplate mdTemplate = mdTemplateMapper.selectById(id);
        // 模板不存在
        if (null == mdTemplate) throw ExceptionFactory.create("MD_010");
        // TODO 当前用户是否有质控模板权限
        AuthUser authUser = WebSecurityUtils.getCurrentUser();
        Result<Boolean> result = deptClientService.checkUserPermission(authUser.getId(), "MD_TEMPLATE_MANAGE", WebSecurityUtils.getCurrentToken());
        // 网络故障，请稍后再试
        if (null == result) throw ExceptionFactory.create("MD_018"); 
        // 无权限
        if (!result.getData()) throw ExceptionFactory.create("403"); 
        mdTemplate.setDelFlag(true);
        mdTemplate.setUpdateBy(authUser.getId());
        mdTemplate.setUpdateTime(new Date());
        mdTemplateMapper.updateById(mdTemplate);
    }

    @Override
    public void enableOrDisable(Long id, Boolean enable) {
        MdTemplate mdTemplate = mdTemplateMapper.selectById(id);
        // 模板不存在
        if (null == mdTemplate) throw ExceptionFactory.create("MD_010");
        // TODO 当前用户是否有质控模板权限
        AuthUser authUser = WebSecurityUtils.getCurrentUser();
        Result<Boolean> result = deptClientService.checkUserPermission(authUser.getId(), "MD_TEMPLATE_MANAGE", WebSecurityUtils.getCurrentToken());
        // 网络故障，请稍后再试
        if (null == result) throw ExceptionFactory.create("MD_018"); 
        // 无权限
        if (!result.getData()) throw ExceptionFactory.create("403"); 
        mdTemplate.setEnable(enable);
        mdTemplate.setUpdateBy(authUser.getId());
        mdTemplate.setUpdateTime(new Date());
        mdTemplateMapper.updateById(mdTemplate);
    }
    
    @Override
    public void release(Long id) {
        MdTemplate mdTemplate = mdTemplateMapper.selectById(id);
        // 模板不存在
        if (null == mdTemplate) throw ExceptionFactory.create("MD_010");
        // 模板已发布
        if (mdTemplate.getReleaseFlag()) {
            throw ExceptionFactory.create("MD_011");
        }
        // 模板是否配置了项目
        Wrapper<MdTemplateItem> mdTemplateItemWrapper = new EntityWrapper();
        mdTemplateItemWrapper.eq("template_id",mdTemplate.getId());
        List<MdTemplateItem> mdTemplateItemList = mdTemplateItemMapper.selectList(mdTemplateItemWrapper);
        if (null == mdTemplateItemList || (null != mdTemplateItemList && mdTemplateItemList.size() == 0)) {
            throw ExceptionFactory.create("MD_019");
        }
        // TODO 当前用户是否有质控模板权限
        AuthUser authUser = WebSecurityUtils.getCurrentUser();
        Result<Boolean> result = deptClientService.checkUserPermission(authUser.getId(), "MD_TEMPLATE_MANAGE", WebSecurityUtils.getCurrentToken());
        // 网络故障，请稍后再试
        if (null == result) throw ExceptionFactory.create("MD_018"); 
        // 无权限
        if (!result.getData()) throw ExceptionFactory.create("403"); 
        mdTemplate.setReleaseFlag(true);
        mdTemplate.setUpdateBy(authUser.getId());
        mdTemplate.setUpdateTime(new Date());
        mdTemplateMapper.updateById(mdTemplate);
    }

    @Override
    public MdTemplateDetailVO getMdTemplateDetail(Long id) {
        MdTemplateDetailVO mdTemplateDetailVO = new MdTemplateDetailVO();
        MdTemplate mdTemplate = mdTemplateMapper.selectById(id);
        // 模板不存在
        if (null == mdTemplate) throw ExceptionFactory.create("MD_010");
        // 模板信息拷贝到返回VO类
        BeanMapper.copy(mdTemplate, mdTemplateDetailVO);
        // 获取一级项目列表
        Wrapper<MdTemplateItem> mdTemplateItemWrapper = new EntityWrapper<MdTemplateItem>();
        mdTemplateItemWrapper.eq("template_id", id);
        mdTemplateItemWrapper.isNull("parent_id");
        mdTemplateItemWrapper.orderBy("sort", true);
        List<MdTemplateItem> mdTemplateParentItemList = mdTemplateItemMapper.selectList(mdTemplateItemWrapper);
        List<MdTemplateItemVO> mdTemplateItemVOList = BeanMapper.mapList(mdTemplateParentItemList, MdTemplateItemVO.class);
        for (MdTemplateItemVO mdTemplateItemVO : mdTemplateItemVOList) {
            if (MdInputTypeEnum.TEXT.getNumber().equals(mdTemplateItemVO.getInputType())) {
                // 获取子文本项目
                mdTemplateItemWrapper = new EntityWrapper<MdTemplateItem>();
                mdTemplateItemWrapper.eq("parent_id", mdTemplateItemVO.getId());
                mdTemplateItemWrapper.eq("template_id", id);
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
        }
        mdTemplateDetailVO.setItems(mdTemplateItemVOList);
        return mdTemplateDetailVO;
    }
    
    
}
