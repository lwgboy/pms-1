package cn.yesway.pms.core.report.entity;

import cn.yesway.pms.common.entity.BaseEntity;
import cn.yesway.pms.core.employee.entity.Employee;
import cn.yesway.pms.core.project.entity.Project;

public class ProjectWorkTime extends BaseEntity {

	private static final long serialVersionUID = -2102644894417012862L;
	private Employee employee;
	private Project project;
	private Integer workTime;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Integer getWorkTime() {
		return workTime;
	}

	public void setWorkTime(Integer workTime) {
		this.workTime = workTime;
	}

}
