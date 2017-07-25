package cn.yesway.pms.core.report.entity;

import java.math.BigDecimal;

import cn.yesway.pms.common.entity.BaseEntity;
import cn.yesway.pms.core.employee.entity.Employee;

public class EmployeeSalaryReport extends BaseEntity {

	private static final long serialVersionUID = -7397842599319601451L;
	private Employee employee;
	private String value;
	private String value1;
	private BigDecimal hourlySalary;
	private BigDecimal monthly_salary;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public BigDecimal getHourlySalary() {
		return hourlySalary;
	}

	public void setHourlySalary(BigDecimal hourlySalary) {
		this.hourlySalary = hourlySalary;
	}

	public BigDecimal getMonthly_salary() {
		return monthly_salary;
	}

	public void setMonthly_salary(BigDecimal monthly_salary) {
		this.monthly_salary = monthly_salary;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

}
