package cn.yesway.pms.core.report.biz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.yesway.pms.core.report.dao.WorkscheduleReportDao;
import cn.yesway.pms.core.report.entity.DepartmentWorkscheduleReport;
import cn.yesway.pms.core.report.entity.EmployeeWorkscheduleReport;
import cn.yesway.pms.core.report.entity.ProjectWorkscheduleReport;
import cn.yesway.pms.core.report.entity.WorkscheduleCountReport;
import cn.yesway.pms.core.report.entity.WorkscheduleReportDetail;

@Component("workscheduleReportBiz")
public class WorkscheduleReportBiz {

	@Autowired
	private WorkscheduleReportDao workscheduleReportDao;

	public List<ProjectWorkscheduleReport> listProjectWorkscheduleReportByParam(Map<String, Object> map) {
		return workscheduleReportDao.listProjectWorkscheduleReportByParam(map);
	}

	public List<EmployeeWorkscheduleReport> listEmployeeWorkscheduleReportByParam(Map<String, Object> map) {
		return workscheduleReportDao.listEmployeeWorkscheduleReportByParam(map);
	}

	public List<DepartmentWorkscheduleReport> listDepartmentWorkscheduleReportByParam(Map<String, Object> map) {
		return workscheduleReportDao.listDepartmentWorkscheduleReportByParam(map);
	}

	public List<WorkscheduleReportDetail> listDetailByParam(Map<String, Object> map) {
		return workscheduleReportDao.listDetailByParam(map);
	}

	public List<WorkscheduleCountReport> listWorkscheduleCountByParam(Map<String, Object> map) {
		return workscheduleReportDao.listWorkscheduleCountByParam(map);
	}

}
