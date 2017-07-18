package cn.yesway.pms.core.cast.expense.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExpenseCastReportDepartment implements Serializable {

	private static final long serialVersionUID = 1135809402757772369L;
	private String name;
	private List<ExpenseCastReportProject> projects = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ExpenseCastReportProject> getProjects() {
		return projects;
	}

	public void setProjects(List<ExpenseCastReportProject> projects) {
		this.projects = projects;
	}

	public ExpenseCastReportProject get(String name) {
		ExpenseCastReportProject result = null;
		for (ExpenseCastReportProject project : projects) {
			if (project.getName().equals(name)) {
				return project;
			}
		}
		return result;
	}

	public Boolean contains(String name) {
		for(ExpenseCastReportProject project : projects) {
			if (project.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
}
