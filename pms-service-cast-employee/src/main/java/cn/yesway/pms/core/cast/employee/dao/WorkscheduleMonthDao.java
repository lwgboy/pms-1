package cn.yesway.pms.core.cast.employee.dao;

import cn.yesway.pms.common.core.dao.BaseDao;
import cn.yesway.pms.core.cast.employee.entity.WorkscheduleMonth;
import cn.yesway.pms.core.cast.employee.entity.WorkscheduleMonthDetail;

public interface WorkscheduleMonthDao extends BaseDao<WorkscheduleMonth> {

	void insertDetail(WorkscheduleMonthDetail workscheduleMonthDetail);

	void insertProjectDetail(WorkscheduleMonthDetail workscheduleMonthDetail);

	void deleteDetail(Long id);

	void deleteProjectDetail(Long id);

	void updateStatus(WorkscheduleMonth workscheduleMonth);

}
