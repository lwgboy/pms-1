package cn.yesway.pms.core.report.entity;

import cn.yesway.pms.core.employee.entity.Employee;

public class EmployeeWorkscheduleReport extends WorkscheduleReport {

	private static final long serialVersionUID = -516026737777020015L;
	private Employee employee;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
