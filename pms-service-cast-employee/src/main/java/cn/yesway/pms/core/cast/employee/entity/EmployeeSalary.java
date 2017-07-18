package cn.yesway.pms.core.cast.employee.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.yesway.pms.common.entity.BaseEntity;
import cn.yesway.pms.common.enums.ApprovalStatus;

public class EmployeeSalary extends BaseEntity {

	private static final long serialVersionUID = -8721391993291667388L;
	private Date createDate;
	private Integer workDay;
	private ApprovalStatus status;
	private BigDecimal salary = new BigDecimal(0.0);
	private BigDecimal socialSecurity = new BigDecimal(0.0);
	private BigDecimal compensationSalary = new BigDecimal(0.0);
	private BigDecimal laborUnionSalary = new BigDecimal(0.0);
	private List<EmployeeSalaryDetail> details = new ArrayList<>();

	@DateTimeFormat(pattern = "yyyy-MM")
	@JsonFormat(pattern = "yyyy-MM", timezone = "GMT+8")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public ApprovalStatus getStatus() {
		return status;
	}

	public void setStatus(ApprovalStatus status) {
		this.status = status;
	}

	public Integer getWorkDay() {
		return workDay;
	}

	public void setWorkDay(Integer workDay) {
		this.workDay = workDay;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public BigDecimal getSocialSecurity() {
		return socialSecurity;
	}

	public void setSocialSecurity(BigDecimal socialSecurity) {
		this.socialSecurity = socialSecurity;
	}

	public BigDecimal getCompensationSalary() {
		return compensationSalary;
	}

	public void setCompensationSalary(BigDecimal compensationSalary) {
		this.compensationSalary = compensationSalary;
	}

	public BigDecimal getLaborUnionSalary() {
		return laborUnionSalary;
	}

	public void setLaborUnionSalary(BigDecimal laborUnionSalary) {
		this.laborUnionSalary = laborUnionSalary;
	}

	public List<EmployeeSalaryDetail> getDetails() {
		return details;
	}

	public void setDetails(List<EmployeeSalaryDetail> details) {
		this.details = details;
	}

}
