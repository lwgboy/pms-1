package cn.yesway.pms.core.cast.employee.entity;

import java.util.ArrayList;
import java.util.List;

import cn.yesway.pms.common.entity.BaseEntity;
import cn.yesway.pms.core.employee.entity.Employee;

public class WorkscheduleMonthDetail extends BaseEntity {

	private static final long serialVersionUID = 962541349262323966L;
	private Long workscheduleMonthId;
	private Employee employee;
	private List<WorkscheduleMonthProjectDetail> details = new ArrayList<>();

	public Long getWorkscheduleMonthId() {
		return workscheduleMonthId;
	}

	public void setWorkscheduleMonthId(Long workscheduleMonthId) {
		this.workscheduleMonthId = workscheduleMonthId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<WorkscheduleMonthProjectDetail> getDetails() {
		return details;
	}

	public void setDetails(List<WorkscheduleMonthProjectDetail> details) {
		this.details = details;
	}

}
