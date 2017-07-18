package cn.yesway.pms.web.console.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import cn.yesway.pms.core.employee.biz.EmployeeBiz;
import cn.yesway.pms.core.employee.entity.Employee;

@RestController
@RequestMapping(value = { "/employee" })
public class EmployeeController {

	private static final Log LOGGER = LogFactory.getLog(EmployeeController.class);

	@Autowired
	private EmployeeBiz employeeBiz;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public WebAppResult login(@RequestBody Employee employee, HttpSession httpSession, HttpServletRequest request,
			HttpServletResponse response) {
		Employee currentEmployee = employeeBiz.login(employee.getLoginName(), employee.getLoginPwd());
		WebAppResult result;
		if (currentEmployee != null) {
			result = new WebAppResult(ResultStatus.SUCCESS, "登录成功", null);
			httpSession.setAttribute("Employee", currentEmployee);
			LOGGER.debug(currentEmployee.getName() + "登录成功");
			if (employee.getRememberMe()) {
				Cookie loginNameCookie = new Cookie(WebConstant.COOKIE_LOGIN_NAME, employee.getLoginName());
				loginNameCookie.setMaxAge(50 * 365 * 24 * 60 * 60);
				loginNameCookie.setPath("/");
				Cookie loginPasswordCookie = new Cookie(WebConstant.COOKIE_LOGIN_PASSWORD, employee.getLoginPwd());
				loginPasswordCookie.setMaxAge(50 * 365 * 24 * 60 * 60);
				loginPasswordCookie.setPath("/");
				response.addCookie(loginNameCookie);
				response.addCookie(loginPasswordCookie);
			}
		} else {
			result = new WebAppResult(ResultStatus.FAIL, "用户名或密码不正确", null);
		}
		return result;
	}

	@RequestMapping(value = "/getCurrent", method = RequestMethod.GET)
	public WebAppResult getCurrent(HttpSession httpSession) {
		// 获得当前登录的员工信息。
		Employee employee = (Employee) httpSession.getAttribute(WebConstant.SESSION_NAME);
		WebAppResult result;
		if (employee != null) {
			result = new WebAppResult(ResultStatus.SUCCESS, "", employee);
		} else {
			result = new WebAppResult(ResultStatus.TIMEOUT, "", null);
		}
		return result;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public WebAppResult logout(HttpSession httpSession, HttpServletRequest request, HttpServletResponse response) {
		// 在session中注销员工信息
		httpSession.removeAttribute(WebConstant.SESSION_NAME);
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(WebConstant.COOKIE_LOGIN_NAME)
						|| cookie.getName().equals(WebConstant.COOKIE_LOGIN_PASSWORD)) {
					cookie.setValue(null);
					cookie.setMaxAge(0);// 立即销毁cookie
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}
		}
		return new WebAppResult(ResultStatus.TIMEOUT, "", null);
	}

	@RequestMapping(value = "/update/password", method = RequestMethod.POST)
	public WebAppResult updatePassword(@RequestBody Employee employee, HttpSession httpSession) {
		employee.setId(((Employee) httpSession.getAttribute(WebConstant.SESSION_NAME)).getId());
		WebAppResult result;
		// 判断旧密码是否正确。
		if (employeeBiz.validateOldPassword(employee)) {
			// 如果旧密码正确，则更新。
			if (employeeBiz.updatePassword(employee)) {
				result = new WebAppResult(ResultStatus.SUCCESS, "密码修改成功", null);
			} else {
				result = new WebAppResult(ResultStatus.FAIL, "密码修改失败", null);
			}
		} else {
			result = new WebAppResult(ResultStatus.FAIL, "输入的旧密码不正确", null);
		}
		return result;
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public WebAppResult list(@RequestBody Map<String, Object> map) {
		PageParam pageParam = new PageParam(Integer.parseInt(map.get("pageNum").toString()),
				Integer.parseInt(map.get("numPerPage").toString()));
		PageBean<Employee> list = employeeBiz.listByPageAndParam(pageParam, map);
		return new WebAppResult(ResultStatus.SUCCESS, "", list);
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public WebAppResult create(@RequestBody Employee employee) {
		if (employeeBiz.create(employee)) {
			return new WebAppResult(ResultStatus.SUCCESS, "创建员工成功", null);
		}
		return new WebAppResult(ResultStatus.FAIL, "创建员工失败", null);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public WebAppResult findById(@PathVariable long id) {
		Employee employee = employeeBiz.findById(id);
		WebAppResult result;
		if (employee != null) {
			result = new WebAppResult(ResultStatus.SUCCESS, "", employee);
		} else {
			result = new WebAppResult(ResultStatus.FAIL, "没有查找到员工", null);
		}
		return result;
	}

	@RequestMapping(value = "/list/param", method = RequestMethod.POST)
	public WebAppResult findByParam(@RequestBody Map<String, Object> map) {
		List<Employee> employees = employeeBiz.listByParam(map);
		return new WebAppResult(ResultStatus.SUCCESS, "", employees);
	}

	@RequestMapping(value = "/list/currentDepartment", method = RequestMethod.GET)
	public WebAppResult findBycurrentDepartment(HttpSession httpSession) {
		Employee employee = (Employee) httpSession.getAttribute(WebConstant.SESSION_NAME);
		if (employee != null) {
			List<Employee> employees = employeeBiz.listByDeptId(employee.getDepartment().getId());
			return new WebAppResult(ResultStatus.SUCCESS, "", employees);
		} else {
			return new WebAppResult(ResultStatus.TIMEOUT, "", null);
		}
	}

	@RequestMapping(value = "/list/department/{id}", method = RequestMethod.GET)
	public WebAppResult findByDepartmentId(@PathVariable long id) {
		List<Employee> employees = employeeBiz.listByDeptId(id);
		return new WebAppResult(ResultStatus.SUCCESS, "", employees);
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public WebAppResult update(@RequestBody Employee employee) {
		WebAppResult result;
		if (employeeBiz.update(employee)) {
			result = new WebAppResult(ResultStatus.SUCCESS, "修改员工成功", employee.getId());
		} else {
			result = new WebAppResult(ResultStatus.FAIL, "修改员工失败", null);
		}
		return result;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public WebAppResult delete(@RequestBody long[] ids) {
		WebAppResult result;
		if (employeeBiz.deleteByIds(ids)) {
			result = new WebAppResult(ResultStatus.SUCCESS, "删除成功", null);
		} else {
			result = new WebAppResult(ResultStatus.FAIL, "删除失败", null);
		}
		return result;
	}

	@RequestMapping(value = "isExistsCode", method = RequestMethod.POST)
	public WebAppResult isExistsCode(@RequestBody Map<String, Object> params) {
		WebAppResult result;
		if (employeeBiz.isExists(params)) {
			result = new WebAppResult(ResultStatus.SUCCESS, "", true);
		} else {
			result = new WebAppResult(ResultStatus.FAIL, "员工编号已存在", false);
		}
		return result;
	}

	@RequestMapping(value = "isExistsLoginName", method = RequestMethod.POST)
	public WebAppResult isExistsLoginName(@RequestBody Map<String, Object> params) {
		WebAppResult result;
		if (employeeBiz.isExists(params)) {
			result = new WebAppResult(ResultStatus.SUCCESS, "", true);
		} else {
			result = new WebAppResult(ResultStatus.FAIL, "登录名已存在", false);
		}
		return result;
	}

}
