package cn.yesway.pms.web.console.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

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
import cn.yesway.pms.core.cast.expense.biz.ExpenseCastBiz;
import cn.yesway.pms.core.cast.expense.entity.ExpenseCast;
import cn.yesway.pms.core.employee.entity.Employee;

@RestController
@RequestMapping(value = { "/expense/cast" })
public class ExpenseCastController {

	@Autowired
	private ExpenseCastBiz expenseCastBiz;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public WebAppResult list(@RequestBody Map<String, Object> map, HttpSession httpSession) {
		PageParam pageParam = new PageParam(Integer.parseInt(map.get("pageNum").toString()),
				Integer.parseInt(map.get("numPerPage").toString()));
		Employee employee = (Employee) httpSession.getAttribute(WebConstant.SESSION_NAME);
		map.put("employeeId", employee.getId());
		WebAppResult result = new WebAppResult(ResultStatus.SUCCESS, "",
				expenseCastBiz.listByPageAndParam(pageParam, map));
		return result;
	}
	
	@RequestMapping(value = "/review/list", method = RequestMethod.POST)
	public WebAppResult reviewList(@RequestBody Map<String, Object> map) {
		PageParam pageParam = new PageParam(Integer.parseInt(map.get("pageNum").toString()),
				Integer.parseInt(map.get("numPerPage").toString()));
		WebAppResult result = new WebAppResult(ResultStatus.SUCCESS, "",
				expenseCastBiz.listByPageAndParam(pageParam, map));
		return result;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public WebAppResult list(@PathVariable("id") Long id) {
		WebAppResult result = new WebAppResult(ResultStatus.SUCCESS, "", expenseCastBiz.findById(id));
		return result;
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public WebAppResult create(@RequestBody ExpenseCast expenseCast, HttpSession httpSession) {
		WebAppResult result;
		Employee employee = (Employee) httpSession.getAttribute(WebConstant.SESSION_NAME);
		expenseCast.setCompany(employee.getCompany());
		expenseCast.setEmployee(employee);
		if (expenseCastBiz.create(expenseCast)) {
			result = new WebAppResult(ResultStatus.SUCCESS, "创建成功。", null);
		} else {
			result = new WebAppResult(ResultStatus.FAIL, "创建失败。", null);
		}
		return result;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public WebAppResult update(@RequestBody ExpenseCast expenseCast, HttpSession httpSession) {
		WebAppResult result;
		Employee employee = (Employee) httpSession.getAttribute(WebConstant.SESSION_NAME);
		expenseCast.setCompany(employee.getCompany());
		expenseCast.setEmployee(employee);
		if (expenseCastBiz.update(expenseCast)) {
			result = new WebAppResult(ResultStatus.SUCCESS, "更新成功。", null);
		} else {
			result = new WebAppResult(ResultStatus.FAIL, "更新失败。", null);
		}
		return result;
	}

	@RequestMapping(value = "update/status", method = RequestMethod.POST)
	public WebAppResult updateStatus(@RequestBody ExpenseCast expenseCast) {
		WebAppResult result;
		if (expenseCastBiz.updateStatus(expenseCast)) {
			result = new WebAppResult(ResultStatus.SUCCESS, "更新成功。", null);
		} else {
			result = new WebAppResult(ResultStatus.FAIL, "更新失败。", null);
		}
		return result;
	}
	
	@RequestMapping(value = "report/{id}", method = RequestMethod.GET)
	public WebAppResult report(@PathVariable("id") Long id) {
		WebAppResult result = new WebAppResult(ResultStatus.SUCCESS, "", expenseCastBiz.getReport(id));
		return result;
	}

	
	
}
