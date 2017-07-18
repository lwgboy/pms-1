package cn.yesway.pms.web.console.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.yesway.pms.common.page.PageParam;
import cn.yesway.pms.common.web.entity.WebAppResult;
import cn.yesway.pms.common.web.entity.WebConstant;
import cn.yesway.pms.common.web.enums.ResultStatus;
import cn.yesway.pms.core.department.biz.DepartmentBiz;
import cn.yesway.pms.core.department.entity.Department;
import cn.yesway.pms.core.employee.biz.EmployeeBiz;
import cn.yesway.pms.core.employee.entity.Employee;

@RestController
@RequestMapping(value = { "/department" })
public class DepartmentController {

	private static final Log LOGGER = LogFactory.getLog(DepartmentController.class);
	@Autowired
	private DepartmentBiz departmentBiz;
	@Autowired
	private EmployeeBiz employeeBiz;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public WebAppResult list(@RequestBody Map<String, Object> map) {
		LOGGER.info("findDepartment");
		PageParam pageParam = new PageParam(Integer.parseInt(map.get("pageNum").toString()),
				Integer.parseInt(map.get("numPerPage").toString()));
		return new WebAppResult(ResultStatus.SUCCESS, "", departmentBiz.listByPageAndParam(pageParam, map));
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public WebAppResult create(@RequestBody Department department) {
		WebAppResult result;
		if (departmentBiz.create(department)) {
			result = new WebAppResult(ResultStatus.SUCCESS, "创建部门成功", null);
		} else {
			result = new WebAppResult(ResultStatus.FAIL, "创建部门失败", null);
		}
		return result;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public WebAppResult findById(@PathVariable long id) {
		Department department = departmentBiz.findById(id);
		WebAppResult result;
		if (department != null) {
			result = new WebAppResult(ResultStatus.SUCCESS, "", department);
		} else {
			result = new WebAppResult(ResultStatus.FAIL, "没有查找到部门", null);
		}
		return result;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public WebAppResult update(@RequestBody Department department) {
		WebAppResult result;
		if (departmentBiz.update(department)) {
			result = new WebAppResult(ResultStatus.SUCCESS, "修改部门成功", department.getId());
		} else {
			result = new WebAppResult(ResultStatus.FAIL, "修改部门失败", null);
		}
		return result;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public WebAppResult delete(@RequestBody long[] ids) {
		// 判断要删除的部门下，是否有子部门。
		if (departmentBiz.hasChildrenById(ids[0])) {
			return new WebAppResult(ResultStatus.FAIL, "该部门下还存在子部门，不能删除。", null);
		}
		// 判断要删除的部门下，是否有员工。
		if (employeeBiz.hasEmployeeByDeptId(ids[0])) {
			return new WebAppResult(ResultStatus.FAIL, "该部门下还有员工，不能删除。", null);
		}
		WebAppResult result;
		if (departmentBiz.deleteByIds(ids)) {
			result = new WebAppResult(ResultStatus.SUCCESS, "删除成功", null);
		} else {
			result = new WebAppResult(ResultStatus.FAIL, "删除失败", null);
		}
		return result;
	}

	@RequestMapping(value = "all", method = RequestMethod.GET)
	public WebAppResult all() {
		return new WebAppResult(ResultStatus.SUCCESS, "", departmentBiz.list());
	}

	@RequestMapping(value = "/listById", method = RequestMethod.GET)
	public WebAppResult listById(HttpSession httpSession) {
		Employee employee = (Employee) httpSession.getAttribute(WebConstant.SESSION_NAME);
		if (employee != null) {
			List<Department> departments = departmentBiz.listById(employee.getDepartment().getId());
			return new WebAppResult(ResultStatus.SUCCESS, "", departments);
		} else {
			return new WebAppResult(ResultStatus.TIMEOUT, "", null);
		}
	}

}
