package cn.yesway.pms.web.console.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.yesway.pms.common.page.PageBean;
import cn.yesway.pms.common.page.PageParam;
import cn.yesway.pms.common.web.entity.WebAppResult;
import cn.yesway.pms.common.web.entity.WebConstant;
import cn.yesway.pms.common.web.enums.ResultStatus;
import cn.yesway.pms.core.cast.employee.biz.WorkscheduleBiz;
import cn.yesway.pms.core.cast.employee.entity.Workschedule;
import cn.yesway.pms.core.employee.entity.Employee;

@RestController
@RequestMapping("/cast/employee")
public class WorkscheduleController {

	@Autowired
	private WorkscheduleBiz workscheduleBiz;

	@RequestMapping(value = "/workSchedule/save", method = RequestMethod.POST)
	public WebAppResult save(@RequestBody Workschedule workschedule, HttpSession httpSession) {
		Employee employee = (Employee) httpSession.getAttribute(WebConstant.SESSION_NAME);
		if (employee != null) {
			workschedule.setEmployee(employee);
			if (workscheduleBiz.create(workschedule)) {
				return new WebAppResult(ResultStatus.SUCCESS, "保存成功", null);
			} else {
				return new WebAppResult(ResultStatus.FAIL, "保存失败", null);
			}
		}
		return new WebAppResult(ResultStatus.TIMEOUT, "", null);
	}

	@RequestMapping(value = "/workSchedule/edit", method = RequestMethod.POST)
	public WebAppResult edit(@RequestBody Workschedule workschedule, HttpSession httpSession) {
		Employee employee = (Employee) httpSession.getAttribute(WebConstant.SESSION_NAME);
		if (employee != null) {
			workschedule.setEmployee(employee);
			if (workscheduleBiz.update(workschedule)) {
				return new WebAppResult(ResultStatus.SUCCESS, "保存成功", null);
			} else {
				return new WebAppResult(ResultStatus.FAIL, "保存失败", null);
			}
		}
		return new WebAppResult(ResultStatus.TIMEOUT, "", null);
	}

	@RequestMapping(value = "/workSchedule/list", method = RequestMethod.POST)
	public WebAppResult list(@RequestBody Map<String, Object> map, HttpSession httpSession) {
		Employee employee = (Employee) httpSession.getAttribute(WebConstant.SESSION_NAME);
		if (employee != null) {
			map.put("employeeId", employee.getId());
			PageParam pageParam = new PageParam(Integer.parseInt(map.get("pageNum").toString()),
					Integer.parseInt(map.get("numPerPage").toString()));
			PageBean<Workschedule> data = workscheduleBiz.listByPageAndParam(pageParam, map);
			return new WebAppResult(ResultStatus.SUCCESS, "", data);
		}
		return new WebAppResult(ResultStatus.TIMEOUT, "", null);
	}

	@RequestMapping(value = "/workSchedule/{id}", method = RequestMethod.GET)
	public WebAppResult findById(@PathVariable Long id) {
		return new WebAppResult(ResultStatus.SUCCESS, "", workscheduleBiz.findById(id));
	}

	@RequestMapping(value = "/workSchedule/pass", method = RequestMethod.POST)
	public WebAppResult approvalPass(@RequestBody Workschedule workschedule, HttpSession httpSession) {
		Employee employee = (Employee) httpSession.getAttribute(WebConstant.SESSION_NAME);
		if (employee != null) {
			workschedule.setEmployee(employee);
			if (workscheduleBiz.approvalPass(workschedule)) {
				return new WebAppResult(ResultStatus.SUCCESS, "", null);
			}
			return new WebAppResult(ResultStatus.FAIL, "", null);
		}
		return new WebAppResult(ResultStatus.TIMEOUT, "", null);
	}
	
	@RequestMapping(value = "/workSchedule/back", method = RequestMethod.POST)
	public WebAppResult approvalFail(@RequestBody Workschedule workschedule, HttpSession httpSession) {
		Employee employee = (Employee) httpSession.getAttribute(WebConstant.SESSION_NAME);
		if (employee != null) {
			workschedule.setEmployee(employee);
			if (workscheduleBiz.approvalFail(workschedule)) {
				return new WebAppResult(ResultStatus.SUCCESS, "", null);
			}
			return new WebAppResult(ResultStatus.FAIL, "", null);
		}
		return new WebAppResult(ResultStatus.TIMEOUT, "", null);
	}

	@RequestMapping(value = "/workSchedule/updateBatchStatus", method = RequestMethod.POST)
	public WebAppResult updateBatchStatus(@RequestBody List<Workschedule> workschedules) {
		workscheduleBiz.updateBatchStatus(workschedules);
		return new WebAppResult(ResultStatus.SUCCESS, "", null);
	}

	@RequestMapping(value = "/workSchedule/isExists", method = RequestMethod.POST)
	public WebAppResult isExists(@RequestBody Map<String, Object> map, HttpSession httpSession) {
		Employee employee = (Employee) httpSession.getAttribute(WebConstant.SESSION_NAME);
		if (employee != null) {
			map.put("employeeId", employee.getId());
			if (!workscheduleBiz.isExistsByEmpIdAndCreateDate(map)) {
				return new WebAppResult(ResultStatus.SUCCESS, "", null);
			}
			return new WebAppResult(ResultStatus.FAIL, "该日期已编写过工时表", null);
		}
		return new WebAppResult(ResultStatus.TIMEOUT, "", null);
	}

	@RequestMapping(value = "/workSchedule/listByStatus")
	public WebAppResult listByStatus(@RequestBody Map<String, Object> map, HttpSession httpSession) {
		Employee employee = (Employee) httpSession.getAttribute(WebConstant.SESSION_NAME);
		if (employee != null) {
			map.put("selfDepartmentId", employee.getDepartment().getId());
			PageParam pageParam = new PageParam(Integer.parseInt(map.get("pageNum").toString()),
					Integer.parseInt(map.get("numPerPage").toString()));
			PageBean<Workschedule> datas = workscheduleBiz.listByStatus(pageParam, map);
			return new WebAppResult(ResultStatus.SUCCESS, "", datas);
		}
		return new WebAppResult(ResultStatus.TIMEOUT, "", null);
	}

	@RequestMapping(value = "/workSchedule/list/param")
	public WebAppResult listByParam(@RequestBody Map<String, Object> map, HttpSession httpSession) {
		Employee employee = (Employee) httpSession.getAttribute(WebConstant.SESSION_NAME);
		if (employee != null) {
			map.put("employeeId", employee.getId());
			return new WebAppResult(ResultStatus.SUCCESS, "", workscheduleBiz.listByParam(map));
		}
		return new WebAppResult(ResultStatus.TIMEOUT, "", null);
	}

}
