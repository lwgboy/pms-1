package cn.yesway.pms.web.console.controller;

import java.util.HashMap;
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
import cn.yesway.pms.core.employee.entity.Employee;
import cn.yesway.pms.core.project.biz.ProjectBiz;
import cn.yesway.pms.core.project.entity.Project;

@RestController
@RequestMapping(value = { "/project" })
public class ProjectController {

	@Autowired
	private ProjectBiz projectBiz;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public WebAppResult findUser(@RequestBody Map<String, Object> map) {
		PageParam pageParam = new PageParam(Integer.parseInt(map.get("pageNum").toString()),
				Integer.parseInt(map.get("numPerPage").toString()));
		WebAppResult result = new WebAppResult(ResultStatus.SUCCESS, "", projectBiz.listByPageAndParam(pageParam, map));
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public WebAppResult create(@RequestBody Project project) {
		if (projectBiz.create(project)) {
			return new WebAppResult(ResultStatus.SUCCESS, "创建项目成功。", null);
		}
		return new WebAppResult(ResultStatus.FAIL, "创建项目失败。", null);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public WebAppResult findById(@PathVariable long id) {
		Project project = projectBiz.findById(id);
		WebAppResult result;
		if (project != null) {
			result = new WebAppResult(ResultStatus.SUCCESS, "", project);
		} else {
			result = new WebAppResult(ResultStatus.FAIL, "没有查找到项目。", null);
		}
		return result;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public WebAppResult update(@RequestBody Project project) {
		WebAppResult result;
		if (projectBiz.update(project)) {
			result = new WebAppResult(ResultStatus.SUCCESS, "修改项目信息成功。", project.getId());
		} else {
			result = new WebAppResult(ResultStatus.FAIL, "修改项目信息失败。", null);
		}
		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public WebAppResult delete(@RequestBody Long[] ids) {
		WebAppResult result;
		if (projectBiz.deleteByIds(ids)) {
			result = new WebAppResult(ResultStatus.SUCCESS, "删除项目信息成功。", null);
		} else {
			result = new WebAppResult(ResultStatus.FAIL, "删除项目信息失败。", null);
		}
		return result;
	}

	@RequestMapping(value = "/isExistsCode", method = RequestMethod.POST)
	public WebAppResult isExistsCode(@RequestBody Map<String, Object> params) {
		WebAppResult result;
		if (!projectBiz.isExists(params)) {
			result = new WebAppResult(ResultStatus.SUCCESS, "", true);
		} else {
			result = new WebAppResult(ResultStatus.FAIL, "项目编号已存在", false);
		}
		return result;
	}

	@RequestMapping(value = "/listByStatus", method = RequestMethod.GET)
	public WebAppResult findListByStatus() {
		List<Project> projects = projectBiz.listByStatus();
		return new WebAppResult(ResultStatus.SUCCESS, "", projects);
	}

	@RequestMapping(value = "/listByEmployeeId", method = RequestMethod.POST)
	public WebAppResult findListByEmployeeId(@RequestBody Map<String, Object> map, HttpSession httpSession) {
		Employee employee = (Employee) httpSession.getAttribute(WebConstant.SESSION_NAME);
		if (employee != null) {
			map.put("employeeId", employee.getId());
			PageParam pageParam = new PageParam(Integer.parseInt(map.get("pageNum").toString()),
					Integer.parseInt(map.get("numPerPage").toString()));
			PageBean<Project> projects = projectBiz.listByEmployeeId(pageParam, map);
			return new WebAppResult(ResultStatus.SUCCESS, "", projects);
		} else {
			return new WebAppResult(ResultStatus.TIMEOUT, "", null);
		}
	}

	@RequestMapping(value = "/myProject", method = RequestMethod.GET)
	public WebAppResult listByEmployeeId(HttpSession httpSession) {
		Employee employee = (Employee) httpSession.getAttribute(WebConstant.SESSION_NAME);
		if (employee != null) {
			Map<String, Object> map = new HashMap<>();
			map.put("employeeId", employee.getId());
			List<Project> projects = projectBiz.listByEmployeeId(map);
			return new WebAppResult(ResultStatus.SUCCESS, "", projects);
		} else {
			return new WebAppResult(ResultStatus.TIMEOUT, "", null);
		}
	}

	@RequestMapping(value = "/listByRunning", method = RequestMethod.GET)
	public WebAppResult findListByRunning() {
		List<Project> projects = projectBiz.listByRunning();
		return new WebAppResult(ResultStatus.SUCCESS, "", projects);
	}

}
