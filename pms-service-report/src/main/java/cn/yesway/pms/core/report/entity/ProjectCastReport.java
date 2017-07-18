package cn.yesway.pms.core.report.entity;

import java.math.BigDecimal;

import cn.yesway.pms.common.entity.BaseEntity;
import cn.yesway.pms.core.project.entity.Project;

public class ProjectCastReport extends BaseEntity {

	private static final long serialVersionUID = -8380963765321562118L;
	private Project project;
	private BigDecimal salary;
	private BigDecimal operatingCast;
	private BigDecimal rentCast;
	private BigDecimal assetCast;
	private BigDecimal resourceCast;
	private BigDecimal totalCast;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public BigDecimal getOperatingCast() {
		return operatingCast;
	}

	public void setOperatingCast(BigDecimal operatingCast) {
		this.operatingCast = operatingCast;
	}

	public BigDecimal getRentCast() {
		return rentCast;
	}

	public void setRentCast(BigDecimal rentCast) {
		this.rentCast = rentCast;
	}

	public BigDecimal getAssetCast() {
		return assetCast;
	}

	public void setAssetCast(BigDecimal assetCast) {
		this.assetCast = assetCast;
	}

	public BigDecimal getResourceCast() {
		return resourceCast;
	}

	public void setResourceCast(BigDecimal resourceCast) {
		this.resourceCast = resourceCast;
	}

	public BigDecimal getTotalCast() {
		return totalCast;
	}

	public void setTotalCast(BigDecimal totalCast) {
		this.totalCast = totalCast;
	}

}
