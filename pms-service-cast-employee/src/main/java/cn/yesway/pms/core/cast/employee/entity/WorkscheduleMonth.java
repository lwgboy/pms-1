package cn.yesway.pms.core.cast.employee.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.yesway.pms.common.entity.BaseEntity;
import cn.yesway.pms.common.enums.ApprovalStatus;

public class WorkscheduleMonth extends BaseEntity {

	private static final long serialVersionUID = 4314168621799682829L;
	private Date affiliationDate;
	private int worktime;
	private List<WorkscheduleMonthDetail> details = new ArrayList<>();
	private ApprovalStatus status;

	@DateTimeFormat(pattern = "yyyy-MM")
	@JsonFormat(pattern = "yyyy-MM", timezone = "GMT+08")
	public Date getAffiliationDate() {
		return affiliationDate;
	}

	public void setAffiliationDate(Date affiliationDate) {
		this.affiliationDate = affiliationDate;
	}

	public int getWorktime() {
		return worktime;
	}

	public void setWorktime(int worktime) {
		this.worktime = worktime;
	}

	public List<WorkscheduleMonthDetail> getDetails() {
		return details;
	}

	public void setDetails(List<WorkscheduleMonthDetail> details) {
		this.details = details;
	}

	public ApprovalStatus getStatus() {
		return status;
	}

	public void setStatus(ApprovalStatus status) {
		this.status = status;
	}

}
