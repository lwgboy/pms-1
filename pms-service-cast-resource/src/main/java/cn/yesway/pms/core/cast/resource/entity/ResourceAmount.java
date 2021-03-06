package cn.yesway.pms.core.cast.resource.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.yesway.pms.common.entity.BaseEntity;
import cn.yesway.pms.common.enums.ApprovalStatus;

public class ResourceAmount extends BaseEntity {

	private static final long serialVersionUID = -5694338109476781856L;
	private Date affiliationDate;
	private ApprovalStatus status;
	private Boolean hasCast;
	private List<ResourceAmountDetail> details = new ArrayList<>();

	@DateTimeFormat(pattern = "yyyy-MM")
	@JsonFormat(pattern = "yyyy-MM", timezone = "GMT+08")
	public Date getAffiliationDate() {
		return affiliationDate;
	}

	public void setAffiliationDate(Date affiliationDate) {
		this.affiliationDate = affiliationDate;
	}

	public ApprovalStatus getStatus() {
		return status;
	}

	public void setStatus(ApprovalStatus status) {
		this.status = status;
	}

	public List<ResourceAmountDetail> getDetails() {
		return details;
	}

	public void setDetails(List<ResourceAmountDetail> details) {
		this.details = details;
	}

	public Boolean getHasCast() {
		return hasCast;
	}

	public void setHasCast(Boolean hasCast) {
		this.hasCast = hasCast;
	}

}
