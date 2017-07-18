package cn.yesway.pms.core.cast.employee.entity;

import cn.yesway.pms.common.entity.BaseEntity;

public class EmployeeSalaryDetail extends BaseEntity {

	private static final long serialVersionUID = -7113439681108672309L;
	private String employeeCode;
	private String monthlySalary;
	private String socialSecurity;
	private String hourlySalary;
	private String compensationSalary;
	private String laborUnionSalary;

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getMonthlySalary() {
		return monthlySalary;
	}

	public void setMonthlySalary(String monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	public String getSocialSecurity() {
		return socialSecurity;
	}

	public void setSocialSecurity(String socialSecurity) {
		this.socialSecurity = socialSecurity;
	}

	public String getHourlySalary() {
		return hourlySalary;
	}

	public void setHourlySalary(String hourlySalary) {
		this.hourlySalary = hourlySalary;
	}

	public String getCompensationSalary() {
		return compensationSalary;
	}

	public void setCompensationSalary(String compensationSalary) {
		this.compensationSalary = compensationSalary;
	}

	public String getLaborUnionSalary() {
		return laborUnionSalary;
	}

	public void setLaborUnionSalary(String laborUnionSalary) {
		this.laborUnionSalary = laborUnionSalary;
	}

}
