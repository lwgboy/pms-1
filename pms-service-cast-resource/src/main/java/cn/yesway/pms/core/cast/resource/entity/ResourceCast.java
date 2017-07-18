package cn.yesway.pms.core.cast.resource.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.yesway.pms.common.entity.BaseEntity;
import cn.yesway.pms.common.enums.ApprovalStatus;

public class ResourceCast extends BaseEntity {

	private static final long serialVersionUID = 2022548264387144091L;
	private Date affiliationDate;
	private BigDecimal preTaxCast;
	private BigDecimal tasMoney;
	private BigDecimal afterTaxCast;
	private String description;
	private ApprovalStatus status;
	private List<ResourceCastDetail> details = new ArrayList<>();

	@DateTimeFormat(pattern = "yyyy-MM")
	@JsonFormat(pattern = "yyyy-MM", timezone = "GMT+08")
	public Date getAffiliationDate() {
		return affiliationDate;
	}

	public void setAffiliationDate(Date affiliationDate) {
		this.affiliationDate = affiliationDate;
	}

	public BigDecimal getPreTaxCast() {
		return preTaxCast;
	}

	public void setPreTaxCast(BigDecimal preTaxCast) {
		this.preTaxCast = preTaxCast;
	}

	public BigDecimal getTasMoney() {
		return tasMoney;
	}

	public void setTasMoney(BigDecimal tasMoney) {
		this.tasMoney = tasMoney;
	}

	public BigDecimal getAfterTaxCast() {
		return afterTaxCast;
	}

	public void setAfterTaxCast(BigDecimal afterTaxCast) {
		this.afterTaxCast = afterTaxCast;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ApprovalStatus getStatus() {
		return status;
	}

	public void setStatus(ApprovalStatus status) {
		this.status = status;
	}

	public List<ResourceCastDetail> getDetails() {
		return details;
	}

	public void setDetails(List<ResourceCastDetail> details) {
		this.details = details;
	}

}
