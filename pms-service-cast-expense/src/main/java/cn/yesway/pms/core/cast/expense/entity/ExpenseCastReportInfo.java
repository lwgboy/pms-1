package cn.yesway.pms.core.cast.expense.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExpenseCastReportInfo implements Serializable {

	private static final long serialVersionUID = -7251401214590062640L;
	private List<String> expenseTemplate;
	private List<ExpenseCastReportDepartment> departments = new ArrayList<>();

	public List<String> getExpenseTemplate() {
		return expenseTemplate;
	}

	public void setExpenseTemplate(List<String> expenseTemplate) {
		this.expenseTemplate = expenseTemplate;
	}

	public List<ExpenseCastReportDepartment> getDepartments() {
		return departments;
	}

	public void setDepartments(List<ExpenseCastReportDepartment> departments) {
		this.departments = departments;
	}
	
	public ExpenseCastReportDepartment get(String name) {
		ExpenseCastReportDepartment result = null;
		for(ExpenseCastReportDepartment dept : departments) {
			if(dept.getName().equals(name)) {
				return dept;
			}
		}
		return result;
	}
	
	public Boolean contains(String name) {
		for(ExpenseCastReportDepartment dept : departments) {
			if(dept.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

}
