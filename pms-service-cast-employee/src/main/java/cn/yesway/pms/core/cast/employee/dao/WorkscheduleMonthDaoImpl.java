package cn.yesway.pms.core.cast.employee.dao;

import org.springframework.stereotype.Component;

import cn.yesway.pms.common.core.dao.BaseDaoImpl;
import cn.yesway.pms.core.cast.employee.entity.WorkscheduleMonth;
import cn.yesway.pms.core.cast.employee.entity.WorkscheduleMonthDetail;

@Component("workscheduleMonthDao")
public class WorkscheduleMonthDaoImpl extends BaseDaoImpl<WorkscheduleMonth> implements WorkscheduleMonthDao {

	@Override
	public void insertDetail(WorkscheduleMonthDetail workscheduleMonthDetail) {
		String sqlId = super.getStatement("insertDetail");
		super.getSessionTemplate().insert(sqlId, workscheduleMonthDetail);
	}

	@Override
	public void deleteDetail(Long id) {
		String sqlId = super.getStatement("deleteDetail");
		super.getSessionTemplate().delete(sqlId, id);
	}

	@Override
	public void updateStatus(WorkscheduleMonth workscheduleMonth) {
		String sqlId = super.getStatement("updateStatus");
		super.getSessionTemplate().update(sqlId, workscheduleMonth);
	}

	@Override
	public void insertProjectDetail(WorkscheduleMonthDetail workscheduleMonthDetail) {
		String sqlId = super.getStatement("insertProjectDetail");
		super.getSessionTemplate().insert(sqlId, workscheduleMonthDetail);
	}

	@Override
	public void deleteProjectDetail(Long id) {
		String sqlId = super.getStatement("deleteProjectDetail");
		super.getSessionTemplate().delete(sqlId, id);
	}

}
