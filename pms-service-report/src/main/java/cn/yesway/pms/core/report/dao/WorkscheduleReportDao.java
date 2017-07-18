package cn.yesway.pms.core.report.dao;

import java.util.List;
import java.util.Map;

import cn.yesway.pms.common.core.dao.BaseDao;
import cn.yesway.pms.core.report.entity.DepartmentWorkscheduleReport;
import cn.yesway.pms.core.report.entity.EmployeeWorkscheduleReport;
import cn.yesway.pms.core.report.entity.ProjectWorkscheduleReport;
import cn.yesway.pms.core.report.entity.WorkscheduleCountReport;
import cn.yesway.pms.core.report.entity.WorkscheduleReportDetail;

public interface WorkscheduleReportDao extends BaseDao<ProjectWorkscheduleReport> {

	List<ProjectWorkscheduleReport> listProjectWorkscheduleReportByParam(Map<String, Object> map);

	List<EmployeeWorkscheduleReport> listEmployeeWorkscheduleReportByParam(Map<String, Object> map);

	List<DepartmentWorkscheduleReport> listDepartmentWorkscheduleReportByParam(Map<String, Object> map);

	List<WorkscheduleReportDetail> listDetailByParam(Map<String, Object> map);

	List<WorkscheduleCountReport> listWorkscheduleCountByParam(Map<String, Object> map);

}
