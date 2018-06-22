package com.aek.ebey.qc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aek.common.core.Result;
import com.aek.common.core.base.BaseServiceImpl;
import com.aek.common.core.exception.ExceptionFactory;
import com.aek.common.core.serurity.WebSecurityUtils;
import com.aek.common.core.serurity.model.AuthUser;
import com.aek.ebey.qc.inter.CheckStatus;
import com.aek.ebey.qc.inter.LiuHuiPage;
import com.aek.ebey.qc.mapper.PmImplementMapper;
import com.aek.ebey.qc.model.PmImplement;
import com.aek.ebey.qc.query.PmImplementRecordQuery;
import com.aek.ebey.qc.request.PmImplementRecord;
import com.aek.ebey.qc.service.PmImplementService;
import com.aek.ebey.qc.service.feign.DeptClientService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * PM实施 服务实现类
 * </p>
 *
 * @author aek
 * @since 2017-11-30
 */
@Service
@Transactional
public class PmImplementServiceImpl extends BaseServiceImpl<PmImplementMapper, PmImplement>
		implements PmImplementService {

	@Autowired
	private PmImplementMapper pmImplementMapper;
	
	@Autowired
	private DeptClientService deptClientService;

	@Override
	@Transactional(readOnly = true)
	public LiuHuiPage<PmImplementRecord> getRecord(PmImplementRecordQuery query) {
		Page<PmImplementRecord> pmImplementRecord = query.getPagePlus();
		List<PmImplementRecord> list = pmImplementMapper.getRecord(pmImplementRecord, query);
		LiuHuiPage<PmImplementRecord> page = new LiuHuiPage<>(pmImplementRecord);
		page.setRecords(list);
		return page;
	}

	@Override
	public void check(Long id) {
		AuthUser user = WebSecurityUtils.getCurrentUser();
		Result<Boolean> ret = deptClientService.checkUserPermission(user.getId(), "PM_CHECK_MANAGE",
				WebSecurityUtils.getCurrentToken());
		if (ret != null) {
			if (ret.getData()) {
				Wrapper<PmImplement> wrapper = new EntityWrapper<>();
				wrapper.eq("implement_id", id);
				List<PmImplement> list = pmImplementMapper.selectList(wrapper);
				if (list != null && list.size() > 0) {
					PmImplement pmImplement = list.get(0);
					if (pmImplement.getIsCheck().intValue() == CheckStatus.TWO.getCode()) {
						throw ExceptionFactory.create("Q_019");
					}
					pmImplement.setIsCheck(CheckStatus.TWO.getCode());
					pmImplementMapper.updateById(pmImplement);
				} else {
					throw ExceptionFactory.create("Q_019");
				}

			}else{
				throw ExceptionFactory.create("Q_021");
			}
		} else {
			throw ExceptionFactory.create("Q_005");
		}

	}

	@Override
	public Integer getPmCheckWaitToDo(Long id) {
		int i=pmImplementMapper.selectByUserId(id);
		return i;
	}

}
