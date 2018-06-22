package com.aek.ebey.qc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aek.common.core.base.BaseMapper;
import com.aek.ebey.qc.model.PmImplement;
import com.aek.ebey.qc.query.PmImplementRecordQuery;
import com.aek.ebey.qc.request.PmImplementRecord;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author aek
 * @since 2017-11-30
 */
public interface PmImplementMapper extends BaseMapper<PmImplement> {


	List<PmImplementRecord> getRecord(Page<PmImplementRecord> pmImplementRecord, @Param("q") PmImplementRecordQuery query);

	int selectByUserId(@Param("userid") Long userid);

}