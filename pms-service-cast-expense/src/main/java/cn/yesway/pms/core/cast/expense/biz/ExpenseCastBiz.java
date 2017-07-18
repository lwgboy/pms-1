package cn.yesway.pms.core.cast.expense.biz;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.yesway.pms.common.enums.ApprovalStatus;
import cn.yesway.pms.common.page.PageBean;
import cn.yesway.pms.common.page.PageParam;
import cn.yesway.pms.core.cast.expense.dao.ExpenseCastDao;
import cn.yesway.pms.core.cast.expense.entity.Expense;
import cn.yesway.pms.core.cast.expense.entity.ExpenseCast;
import cn.yesway.pms.core.cast.expense.entity.ExpenseCastReportData;
import cn.yesway.pms.core.cast.expense.entity.ExpenseCastReportDepartment;
import cn.yesway.pms.core.cast.expense.entity.ExpenseCastReportInfo;
import cn.yesway.pms.core.cast.expense.entity.ExpenseCastReportProject;
import cn.yesway.pms.core.department.entity.Department;
import cn.yesway.pms.core.project.entity.Project;
import cn.yesway.pms.generator.service.Generator;

@Component("expenseCastBiz")
public class ExpenseCastBiz {

	@Autowired
	public Generator businessCodeGenerator;
	@Autowired
	public ExpenseCastDao expenseCastDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean create(ExpenseCast expenseCast) {
		String code = (String) businessCodeGenerator.generate("OP");
		System.out.println(code);
		expenseCast.setCode(code);
		expenseCast.setStatus(ApprovalStatus.EDIT);
		if (expenseCastDao.insert(expenseCast) > 0) {
			expenseCastDao.insertDetail(expenseCast);
		}
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean update(ExpenseCast expenseCast) {
		if (expenseCastDao.update(expenseCast) > 0) {
			expenseCastDao.deleteDetailById(expenseCast.getId());
			expenseCastDao.insertDetail(expenseCast);
		}
		return true;
	}

	public Boolean updateStatus(ExpenseCast expenseCast) {
		if (expenseCastDao.update(expenseCast) > 0) {
			return true;
		}
		return false;
	}

	public ExpenseCast findById(Long id) {
		return expenseCastDao.findById(id);
	}

	public PageBean<ExpenseCast> listByPageAndParam(PageParam param, Map<String, Object> map) {
		return expenseCastDao.listByPageAndParam(param, map);
	}

	public ExpenseCastReportInfo getReport(Long id) {
		List<ExpenseCastReportData> datas = expenseCastDao.findReportById(id);
		// 将金额放入项目中，过滤出费用项
		ExpenseCastReportInfo result = new ExpenseCastReportInfo();
		result.setExpenseTemplate(
				datas.stream().map(ecr -> ecr.getExpense().getName()).distinct().collect(Collectors.toList()));
		for (ExpenseCastReportData data : datas) {
			Department dept = data.getDepartment();
			Project project = data.getProject();
			Expense expense = data.getExpense();
			BigDecimal cast = data.getCast();

			ExpenseCastReportDepartment ecrDept;
			ExpenseCastReportProject ecrProject;

			// 如果部门不再结果集中，则把部门和项目都添加到结果集。
			if (!result.contains(dept.getName())) {
				ecrDept = new ExpenseCastReportDepartment();
				ecrDept.setName(dept.getName());
				ecrProject = new ExpenseCastReportProject();
				ecrProject.setName(project.getName());
				ecrProject.setCasts(new BigDecimal[result.getExpenseTemplate().size()]);
				setCast(ecrProject, expense, result.getExpenseTemplate(), cast);
				ecrDept.getProjects().add(ecrProject);
				result.getDepartments().add(ecrDept);
			} else {
				ecrDept = result.get(dept.getName());
				if (!ecrDept.contains(project.getName())) {
					ecrProject = new ExpenseCastReportProject();
					ecrProject.setCasts(new BigDecimal[result.getExpenseTemplate().size()]);
					ecrProject.setName(project.getName());
					setCast(ecrProject, expense, result.getExpenseTemplate(), cast);
					ecrDept.getProjects().add(ecrProject);
				} else {
					ecrProject = ecrDept.get(project.getName());
					setCast(ecrProject, expense, result.getExpenseTemplate(), cast);
				}
			}
		}
		return result;
	}

	private void setCast(ExpenseCastReportProject ecrProject, Expense expense, List<String> expenseTemplate,
			BigDecimal cast) {
		for (int i = 0; i < expenseTemplate.size(); i++) {
			if (expenseTemplate.get(i).equals(expense.getName())) {
				ecrProject.getCasts()[i] = cast;
				break;
			}
		}
	}
}
