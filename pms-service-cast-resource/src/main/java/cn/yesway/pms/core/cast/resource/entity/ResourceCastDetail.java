package cn.yesway.pms.core.cast.resource.entity;

import java.math.BigDecimal;

import cn.yesway.pms.common.entity.BaseEntity;

public class ResourceCastDetail extends BaseEntity {

	private static final long serialVersionUID = 7505395420408411645L;
	private Resource resource;
	private BigDecimal preTaxCast;
	private BigDecimal tasMoney;
	private BigDecimal afterTaxCast;
	private String description;

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
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

}
