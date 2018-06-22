package com.aek.ebey.qc.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.aek.common.core.base.BaseMapper;
import com.aek.common.core.serurity.model.AuthUser;
import com.aek.ebey.qc.model.PmPlan;
import com.aek.ebey.qc.query.PmPlanQuery;
import com.aek.ebey.qc.request.PmOverView;
import com.aek.ebey.qc.request.PmPlanResponse;
import com.aek.ebey.qc.request.PmPlanTotal;
import com.aek.ebey.qc.request.TimeQuery;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author aek
 * @since 2017-11-28
 */
public interface PmPlanMapper extends BaseMapper<PmPlan> {

	List<PmPlanResponse> search(Page<PmPlanResponse> pagePmPlan,@Param("q") PmPlanQuery query, @Param("user") AuthUser authUser);

	List<PmOverView> selcetByCondtion(@Param("q") TimeQuery query);

}