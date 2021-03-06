package cn.yesway.pms.core.cast.resource.entity;

import java.math.BigDecimal;

import cn.yesway.pms.common.entity.BaseEntity;
import cn.yesway.pms.core.project.entity.Project;

public class ResourceCastReportDetail extends BaseEntity {

	private static final long serialVersionUID = -6052437490631223465L;
	private Project project;
	private BigDecimal preTaxCast;
	private BigDecimal tasMoney;
	private BigDecimal afterTaxCast;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
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

}
