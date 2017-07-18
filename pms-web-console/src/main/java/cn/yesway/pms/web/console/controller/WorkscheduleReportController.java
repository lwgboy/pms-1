package cn.yesway.pms.web.console.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.yesway.pms.common.web.entity.WebAppResult;
import cn.yesway.pms.common.web.entity.WebConstant;
import cn.yesway.pms.common.web.enums.ResultStatus;
import cn.yesway.pms.core.employee.biz.EmployeeBiz;
import cn.yesway.pms.core.employee.entity.Employee;
import cn.yesway.pms.core.project.biz.ProjectBiz;
import cn.yesway.pms.core.project.entity.Project;
import cn.yesway.pms.core.report.biz.WorkscheduleReportBiz;
import cn.yesway.pms.core.report.entity.EmployeeWorkscheduleReport;
import cn.yesway.pms.core.report.entity.ProjectWorkscheduleReport;

@RestController
@RequestMapping("/report/workschedule")
public class WorkscheduleReportController {

	@Autowired
	private EmployeeBiz employeeBiz;
	@Autowired
	private ProjectBiz projectBiz;
	@Autowired
	private WorkscheduleReportBiz workscheduleReportBiz;

	// 当前登录人的项目工时信息
	// 即当前登录人在各个项目中的工时投入情况
	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public WebAppResult employeeReport(@RequestBody Map<String, Object> map, HttpSession httpSession) {
		Employee employee = (Employee) httpSession.getAttribute(WebConstant.SESSION_NAME);
		map.put("employeeIds", new Long[] { employee.getId() });
		List<ProjectWorkscheduleReport> report = workscheduleReportBiz.listProjectWorkscheduleReportByParam(map);
		WebAppResult result = new WebAppResult(ResultStatus.SUCCESS, "", report);
		return result;
	}

	// 当前登录人所在部门下，所有员工的项目工时信息
	// 即当前登录人所在的部门下，所有员工在各个项目中的投入情况
	@RequestMapping(value = "/myDepartment", method = RequestMethod.POST)
	public WebAppResult myDepartmentReport(@RequestBody Map<String, Object> map, HttpSession httpSession) {
		Long deptId = null;
		if (map.get("deptId") == null || "".equals(map.get("deptId"))) {
			Employee employee = (Employee) httpSession.getAttribute(WebConstant.SESSION_NAME);
			deptId = employee.getDepartment().getId();
		} else {
			deptId = Long.parseLong((String) map.get("deptId"));
		}
		List<Employee> myDeptEmployees = employeeBiz.listByDeptId(deptId);
		Object[] ids = myDeptEmployees.stream().map(Employee::getId).collect(Collectors.toList()).toArray();
		map.put("employeeIds", ids);
		WebAppResult result = new WebAppResult(ResultStatus.SUCCESS, "",
				workscheduleReportBiz.listProjectWorkscheduleReportByParam(map));
		return result;
	}

	// 显示某一个项目下，当前登录人所在部门的人员，在该项目下投入的工时
	@RequestMapping(value = "/myDepartment/employees", method = RequestMethod.POST)
	public WebAppResult myDepartmentEmployeesReport(@RequestBody Map<String, Object> map, HttpSession httpSession) {

		Long deptId = null;
		if (map.get("deptId") == null || "".equals(map.get("deptId"))) {
			Employee employee = (Employee) httpSession.getAttribute(WebConstant.SESSION_NAME);
			deptId = employee.getDepartment().getId();
		} else {
			deptId = Long.parseLong((String) map.get("deptId"));
		}

		List<Employee> myDeptEmployees = employeeBiz.listByDeptId(deptId);
		Object[] ids = myDeptEmployees.stream().map(Employee::getId).collect(Collectors.toList()).toArray();
		map.put("employeeIds", ids);
		WebAppResult result = new WebAppResult(ResultStatus.SUCCESS, "",
				workscheduleReportBiz.listEmployeeWorkscheduleReportByParam(map));
		return result;
	}

	// 当前登录人所在部门和子部门，在项目中的工时情况
	// 即当前登录人所在的部门或子部门，在各个项目中的投入情况
	@RequestMapping(value = "/myCenter", method = RequestMethod.POST)
	public WebAppResult myCenterReport(@RequestBody Map<String, Object> map, HttpSession httpSession) {
		Employee employee = (Employee) httpSession.getAttribute(WebConstant.SESSION_NAME);
		map.put("departmentId", employee.getDepartment().getId());
		WebAppResult result = new WebAppResult(ResultStatus.SUCCESS, "",
				workscheduleReportBiz.listDepartmentWorkscheduleReportByParam(map));
		return result;
	}

	@RequestMapping(value = "/myProject", method = RequestMethod.POST)
	public WebAppResult myProjectReport(@RequestBody Map<String, Object> map, HttpSession httpSession) {
		Employee employee = (Employee) httpSession.getAttribute(WebConstant.SESSION_NAME);
		map.put("employeeId", employee.getId());
		List<Project> projects = projectBiz.listByEmployeeId(map);
		Object[] ids = projects.stream().map(Project::getId).collect(Collectors.toList()).toArray();
		map.put("projectIds", ids);
		List<ProjectWorkscheduleReport> report = workscheduleReportBiz.listProjectWorkscheduleReportByParam(map);
		WebAppResult result = new WebAppResult(ResultStatus.SUCCESS, "", report);
		return result;
	}

	@RequestMapping(value = "/myProject/employees", method = RequestMethod.POST)
	public WebAppResult myProjectEmployeesReport(@RequestBody Map<String, Object> map, HttpSession httpSession) {
		List<EmployeeWorkscheduleReport> report = workscheduleReportBiz.listEmployeeWorkscheduleReportByParam(map);
		WebAppResult result = new WebAppResult(ResultStatus.SUCCESS, "", report);
		return result;
	}

	@RequestMapping(value = "/employee/detail", method = RequestMethod.POST)
	public WebAppResult reportDetail(@RequestBody Map<String, Object> map, HttpSession httpSession) {
		Employee employee = (Employee) httpSession.getAttribute(WebConstant.SESSION_NAME);
		map.put("employeeIds", new Long[] { employee.getId() });
		WebAppResult result = new WebAppResult(ResultStatus.SUCCESS, "", workscheduleReportBiz.listDetailByParam(map));
		return result;
	}

	@RequestMapping(value = "/employee/detail/id", method = RequestMethod.POST)
	public WebAppResult reportDetailById(@RequestBody Map<String, Object> map, HttpSession httpSession) {
		WebAppResult result = new WebAppResult(ResultStatus.SUCCESS, "", workscheduleReportBiz.listDetailByParam(map));
		return result;
	}

	@RequestMapping(value = "/employee/count", method = RequestMethod.POST)
	public WebAppResult reportCount(@RequestBody Map<String, Object> map, HttpSession httpSession) {
		Employee employee = (Employee) httpSession.getAttribute(WebConstant.SESSION_NAME);
		map.put("departmentId", employee.getDepartment().getId());
		WebAppResult result = new WebAppResult(ResultStatus.SUCCESS, "",
				workscheduleReportBiz.listWorkscheduleCountByParam(map));
		return result;
	}
}
