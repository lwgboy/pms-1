package cn.yesway.pms.core.report.dao;

import java.util.List;
import java.util.Map;

import cn.yesway.pms.common.core.dao.BaseDao;
import cn.yesway.pms.core.report.entity.EmployeeSalaryReport;
import cn.yesway.pms.core.report.entity.ProjectCastReport;
import cn.yesway.pms.core.report.entity.ProjectExpenseCast;
import cn.yesway.pms.core.report.entity.ProjectResourceCast;
import cn.yesway.pms.core.report.entity.ProjectWorkTime;

public interface ProjectReportCastDao extends BaseDao<ProjectCastReport> {

	List<ProjectWorkTime> selectWorkTime(Map<String, Object> map);

	List<EmployeeSalaryReport> selectEmployeeSalary(Map<String, Object> map);

	List<ProjectExpenseCast> selectExpenseCast(Map<String, Object> map);

	List<ProjectResourceCast> selectResourceCast(Map<String, Object> map);

}
