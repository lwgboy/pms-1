package cn.yesway.pms.core.report.biz;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.yesway.pms.common.core.secret.ASESecret;
import cn.yesway.pms.common.enums.ExpenseType;
import cn.yesway.pms.core.project.dao.ProjectDao;
import cn.yesway.pms.core.project.entity.Project;
import cn.yesway.pms.core.report.dao.ProjectReportCastDao;
import cn.yesway.pms.core.report.entity.EmployeeSalaryReport;
import cn.yesway.pms.core.report.entity.ProjectCastReport;
import cn.yesway.pms.core.report.entity.ProjectExpenseCast;
import cn.yesway.pms.core.report.entity.ProjectResourceCast;
import cn.yesway.pms.core.report.entity.ProjectWorkTime;

@Component("projectCastReportBiz")
public class ProjectCastReportBiz {

	@Autowired
	private ProjectReportCastDao projectReportCastDao;
	@Autowired
	private ProjectDao projectDao;
	private static final String SALT = "Cn>YseWay9%!(0";

	public List<ProjectCastReport> selectWorkTime(Map<String, Object> map) {
		Map<String, ProjectCastReport> projectCastReports = new TreeMap<>();
		initProject(projectCastReports);
		initWorkTime(projectCastReports, map);
		initResource(projectCastReports, map);
		initExpense(projectCastReports, map);

		Iterator<Entry<String, ProjectCastReport>> iter = projectCastReports.entrySet().iterator();
		while (iter.hasNext()) {
			// 薪资费用
			Entry<String, ProjectCastReport> entry = iter.next();
			ProjectCastReport report = entry.getValue();
			BigDecimal salary = report.getSalary();
			if (salary == null) {
				salary = new BigDecimal(0.0);
			}
			salary = salary.setScale(2, BigDecimal.ROUND_HALF_UP);
			report.setSalary(salary);
			// 运营费用
			BigDecimal op_cast = report.getOperatingCast();
			if (op_cast == null) {
				op_cast = new BigDecimal(0.0);
			}
			op_cast = op_cast.setScale(2, BigDecimal.ROUND_HALF_UP);
			report.setOperatingCast(op_cast);
			// 房租费用
			BigDecimal rent_cast = report.getRentCast();
			if (rent_cast == null) {
				rent_cast = new BigDecimal(0.0);
			}
			rent_cast = rent_cast.setScale(2, BigDecimal.ROUND_HALF_UP);
			report.setRentCast(rent_cast);
			// 资产费用
			BigDecimal asset_cast = report.getAssetCast();
			if (asset_cast == null) {
				asset_cast = new BigDecimal(0.0);
			}
			asset_cast = asset_cast.setScale(2, BigDecimal.ROUND_HALF_UP);
			report.setAssetCast(asset_cast);
			// 资源费用
			BigDecimal re_cast = report.getResourceCast();
			if (re_cast == null) {
				re_cast = new BigDecimal(0.0);
			}
			re_cast = re_cast.setScale(2, BigDecimal.ROUND_HALF_UP);
			report.setResourceCast(re_cast);
			// 总费用
			BigDecimal total_cast = new BigDecimal(0.0);
			total_cast = total_cast.add(salary).add(op_cast).add(rent_cast).add(asset_cast).add(re_cast);
			total_cast = total_cast.setScale(2, BigDecimal.ROUND_HALF_UP);
			report.setTotalCast(total_cast);
		}
		List<ProjectCastReport> result = new ArrayList<>(projectCastReports.values());
		return result;
	}

	private void initProject(Map<String, ProjectCastReport> projectCastReports) {
		List<Project> projects = projectDao.listByParam(null);
		for (Project project : projects) {
			ProjectCastReport projectCastReport = new ProjectCastReport();
			projectCastReport.setProject(project);
			projectCastReports.put(project.getName(), projectCastReport);
		}
	}

	private void initWorkTime(Map<String, ProjectCastReport> projectCastReports, Map<String, Object> map) {
		// 计算时薪
		List<EmployeeSalaryReport> employeeSalary = projectReportCastDao.selectEmployeeSalary(map);
		Map<Long, BigDecimal> salaries = new HashMap<>();
		for (EmployeeSalaryReport empSalary : employeeSalary) {
			String hourlySalary = new String(ASESecret.decrypt(ASESecret.parseHexStr2Byte(empSalary.getValue()), SALT));
			salaries.put(empSalary.getEmployee().getId(), new BigDecimal(hourlySalary));
		}
		if (!salaries.isEmpty()) {
			// 计算工时费用
			List<ProjectWorkTime> projectWorkTime = projectReportCastDao.selectWorkTime(map);
			for (ProjectWorkTime worktime : projectWorkTime) {
				// 计算该员工的工时费用。
				BigDecimal salary = salaries.get(worktime.getEmployee().getId());
				BigDecimal cast = salary.multiply(new BigDecimal(worktime.getWorkTime()));
				// 将该员工的工时费用，添加到该项目。
				ProjectCastReport projectCastReport = projectCastReports.get(worktime.getProject().getName());
				BigDecimal total_salary = projectCastReport.getSalary();
				if (total_salary == null) {
					total_salary = new BigDecimal(0.0);
				}
				total_salary = total_salary.add(cast);
				projectCastReport.setSalary(total_salary);
			}
		}
	}

	private void initResource(Map<String, ProjectCastReport> projectCastReports, Map<String, Object> map) {
		List<ProjectResourceCast> resourceCast = projectReportCastDao.selectResourceCast(map);
		for (ProjectResourceCast resource : resourceCast) {
			ProjectCastReport projectCastReport = projectCastReports.get(resource.getProject().getName());
			BigDecimal total_resource_cast = projectCastReport.getResourceCast();
			if (total_resource_cast == null) {
				total_resource_cast = new BigDecimal(0.0);
			}
			total_resource_cast = total_resource_cast.add(resource.getCast());
			projectCastReport.setResourceCast(total_resource_cast);
		}
	}

	private void initExpense(Map<String, ProjectCastReport> projectCastReports, Map<String, Object> map) {
		List<ProjectExpenseCast> expenseCast = projectReportCastDao.selectExpenseCast(map);
		for (ProjectExpenseCast expense : expenseCast) {
			ProjectCastReport projectCastReport = projectCastReports.get(expense.getProject().getName());
			if (expense.getExpense().getType() == ExpenseType.OPERATING) {
				BigDecimal total_op_cast = projectCastReport.getOperatingCast();
				if (total_op_cast == null) {
					total_op_cast = new BigDecimal(0.0);
				}
				total_op_cast = total_op_cast.add(expense.getCast());
				projectCastReport.setOperatingCast(total_op_cast);
			} else if (expense.getExpense().getType() == ExpenseType.RANT) {
				BigDecimal total_rent_cast = projectCastReport.getRentCast();
				if (total_rent_cast == null) {
					total_rent_cast = new BigDecimal(0.0);
				}
				total_rent_cast = total_rent_cast.add(expense.getCast());
				projectCastReport.setRentCast(total_rent_cast);
			} else {
				BigDecimal total_asset_cast = projectCastReport.getAssetCast();
				if (total_asset_cast == null) {
					total_asset_cast = new BigDecimal(0.0);
				}
				total_asset_cast = total_asset_cast.add(expense.getCast());
				projectCastReport.setAssetCast(total_asset_cast);
			}
		}
	}

}
