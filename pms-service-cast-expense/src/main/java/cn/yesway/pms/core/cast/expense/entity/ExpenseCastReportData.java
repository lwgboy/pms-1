package cn.yesway.pms.core.cast.expense.entity;

import java.math.BigDecimal;

import cn.yesway.pms.common.entity.BaseEntity;
import cn.yesway.pms.core.department.entity.Department;
import cn.yesway.pms.core.project.entity.Project;

public class ExpenseCastReportData extends BaseEntity {

	private static final long serialVersionUID = 5078839651192162281L;
	private Department department;
	private Project project;
	private Expense expense;
	private BigDecimal cast;

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Expense getExpense() {
		return expense;
	}

	public void setExpense(Expense expense) {
		this.expense = expense;
	}

	public BigDecimal getCast() {
		return cast;
	}

	public void setCast(BigDecimal cast) {
		this.cast = cast;
	}

}
