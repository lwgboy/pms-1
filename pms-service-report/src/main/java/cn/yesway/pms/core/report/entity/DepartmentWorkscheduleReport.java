package cn.yesway.pms.core.report.entity;

import cn.yesway.pms.core.department.entity.Department;

public class DepartmentWorkscheduleReport extends WorkscheduleReport {

	private static final long serialVersionUID = -5234376217871479103L;
	private Department department;

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
