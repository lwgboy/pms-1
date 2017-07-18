package cn.yesway.pms.core.report.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.yesway.pms.common.core.dao.BaseDaoImpl;
import cn.yesway.pms.core.report.entity.DepartmentWorkscheduleReport;
import cn.yesway.pms.core.report.entity.EmployeeWorkscheduleReport;
import cn.yesway.pms.core.report.entity.ProjectWorkscheduleReport;
import cn.yesway.pms.core.report.entity.WorkscheduleCountReport;
import cn.yesway.pms.core.report.entity.WorkscheduleReportDetail;

@Component("workscheduleReportDao")
public class WorkscheduleReportDaoImpl extends BaseDaoImpl<ProjectWorkscheduleReport> implements WorkscheduleReportDao {

	@Override
	public List<WorkscheduleReportDetail> listDetailByParam(Map<String, Object> map) {
		String sqlId = getStatement("listDetailByParam");
		return getSessionTemplate().selectList(sqlId, map);
	}

	@Override
	public List<ProjectWorkscheduleReport> listProjectWorkscheduleReportByParam(Map<String, Object> map) {
		String sqlId = getStatement("listProjectWorkscheduleReportByParam");
		return getSessionTemplate().selectList(sqlId, map);
	}

	@Override
	public List<DepartmentWorkscheduleReport> listDepartmentWorkscheduleReportByParam(Map<String, Object> map) {
		String sqlId = getStatement("listDepartmentWorkscheduleReportByParam");
		return getSessionTemplate().selectList(sqlId, map);
	}

	@Override
	public List<EmployeeWorkscheduleReport> listEmployeeWorkscheduleReportByParam(Map<String, Object> map) {
		String sqlId = getStatement("listEmployeeWorkscheduleReportByParam");
		return getSessionTemplate().selectList(sqlId, map);
	}

	@Override
	public List<WorkscheduleCountReport> listWorkscheduleCountByParam(Map<String, Object> map) {
		String sqlId = getStatement("listWorkscheduleCountByParam");
		return getSessionTemplate().selectList(sqlId, map);
	}

}
