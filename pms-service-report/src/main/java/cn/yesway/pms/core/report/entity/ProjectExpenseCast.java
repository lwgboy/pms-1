package cn.yesway.pms.core.report.entity;

import java.math.BigDecimal;

import cn.yesway.pms.common.entity.BaseEntity;
import cn.yesway.pms.core.cast.expense.entity.Expense;
import cn.yesway.pms.core.project.entity.Project;

public class ProjectExpenseCast extends BaseEntity {

	private static final long serialVersionUID = -11933289040639390L;
	private Project project;
	private Expense expense;
	private BigDecimal cast;

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
