package cn.yesway.pms.core.cast.expense.entity;

import java.math.BigDecimal;

import cn.yesway.pms.common.entity.BaseEntity;
import cn.yesway.pms.core.department.entity.Department;
import cn.yesway.pms.core.project.entity.Project;

public class ExpenseCastDetail extends BaseEntity {

	private static final long serialVersionUID = 1366879632738179413L;
	private Long expenseCastId;
	private Project project;
	private BigDecimal preTaxCast;
	private BigDecimal tasMoney;
	private BigDecimal afterTaxCast;
	private String description;
	private Expense expense;
	private Department department;

	public Long getExpenseCastId() {
		return expenseCastId;
	}

	public void setExpenseCastId(Long expenseCastId) {
		this.expenseCastId = expenseCastId;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public BigDecimal getPreTaxCast() {
		return preTaxCast;
	}

	public void setPreTaxCast(BigDecimal preTaxCast) {
		this.preTaxCast = preTaxCast;
	}

	public BigDecimal getTasMoney() {
		return tasMoney;
	}

	public void setTasMoney(BigDecimal tasMoney) {
		this.tasMoney = tasMoney;
	}

	public BigDecimal getAfterTaxCast() {
		return afterTaxCast;
	}

	public void setAfterTaxCast(BigDecimal afterTaxCast) {
		this.afterTaxCast = afterTaxCast;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Expense getExpense() {
		return expense;
	}

	public void setExpense(Expense expense) {
		this.expense = expense;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
