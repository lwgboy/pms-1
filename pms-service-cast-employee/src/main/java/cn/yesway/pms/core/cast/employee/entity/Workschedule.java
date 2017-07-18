package cn.yesway.pms.core.cast.employee.entity;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.yesway.pms.common.entity.BaseEntity;
import cn.yesway.pms.common.enums.ApprovalStatus;
import cn.yesway.pms.core.employee.entity.Employee;

public class Workschedule extends BaseEntity {

	private static final long serialVersionUID = 7454018071615764932L;
	private Employee employee = new Employee();
	private Date createDate;
	private ApprovalStatus status;
	private List<WorkscheduleDetail> details;
	private Integer totalWorkTime;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08")
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

	public List<WorkscheduleDetail> getDetails() {
		return details;
	}

	public void setDetails(List<WorkscheduleDetail> details) {
		this.details = details;
	}

	public Integer getTotalWorkTime() {
		return totalWorkTime;
	}

	public void setTotalWorkTime(Integer totalWorkTime) {
		this.totalWorkTime = totalWorkTime;
	}

}
