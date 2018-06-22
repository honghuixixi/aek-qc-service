package com.aek.ebey.qc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aek.common.core.base.BaseServiceImpl;
import com.aek.common.core.exception.ExceptionFactory;
import com.aek.ebey.qc.mapper.QcAssetsMapper;
import com.aek.ebey.qc.model.QcAssets;
import com.aek.ebey.qc.model.QcImplement;
import com.aek.ebey.qc.query.QcAssetsByImplementIdQuery;
import com.aek.ebey.qc.query.QcAssetsQuery;
import com.aek.ebey.qc.query.QcAssetsQuery2;
import com.aek.ebey.qc.request.QcAssetsResponse;
import com.aek.ebey.qc.request.QcAssetsResponse2;
import com.aek.ebey.qc.request.QcImAssets;
import com.aek.ebey.qc.request.QcImAssets3;
import com.aek.ebey.qc.service.QcAssetsService;
import com.aek.ebey.qc.service.QcImplementService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author aek
 * @since 2017-11-06
 */
@Service
@Transactional
public class QcAssetsServiceImpl extends BaseServiceImpl<QcAssetsMapper, QcAssets> implements QcAssetsService {

	@Autowired
	private QcAssetsMapper qcAssetsMapper;
	
	@Autowired
	private QcImplementService qcImplementService;
	
	@Override
	public List<QcAssetsResponse> searchQcAssets(Page<QcAssetsResponse> pageQcAssets, QcAssetsQuery query) {
		return qcAssetsMapper.searchQcAssets(pageQcAssets, query);
	}
	@Override
	public List<QcAssetsResponse> selectByimplementId(Long id) {
		return qcAssetsMapper.selectByimplementId(id);
	}
	@Override
	public List<QcAssetsResponse> searchContentByImplementId(Page<QcAssetsResponse> pageQcAssets,
			QcAssetsByImplementIdQuery query) {
		return qcAssetsMapper.searchContentByImplementId(pageQcAssets, query);
	}
	@Override
	public List<QcAssetsResponse2> searchQcAssets2(Page<QcAssetsResponse2> pageQcAssets, QcAssetsQuery2 query) {
		return qcAssetsMapper.searchQcAssets2(pageQcAssets, query);
	}
	@Override
	public int check(QcImAssets request) {
		return qcAssetsMapper.check(request);
	}
	@Override
	public void submitQcImAssets(QcImAssets3 request) {
		if(request!=null&&request.getId()!=null){
			Wrapper<QcImplement> wrapper=new EntityWrapper<>();
			wrapper.eq("paln_id", request.getId()).eq("status", 1);
			List<QcImplement> list=qcImplementService.selectList(wrapper);
			if(list!=null&&list.size()>0){
				QcImplement QcImplement=list.get(0);
				request.setId(QcImplement.getId());
			}else{
				throw ExceptionFactory.create("Q_017");
			}
		}else{
			throw ExceptionFactory.create("Q_003");
		}
		request.setImAtatus(request.getStatus());
		qcAssetsMapper.submitQcImAssets(request);
	}
	
}
