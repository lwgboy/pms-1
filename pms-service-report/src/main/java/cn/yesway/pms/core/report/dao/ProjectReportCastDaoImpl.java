package cn.yesway.pms.core.report.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.yesway.pms.common.core.dao.BaseDaoImpl;
import cn.yesway.pms.core.report.entity.EmployeeSalaryReport;
import cn.yesway.pms.core.report.entity.ProjectCastReport;
import cn.yesway.pms.core.report.entity.ProjectExpenseCast;
import cn.yesway.pms.core.report.entity.ProjectResourceCast;
import cn.yesway.pms.core.report.entity.ProjectWorkTime;

@Component("projectReportCastDaoImpl")
public class ProjectReportCastDaoImpl extends BaseDaoImpl<ProjectCastReport> implements ProjectReportCastDao {

	@Override
	public List<ProjectWorkTime> selectWorkTime(Map<String, Object> map) {
		String sqlId = getStatement("selectWorkTime");
		return getSessionTemplate().selectList(sqlId, map);
	}

	@Override
	public List<EmployeeSalaryReport> selectEmployeeSalary(Map<String, Object> map) {
		String sqlId = getStatement("selectEmployeeSalary");
		return getSessionTemplate().selectList(sqlId, map);
	}

	@Override
	public List<ProjectExpenseCast> selectExpenseCast(Map<String, Object> map) {
		String sqlId = getStatement("selectExpenseCast");
		return getSessionTemplate().selectList(sqlId, map);
	}

	@Override
	public List<ProjectResourceCast> selectResourceCast(Map<String, Object> map) {
		String sqlId = getStatement("selectResourceCast");
		return getSessionTemplate().selectList(sqlId, map);
	}

}
