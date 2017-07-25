package cn.yesway.pms.web.console.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.yesway.pms.common.page.PageBean;
import cn.yesway.pms.common.page.PageParam;
import cn.yesway.pms.common.web.entity.WebAppResult;
import cn.yesway.pms.common.web.entity.WebConstant;
import cn.yesway.pms.common.web.enums.ResultStatus;
import cn.yesway.pms.core.cast.employee.biz.WorkscheduleBiz;
import cn.yesway.pms.core.cast.employee.biz.WorkscheduleMonthBiz;
import cn.yesway.pms.core.cast.employee.entity.Workschedule;
import cn.yesway.pms.core.cast.employee.entity.WorkscheduleMonth;
import cn.yesway.pms.core.employee.entity.Employee;

@RestController
@RequestMapping("/cast/employee")
public class WorkscheduleController {

	@Autowired
	private WorkscheduleBiz workscheduleBiz;
	@Autowired
	private WorkscheduleMonthBiz workscheduleMonthBiz;

	@RequestMapping(value = "/workSchedule/month/upload", method = RequestMethod.POST)
	public WebAppResult upload(@RequestParam("file") MultipartFile file, HttpServletRequest request)
			throws IOException {
		// 获得上传文件夹的路径。
		String folderPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/workschedule");
		// 获得上传文件的文件名称。
		String fileName = file.getOriginalFilename();
		// 定义上传路径，如果目录不存在则创建
		File output = new File(folderPath, fileName);
		if (!output.exists()) {
			output.mkdirs();
		}
		// 上传文件
		file.transferTo(output);
		workscheduleMonthBiz.importFile(output.getPath());
		return new WebAppResult(ResultStatus.SUCCESS, "上传成功", null);
	}

	@RequestMapping(value = "/workSchedule/month/list", method = RequestMethod.POST)
	public WebAppResult monthlist(@RequestBody Map<String, Object> map) {
		PageParam pageParam = new PageParam(Integer.parseInt(map.get("pageNum").toString()),
				Integer.parseInt(map.get("numPerPage").toString()));
		PageBean<WorkscheduleMonth> data = workscheduleMonthBiz.listByPageAndParam(pageParam, map);
		return new WebAppResult(ResultStatus.SUCCESS, "", data);
	}
	
	@RequestMapping(value = "/workSchedule/month/delete/{id}", method = RequestMethod.GET)
	public WebAppResult monthlist(@PathVariable Long id) {
		workscheduleMonthBiz.delete(id);
		return new WebAppResult(ResultStatus.SUCCESS, "", null);
	}
	
	@RequestMapping(value = "/workSchedule/month/updateStatus", method = RequestMethod.POST)
	public WebAppResult monthUpdateStatus(@RequestBody WorkscheduleMonth workscheduleMonth) {
		workscheduleMonthBiz.updateStatus(workscheduleMonth);
		return new WebAppResult(ResultStatus.SUCCESS, "", null);
	}

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
